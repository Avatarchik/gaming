goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.events");
goog.require("amaya.game.Model");
goog.require("amaya.game.Display");
goog.require("amaya.game.PaylineView");
goog.require("amaya.game.ReelView");
goog.require("amaya.game.StarsView");

goog.provide("amaya.game.Game");

amaya.game.Game = function () {

    var instance = {};

    var gameServices;
    var gameCanvasManager = new amaya.sdk.ui.canvas.CanvasManager();
    var soundSystem = new amaya.sdk.sound.SoundSystem();
    var model;
    var display;
    var backgroundView;
    var starsView;
    var wonView;
    var reelView;
    var paylineView;
    var freeSpinView;

    amaya.sdk.singleplayer.V2Game.init( startMain, resumeMain );

    function startMain ( data, openingBalance ) {
        main(openingBalance);

        // A set of fake reel stops are created to be displayed on the reels when the game starts.
        // For gaming authority certification purposes, the stops displayed when the game begins must be a real,
        // achievable result. In other words, the player must be able to get result by playing the game.
        model.setReelStops([[2,4,2],[3,5,1],[7,1,3],[6,3,2],[1,3,5]]);

        // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
        // model (Set above during model instantiation).
        reelView.forceReels();
    }

    function resumeMain ( data, openingBalance ) {
        main(openingBalance);

        var resumeGameResponse = new amaya.game.messaging.ResumeGameResponse(data);

        model.setCreditValue(resumeGameResponse.getCreditValue());
        model.setCreditsPerLine(resumeGameResponse.getCreditsPerLine());
        model.setCreditsWon(resumeGameResponse.getCreditsWon());
        model.setReelStops(resumeGameResponse.getReelStops());
        model.setLinesWon(resumeGameResponse.getLinesWon());
        model.setWinningLines(resumeGameResponse.getWinningLines());
        model.setStarsTotal(resumeGameResponse.getStarsTotal());

        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDIT_VALUE, resumeGameResponse.getCreditValue());
        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDITS_PER_LINE, resumeGameResponse.getCreditsPerLine());

        updateFinanceFields();
        reelView.forceReels();

        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.
        if ( resumeGameResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
            model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(resumeGameResponse.getFreeSpinsUsed());

            if ( resumeGameResponse.getFreeSpinsUsed() > 0 ) {
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();
                freeSpinView.updateCounter(model.getFreeSpinsUsed() + 1,model.getFreeSpinsTotal());
            }
        }

        onSpinComplete(new amaya.game.events.SpinCompleteEvent());
    }

    function main ( openingBalance ) {
        // Create a new instance of GameServices to interact with the framework.
        // Game Services can be instantiated anywhere but since class will use it repeatedly, we'll assign it to a class member variable.
        gameServices = new amaya.GameServices();

        // Create a stepper control for selecting credit value
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDIT_VALUE,
            "Credit Value",
            gameServices.profile.getCreditValues(),
            gameServices.profile.getCreditValues()[0],
            true );

        // Create a stepper control for selecting credits per line
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDITS_PER_LINE,
            "Credits Per Line",
            [1,2,3,4,5],
            1,
            false );

        // Create an autoplay control
        gameServices.gameSettings.addAutoSpinControl(
            amaya.game.GameConstants.ID_AUTO_SPIN,
            "Auto Spin" );
        // The auto play control has additional events that must be integrated to make the auto play system function properly.
        gameServices.addEventListener( amaya.api.events.AutoSpinStartEvent.START, onAutoSpinStart );

        // Create a listener for responses from the server.
        gameServices.addEventListener( amaya.api.events.IncomingMessageEvent.INCOMING_MESSAGE, onIncomingMessage );

        // Create a listener for balance updates.
        gameServices.addEventListener( amaya.api.events.GameFinanceUpdateEvent.GAME_FINANCE_UPDATE, onGameFinanceUpdate );

        gameServices.addEventListener( amaya.api.events.DialogResultEvent.DIALOG_RESULT, onDialogResult );
        gameServices.addEventListener( amaya.api.events.ErrorDisplayEvent.ERROR_DISPLAYED, onErrorDisplayed );
        gameServices.addEventListener( amaya.api.events.ModalDialogChangeEvent.MODAL_DIALOG_CHANGE, onModalDialogChange );
        gameServices.addEventListener( amaya.api.events.OptionChangeEvent.OPTION_CHANGE, onOptionChange );
        gameServices.addEventListener( amaya.api.events.GameSettingChangeEvent.CHANGE, onGameSettingChange );
        gameServices.addEventListener( amaya.api.events.OrientationChangeEvent.CHANGE, onOrientationChange );

        // Enable the use of credit displays within the framework.
        // This allows the display in the bottom status bar to be toggled by the user between cash and credit displays.
        gameServices.enableCreditDisplays();

        display = new amaya.game.Display( gameCanvasManager.getCanvas() );

        // Create the game's model.
        // The model stores the state of the game.
        model = new amaya.game.Model( openingBalance, gameServices.profile.getCreditValues(), amaya.game.GameConstants.NUM_LINES );

        // Create View classes.
        // These classes are responsible for controlling the display of the ui graphics.
        // Each view is passed a reference to the ui (added to the display list above) and a reference to the model.
        //
        // Background View.
        backgroundView = new amaya.game.BackgroundView( display.backgroundContainer );

        // Reel View - Controls the display of the reels (Spin and Icon animations).
        reelView = new amaya.game.ReelView( display.reelsContainer, model, soundSystem );

        // Payline View - Controls the display of the paylines.
        paylineView = new amaya.game.PaylineView( display.paylineContainer, model );

        // Won View - Controls the display of the won field and sounds
        wonView = new amaya.game.WonView( display.wonContainer, model, soundSystem );

        // Update the balance and wagered fields
        updateFinanceFields();

        // Stars View - Controls the display of the star icon accumulator.
        starsView = new amaya.game.StarsView( display.starsContainer, model );

        // Free Spin View - Controls the display of the free spin ui. Note that the Reel View still displays the
        // free spin reel animations.
        freeSpinView = new amaya.game.FreeSpinView( display.freeSpinContainer, model );

        // Send the init request to the server. request is used to get initial information from the server
        // to properly set up the game.
        var initReq = new amaya.game.messaging.InitRequest();
        gameServices.sendV2XMLRequest( initReq.getRequestData() );
    }

    function onIncomingMessage ( event ) {
        switch (event.data.name) {
            case amaya.game.messaging.MessagingConstants.INIT_REQUEST:
                handleInitResponse( new amaya.game.messaging.InitResponse( event.data.response ) );
                break;

            case amaya.game.messaging.MessagingConstants.SPIN_REQUEST:
                handleSpinResponse( new amaya.game.messaging.SpinResponse ( event.data.response ) );
                break;

            case amaya.game.messaging.MessagingConstants.FREE_SPIN_REQUEST:
                handleFreeSpinResponse( new amaya.game.messaging.FreeSpinResponse ( event.data.response ) );
                break;

            case amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq.CONFIRM_HAND_END_REQUEST:
                handleConfirmHandEndRequest();
                break;
        }
    }

    /**
     * Handles the InitRequest response.
     */
    function handleInitResponse ( initResponse ) {
        // Record the number of "stars" accumulated from previous sessions and update the stars view.
        model.setStarsTotal(initResponse.getStarsTotal());
        starsView.updateStarsTotal();

        // Enable the game ui
        enableUI();

        // When all setup is complete, remove the loader screen to reveal the game.
        gameServices.removeLoader();

        // Play the intro sound
        soundSystem.play("intro");

        // Notify the framework that game is now ready for play.
        // This is required by all games.
        gameServices.notifyGameReady();
    }

    /** 
     * Handles the regular Spin Response.
     * @param {amaya.game.messaging.SpinResponse} spinResponse
     */
    function handleSpinResponse ( spinResponse ) {
        // Record data from the response into the model
        model.setCreditsWon(spinResponse.getCreditsWon());
        model.setReelStops(spinResponse.getReelStops());
        model.setLinesWon(spinResponse.getLinesWon());
        model.setWinningLines(spinResponse.getWinningLines());
        model.setStarsTotal(spinResponse.getStarsTotal());

        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.
        if ( spinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
            model.setFreeSpinsTotal(spinResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(spinResponse.getFreeSpinsUsed());
        }
    }

    /** Handles the Free Spin response. */
    function handleFreeSpinResponse ( freeSpinResponse ) {
        // Record data from the response into the model.
        model.setCreditsWon(freeSpinResponse.getCreditsWon());
        model.setReelStops(freeSpinResponse.getReelStops());
        model.setLinesWon(freeSpinResponse.getLinesWon());
        model.setWinningLines(freeSpinResponse.getWinningLines());
        model.setStarsTotal(freeSpinResponse.getStarsTotal());
    
        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then this free spin triggers additional free spins.
        // If so, set the triggered flag to true.
        if ( freeSpinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
        }
    
        // Record the updated free spin data into the model.
        model.setFreeSpinsTotal(freeSpinResponse.getFreeSpinsTotal());
        model.setFreeSpinsUsed(freeSpinResponse.getFreeSpinsUsed());
        model.setFreeSpinsWon(freeSpinResponse.getFreeSpinsWon());
    }

    /**
     * Handle the confirm hand end response.
     * Confirm hand end is a standard game message that tells the server that the results of the game
     * have been displayed to the player so the game is no longer in an unfinished (resumable) state.
     */
    function handleConfirmHandEndRequest () {
        // Responsible gaming regulations require that the 3 seconds must elapse from the start of one spin
        // to the start of the next. When the spin was initiated, the start time was recorded (See spin method).
        // time is compared to the current time to see if 3 seconds have elapsed.
        // If so, the ui is enabled so that the player can spin again.
        // If not, a timer is set to enable the ui when the remaining portion of the 3 seconds has elapsed.
        var elapsedSpinTime = new Date().getTime() - model.getSpinStartTime();
        var delay = 3000 - elapsedSpinTime;
        if ( delay < 0 ) delay = 0;
        setTimeout(function () {
            // Now that the spin is completed, the UI should be re-enabled unless there are additional auto spins.
            // If so, the ui should not re-enable and a slight delay should be included to allow the user to see
            // the results. If the spin has no wins, the delay should be shorter.
            if (gameServices.autoSpin.hasSpins()) {
                setTimeout(autoSpin,(model.spinHasPaylineWins()) ? 1500 : 200 );
            } else {
                enableUI();
            }
        },delay);
    }

    /**
     * Handle the balance updates from the server.
     */
    function onGameFinanceUpdate ( event ) {
        model.setBalance(event.data.balance);
        if ( !model.getGameInProgress() ) {
            updateFinanceFields();
        }
    }

    function onDialogResult ( event ) {
    }

    function onErrorDisplayed ( event ) {
    }

    function onModalDialogChange ( event ) {
    }

    function onOptionChange ( event ) {
    }

    function onOrientationChange ( event ) {
        if ( event.data.isPortrait() ) {

        } else if ( event.data.isLandscape() ) {

        }
    }

    // This method handles game setting change events from Game Services
    function onGameSettingChange ( event ) {
        switch ( event.data.id ) {
            case amaya.game.GameConstants.ID_CREDIT_VALUE:
                // Tell the model to set the credit value.
                model.setCreditValue( event.data.value );
                break;
            case amaya.game.GameConstants.ID_CREDITS_PER_LINE:
                // Update the model with the players new bet amount.
                model.setCreditsPerLine(event.data.value);
                break;
        }

        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }

    /**
     * Handles the event dispatched when the player clicks the Credit Value Increase button.
     */
    function onCreditValueIncrease ( event ) {
        // Tell the model to increase the credit value.
        model.increaseCreditValue();

        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }

    /**
     * Handles the event dispatched when the player clicks the Credit Value Decrease button.
     */
    function onCreditValueDecrease ( event ) {
        // Tell the model to decrease the credit value.
        model.decreaseCreditValue();

        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }

    function onCreditsPerLine ( event ) {
        // Once the user has started modifying the bet, hide any winning paylines that are being displayed
        // from the previous spin.
        paylineView.hideAllPaylineWins();

        // Update the model with the players new bet amount.
        model.setCreditsPerLine(event.data.creditsPerLine);

        // Update the betting ui so that the new bet amount is displayed.
        updateFinanceFields();
    }

    /** 
     * Handles the event dispatched when the player clicks the spin button.
     */
    function onSpin ( event ) {
        // Disable the ui so that the player cannot change the bet or click spin again.
        disableUI();

        // Validate to see if the player has satisfied all conditions required to spin.
        // 1. Has the player placed a bet?
        // 2. Does the player have enough money to bet?
        // If so, call the spin method.
        // If not, display a dialog with a message informing the player of the applicable
        // problem and re-enable the ui.
        if ( model.getBet() > 0 ) {
            if ( model.getBet() <= model.getCredits() ) {
                spin();
            } else {
                gameServices.showOkDialog("spinWarningLowCredits","You do not have enough credits to place bet.");
                enableUI();
            }
        } else {
            gameServices.showOkDialog("spinWarningMinBet","You must bet at least one credit on at least one line to spin.");
            enableUI();
        }
    }

    /**
     * Handles the event dispatched when the regular reel spin animation has completed.
     * Note: handler is only executed when regular spins complete. See onFreeSpinComplete for free spin handling.
     */
    function onSpinComplete ( event ) {
        // Highlight any stars collected on spin.
        reelView.highlightStarIcons();
        starsView.updateStarsTotal();

        // If spin has winning paylines, show the win amount and highlight the winning paylines
        if ( model.spinHasPaylineWins() ) {
            wonView.showWin();
            paylineView.showAllPaylineWins();
        }

        // Check to see if spin triggered free spins.
        // If so, transition to free spin mode.
        // If not, send the confirm hand end request to tell the server that the results have been displayed.
        if ( model.getFreeSpinsTriggered() ) {
            // If the auto spin system has spins remaining, they must be cancelled.
            if ( gameServices.autoSpin.hasSpins() ) {
                gameServices.autoSpin.cancel();
            }

            // Highlight the free spin icons
            reelView.highlightFreeSpinIcons();

            // Set the game state to free spin so that the next spin is executed as a free spin.
            model.setGameState(amaya.game.GameConstants.FREE_SPIN_STATE);

            // Play the bonus start sound
            soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_INTRO);

            // Notify the framework that the bonus round is starting.
            gameServices.notifyBonusGameStart();

            // Set a timer to start the first free spin.
            // At point, the UI is disabled so the player can't bet or spin.
            // Provided a few seconds for the player to see the wins from the trigger
            // spin and then start running the free spins.
            setTimeout( spin, 3500 );
        } else {
            model.setGameInProgress(false);

            // Send the confirm hand end request.
            var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
            gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());

            // Notify the Casino 5 framework that the game has now completed.
            // This is required by all games.
            gameServices.notifyGameEnd();
        }
    }

    /**
     * Handles the event dispatched when the free spin reel animation has completed.
     * Note: handler is only executed when free spins complete. See onSpinComplete for regular spin handling.
     */
    function onFreeSpinComplete ( event ) {
        // Highlight any stars collected on this spin.
        reelView.highlightStarIcons();
        starsView.updateStarsTotal();

        // Update the free spin counter in the free spin view.
        // Since we updated the "used" count at the start of the spin, this won't change either value
        // unless additional free spins were triggered (in which case, the total will update to include new
        // free spins won on this spin).
        freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());


        // If this spin has winning paylines, show the win amount and highlight the winning paylines
        if ( model.spinHasPaylineWins() ) {
            wonView.showWin();
            paylineView.showAllPaylineWins();
        }

        // Check to see if this spin triggered free spins.
        // If so, transition to free spin mode.
        // If not, send the confirm hand end request to tell the server that the results have been displayed.
        if ( model.getFreeSpinsTriggered() ) {
            // Highlight the free spin icons
            reelView.highlightFreeSpinIcons();
        }

        // If the current freeSpinsUsed count is less than the freeSpinsTotal count, then there are
        // additional free spins to execute. Otherwise, this was the last free spin and the game
        // should transition back to the regular spin state.
        if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) {
            // Provide a couple of seconds for the player to view any wins before starting the next spin.
            // Note, at this point, the game state is still in free spin mode and no confirm hand end should
            // be sent until all free spins have completed.
            setTimeout( spin, 2000 );
        } else {
            // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
            model.setGameState(amaya.game.GameConstants.SPIN_STATE);
            model.resetAfterFreeSpinBonus();

            model.setGameInProgress(false);

            // Hide the free spin background and counter.
            backgroundView.showMainSpinBackground();
            freeSpinView.hideCounter();

            // Play the bonus end sound
            soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_END);

            // Send the confirm hand end request to tell the server that the trigger spin and all free spins
            // have finished displaying. This takes the game out of a resumable state on the server.
            var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
            gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());

            // Notify the Casino 5 framework that the game has now completed.
            // This is required by all games.
            gameServices.notifyGameEnd();

            // Notify the framework that the bonus round is complete.
            gameServices.notifyBonusGameEnd();
        }
    }

    // Event handler that is called when the player initiates a new series of auto spins.
    // This event only occurs once at the start of the series.
    function onAutoSpinStart ( event ) {
        autoSpin();
    }


    // *** Private Methods *****************************************************************************************
    //
    /**
     * Controls starting of a new spin.
     */
    function spin () {
        // Remove any event listeners from the reel view.
        // A new one will be assigned depending on whether is a regular or free spin so that the correct
        // actions can be taken after the spin animation completes.
        reelView.removeEventListener(amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
        reelView.removeEventListener(amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
    
        // Reset the model. Not all values are reset, just those that need to be reinitialized between spins.
        model.resetForSpin();
    
        // Set a flag indicating the game is in progress. is used to control balance updating.
        model.setGameInProgress(true);
    
        // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
        paylineView.hideAllPaylineWins();
    
        // Check the current game state to determine if a regular spin or a free spin is being initiated.
        switch ( model.getGameState() ) {
    
            // Regular Spin
            case amaya.game.GameConstants.SPIN_STATE:
                // Clear the win field from the previous spin.
                wonView.clearWin();
    
                // Create and send a new SpinRequest object, passing the values required from the model.
                var spinRequest = new amaya.game.messaging.SpinRequest ( model.getCreditValue(), model.getCreditsPerLine() );
                gameServices.sendV2XMLRequest( spinRequest.getRequestData() );
    
                // Assign an event listener to act when the ReelView dispatches a spin complete event.
                // The onSpinComplete method handles the completion of regular spins.
                reelView.addEventListener(amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
    
                // Mark the time the spin starts.
                // Responsible Gaming regulations require that 3 seconds must elapse between the start of the one
                // regular spin and the start of the next. On completion of the spin, the time will be checked and
                // if 3 seconds has not elapsed, the ui will remain disabled until it has.
                // Note: See the handleConfirmHandEndResponse() event handler in class.
                model.setSpinStartTime(new Date().getTime());

                // Notify the Casino 5 framework that the game is now in progress.
                // This is required by all games.
                gameServices.notifyGameStart();
                break;
    
    
            // Free Spin
            case amaya.game.GameConstants.FREE_SPIN_STATE:
                // Turn on the free spin background and counter.
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();

                // Update the free spin counter in the free spin view.
                // Since we are starting the animation at the same time we send the request, the server won't tell
                // the client the new "used" count until the spin response is received (which is when the model
                // will be updated). creates an odd display for the player they would only see the used count
                // update as the spin is stopping instead of when it's starting. So code updates the counter to
                // show the used count + 1 now in anticipation of receiving that value from the server in when the
                // response comes in.
                freeSpinView.updateCounter(model.getFreeSpinsUsed() + 1,model.getFreeSpinsTotal());
    
                // Create and send a new FreeSpinRequest object, passing the values required from the model.
                var freeSpinRequest = new amaya.game.messaging.FreeSpinRequest( model.getFreeSpinsTotal(), model.getFreeSpinsUsed() );
                gameServices.sendV2XMLRequest(freeSpinRequest.getRequestData());
    
                // Assign an event listener to act when the ReelView dispatches a spin complete event.
                // The onFreeSpinComplete method handles the completion of free spins.
                reelView.addEventListener(amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);

                break;
        }
    
        // Start the spin animation.
        // In order to provide a seamless experience for the player, the spin animation is started at the
        // same time the request is sent to the server (as opposed to waiting for the response).
        // means the spin animation will have to loop endlessly while waiting for the response to arrive.
        // In example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
        // For more information on the animation, see the ReelView class.
        // An alternate design would be to have class call a method on the ReelView when the response arrives.
        reelView.spin();
    }
    
    /**
     * Enables the interactive controls.
     */
    function enableUI () {
        gameServices.showSpinButton();
        gameServices.addEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        updateFinanceFields();
        paylineView.enableUI();
    }
    
    /**
     * Disables the interactive controls
     */
    function disableUI () {
        gameServices.hideSpinButton();
        gameServices.removeEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        updateFinanceFields();
        paylineView.disableUI();
    }

    /**
     * Start the next auto spin.
     * Note, it is important that the GameServices.notifyGameStart is called *before* the GameServices.autoSpin.next is called.
     * In this example, the notifyGameStart method is called as part of the "spin()" function.
     */
    function autoSpin () {
        spin();
        gameServices.autoSpin.next();
    }

    function updateFinanceFields () {
        // Update the balance and wagered fields in the framework ui.
        //
        // Update the values in cash
        gameServices.setBalanceDisplay( model.getBalance() );
        gameServices.setWageredDisplay( model.getWager() );
        //
        // Update the values in credits
        gameServices.setCreditsDisplay( model.getCredits() );
        gameServices.setCreditsWageredDisplay( model.getBet() );
    }
    //
    // *************************************************************************************************************
    
    return instance;

};
