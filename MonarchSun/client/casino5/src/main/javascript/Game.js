goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.events");
goog.require("amaya.game.Model");
goog.require("amaya.game.Display");
goog.require("amaya.game.PaylineView");
goog.require("amaya.game.ReelView");
goog.require("amaya.game.CelebrationWindow");
goog.require("amaya.game.SoundManager");

goog.provide("amaya.game.Game");

amaya.game.Game = function () {

    var instance = {};

    var gameServices = new amaya.GameServices();
    var gameCanvasManager = new amaya.sdk.ui.canvas.CanvasManager();
    var soundSystem = new amaya.sdk.sound.SoundSystem();
    var model;
    var display;
    var backgroundView;
    var wonView;
    var reelView;
    var paylineView;
    var freeSpinView;
    var celebrationWindowView;
    var introView;
    var introTimeout;
    var bigWin = false;
    var spinIntegrator;
    var winAnimationIntegrator;
	var soundplay = false;
	var bonusTimeout;
	var spinTimeout;
	var freespinTime;
	var soundManager;
    var creditValues = [];
	var retriggerTimeOut;
	var autoTimer;
	var autoSpinHas = false;
	var payLineTimer;
	var stackPos =0;
	var scatFlag =0;
    var allWinDisplayTimer;
    var noInitFlag = false;
	var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");

   // var randomStartReelGrids = [[[6,5,6,3],[7,10,3,10],[5,8,12,1],[7,11,13,1],[1,8,11,1]],[[12,13,9,1],[6,10,2,1],[6,12,7,1],[4,9,6,1],[3,8,9,1]],[[10,8,6,1],[10,3,12,1],[9,13,11,1],[5,9,3,1],[6,11,13,1]],[[10,5,8,1],[6,10,4,1],[11,7,5,1],[9,6,12,1],[8,11,2,1]],[[11,6,9,1],[11,9,6,1],[3,7,10,1],[9,6,12,1],[3,10,5,1]]];

    var celebrationWindowTimer;

    amaya.sdk.singleplayer.V2Game.init( startMain, resumeMain );

    function startMain ( data, openingBalance ) {
        gameServices.notifyGameStart();
        main(openingBalance);

    }

    function resumeMain ( data, openingBalance ) {
        gameServices.notifyGameStart();
        noInitFlag = true;
        main(openingBalance);
        console.log("resume main");
        console.log(data);

        var resumeGameResponse = new amaya.game.messaging.ResumeGameResponse(data);
        model.setFlag(true);
        model.setGameInProgress(true);

        model.setCreditValue(resumeGameResponse.getCreditValue());
        model.setCreditsPerLine(resumeGameResponse.getCreditsPerLine());
        //model.setCreditsWon(resumeGameResponse.getCreditsWon()- resumeGameResponse.getScatterWin());
        model.setCreditsWon(resumeGameResponse.getCreditsWon());
        model.setReelStops(resumeGameResponse.getReelStops());
        model.setLinesWon(resumeGameResponse.getLinesWon());
        model.setWinningLines(resumeGameResponse.getWinningLines());
        // to get symbols and ways win
        model.setSymbolsWays(resumeGameResponse.getSymbolsAndWays());
        model.setWinningLineId(resumeGameResponse.getWinningLineId());

        model.setStreamStack(resumeGameResponse.getStreamingStack());//set streaming stack
        model.setAllPosition(resumeGameResponse.getAllPositions());//set positions stack for all wins
        model.setScatterAllPositions(resumeGameResponse.getScatterWays());//set  scatter positions stack for all wins

        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDIT_VALUE, resumeGameResponse.getCreditValue());
        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDITS_PER_LINE, resumeGameResponse.getCreditsPerLine());
        if (openingBalance >= model.getWon()) {
            if (resumeGameResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal()) {
                if (resumeGameResponse.getFreeSpinsUsed() === resumeGameResponse.getFreeSpinsTotal()) {
                    model.setBalance(openingBalance - model.getWon());
                }
                else {
                    model.setBalance(openingBalance);
                }
            }
            else {
                model.setBalance(openingBalance - model.getWon());
            }
        }
        else {
            model.setBalance(openingBalance);
        }
        updateFinanceFields();
        model.setOneWinAnimationCycleComplete(false);
        spinIntegrator.showStaticReels(model.getReelStops());
        //reelView.forceReels();

        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.

        if ( resumeGameResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            spinIntegrator.setReelSetIndexForFreeGame();


            if (resumeGameResponse.hasFreeSpinsTriggered()){
                model.setFreeSpinsTriggered(true);
                 model.setScatterWin(resumeGameResponse.getScatterWin());
            }
            model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(resumeGameResponse.getFreeSpinsUsed());
            model.setFreeSpinsWon (resumeGameResponse.getFreeSpinsWon());
            model.setScatterLength(resumeGameResponse.getScatterLength());
            freeSpinView.bonusTotal(resumeGameResponse.getFreeSpinsWon());

           /* this check is kept outside from following check of no. of free spins used
            so that NumFreeSpinsWon can be set in model for broken at just start of triggering free spins*/
            if (resumeGameResponse.hasFreeSpinsTriggered()){
               console.log("hasFreeSpinsTriggered setNumFreeSpinsWons");
             //  model.setNumFreeSpinsWon(amaya.game.GameConstants.NUM_FREESPINS_WON[resumeGameResponse.getNumScatterSymbols() - 1]);
             //  model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal());
              model.setNumFreeSpinsWon(resumeGameResponse.getScatterWays().freespins);
            }

            if ( resumeGameResponse.getFreeSpinsUsed() > 0 ) {
                soundManager.PlayFreeSpinSound();
                if (resumeGameResponse.hasFreeSpinsTriggered()){
                   // model.setNumFreeSpinsWon(amaya.game.GameConstants.NUM_FREESPINS_WON[resumeGameResponse.getNumScatterSymbols() - 1]);
                   // model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal() - model.getNumFreeSpinsWon());
                    model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal() - resumeGameResponse.getScatterWays().freespins);
                }
                model.setGameState(amaya.game.GameConstants.FREE_SPIN_STATE);
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();
                wonView.setFreePromoMsg();
                freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                model.setBaseReelStops(resumeGameResponse.getBaseReelStops());
                handleInitResponse(resumeGameResponse);
              //  onFreeSpinComplete( new ingenuity.gdk.events.SpinCompleteEvent() );
                console.log("execute");
                return;

            }

        }
         model.setBaseReelStops(resumeGameResponse.getReelStops());
         wonView.setPromoMsg();
         handleInitResponse(resumeGameResponse);
        //onSpinComplete( new ingenuity.gdk.events.SpinCompleteEvent() );


    }

    function main ( openingBalance ) {
        console.log("main");
        console.log(gameServices.profile.getCreditValues());
        // Create a new instance of GameServices to interact with the framework.
        // Game Services can be instantiated anywhere but since class will use it repeatedly, we'll assign it to a class member variable.
        gameServices = new amaya.GameServices();

        for (var i = 0; i < gameServices.profile.getCreditValues().length; i++) {
            var numToString = amaya.sdk.utils.NumberUtils.toShorthandFormat( gameServices.profile.getCreditValues()[i], gameServices.session.lang() );
            if (gameServices.profile.getCreditValues()[i] >= 1000) {
                creditValues[i] = gameServices.profile.getCurrencyCode() + numToString;
            }
            else {
                creditValues[i] = gameServices.profile.getCreditValues()[i];
            }
        }

        // Create a stepper control for selecting credit value
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDIT_VALUE,
            langFile.Credit_Value,
            creditValues,
            creditValues[0],
            true );

        // Create a stepper control for selecting credits per line
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDITS_PER_LINE,
            langFile.Credits_Per_Line,
            [1,2,3,4,5],
            1,
            false );

        // Create an autoplay control
        gameServices.gameSettings.addAutoSpinControl(
            amaya.game.GameConstants.ID_AUTO_SPIN,
            langFile.Auto_Spin );
        // The auto play control has additional events that must be integrated to make the auto play system function properly.
        gameServices.addEventListener( amaya.api.events.AutoSpinStartEvent.START, onAutoSpinStart );
        gameServices.addEventListener( amaya.api.events.AutoSpinStopEvent.STOP, onAutoSpinStop );

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

        //Sound Manger for play all sound
        soundManager = new amaya.game.SoundManager ( model, soundSystem );

        // Create View classes.
        // These classes are responsible for controlling the display of the ui graphics.
        // Each view is passed a reference to the ui (added to the display list above) and a reference to the model.
        //
        // Background View.
        backgroundView = new amaya.game.BackgroundView( display.backgroundContainer, display.logoContainer );
        backgroundView.showLogo();

        // Reel View - Controls the display of the reels (Spin and Icon animations).
        reelView = new amaya.game.ReelView( display.reelsContainer, display.symAnimContainer, display.stickyWildContainer, model, display.winboxContainer , display.reelStreamContainer, soundManager);


        // Payline View - Controls the display of the paylines.
        paylineView = new amaya.game.PaylineView( display.paylineContainer, display.winboxContainer, model, reelView );

        // Won View - Controls the display of the won field and sounds
        wonView = new amaya.game.WonView( display.wonContainer, model, soundManager, backgroundView );

        // Update the balance and wagered fields
        updateFinanceFields();

        // Stars View - Controls the display of the star icon accumulator.
//        starsView = new amaya.game.StarsView( display.starsContainer, model );

        // Free Spin View - Controls the display of the free spin ui. Note that the Reel View still displays the
        // free spin reel animations.
        freeSpinView = new amaya.game.FreeSpinView( display.freeSpinContainer, model );


        celebrationWindowView = new amaya.game.CelebrationWindow ( display.winCelebrationContainer, model, soundManager );

//        introView = new amaya.game.IntroView(display.introContainer);

        // Setup the common model for Ingenuity Mobile Engine
        var commonModel = ingenuity.gdk.IngenuitySlotLibrary.Common.CommonModel();
        commonModel.setReelIndexStartIndex(amaya.game.GameConstants.REEL_INDEX_START_INDEX);
        commonModel.setSymbolIndexStartIndex(amaya.game.GameConstants.SYMBOL_INDEX_START_INDEX);

        // Reel SpinIntegrator - integrates the ingenuity gaming reel spin module
        // setup the data for spin module into an object
        var spinData = { numReels:amaya.game.GameConstants.REELS, numDisplayIcons:amaya.game.GameConstants.ICONS,
            spacing:(gameServices.assets.getAsset("device/main.json")).icons.spacing, numReelSpinRows:amaya.game.GameConstants.NUM_REEL_SPIN_ROWS,
            numSymbols:amaya.game.GameConstants.NUM_SYMBOLS, symbolData:reelView.getSymbolData()
        };
        spinIntegrator = new ingenuity.gdk.Integrators.CJIntegrators.SpinIntegrator(reelView.getReelContainer(), new amaya.api.EventDispatcher(), commonModel);

/*
        // Reel spin configuration data
        var sym_height = spinData.spacing;
        var reelSpinConfigData = {};
        reelSpinConfigData.spin_max_speed = Math.round(22 * sym_height / 100);
        reelSpinConfigData.spin_speed_incr = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_speed_decr = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_min_speed = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_delay = 300;
        reelSpinConfigData.spin_reverse_time = Math.round(8 * sym_height / 100);
        reelSpinConfigData.spin_speed_reverse = Math.round(-2 * sym_height / 100);
        reelSpinConfigData.spin_first_reel_cont_time = 200;
        reelSpinConfigData.spin_anticipation_time = 1200;

        spinIntegrator.setReelSpinConfigurationData(reelSpinConfigData);
*/
        spinIntegrator.powerUpSpin(spinData);

        // Win Animation Integrator - integrates the ingenuity gaming win animation module
        var winAnimData = { spacing:(gameServices.assets.getAsset("device/main.json")).icons.spacing, symbolData:getSymbolAnimData(),
        numReels:amaya.game.GameConstants.REELS, numDisplayedReelIcons:amaya.game.GameConstants.ICONS};
        winAnimationIntegrator = new ingenuity.gdk.Integrators.CJIntegrators.WinAnimationIntegrator(reelView.getSymAnimContainer(), new amaya.api.EventDispatcher(), commonModel);
        winAnimationIntegrator.addEventListener(ingenuity.gdk.events.WinAnimationCycleCompleteEvent.WIN_ANIM_CYCLE_COMPLETED,onWinAnimCycleComplete);
        winAnimationIntegrator.addEventListener(ingenuity.gdk.events.LineAnimationStartEvent.LINE_ANIMATION_STARTED,onLineAnimStart);
        winAnimationIntegrator.powerupAnimations(winAnimData);


        // Send the init request to the server. request is used to get initial information from the server
        // to properly set up the game.

        if(!noInitFlag){
        var initReq = new amaya.game.messaging.InitRequest();
        gameServices.sendV2XMLRequest( initReq.getRequestData() );
        }
        else{noInitFlag = false;}
    }

    function onIncomingMessage ( event ) {
    		console.log(event.data.response);
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
        //Setup reels
       // console.log("Setup reels");

        // Record the reel strips received in the response. This game has the same reel strips for base and free games
        spinIntegrator.setReelStrips(initResponse.getReels(), initResponse.getFreeReels());
        if (model.getFlag()) {
            spinRestore();
            // set the flag to track whether a spin response has been received from the server
            spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());
            checkForAnticipation();
        }
        else {
            model.setReelStops(initResponse.getReelStops());
            // Intro View - Controls the display of the game's intro screen
            introView = new amaya.game.IntroView( display.introContainer);
            introView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeIntro );
            introTimeout= setTimeout(function() {removeIntro();}, 10000);

            // Play the intro sound
            soundManager.PlayIntro();
        }

        // When all setup is complete, remove the loader screen to reveal the game.
        gameServices.removeLoader();

    }

	function startBonusSound() {
	    soundplay = true;
	    //soundManager.PlayFreeSpinSound();
	}
	function startSpinSound() {
        soundManager.PlaySpinSound();
    }

    /**
     * Handles the regular Spin Response.
     * @param {amaya.game.messaging.SpinResponse} spinResponse
     */
    function handleSpinResponse ( spinResponse ) {
        // Record data from the response into the model
         model.setStreamStack(spinResponse.getStreamingStack());//set streaming stack
         model.setScatterWin(spinResponse.getScatterWin());
         // to get symbols and ways win
         model.setSymbolsWays(spinResponse.getSymbolsAndWays());
         model.setWinningLineId(spinResponse.getWinningLineId());

         //to get the sticky wild data
         //console.log('spinResponse.getstickyWilds()', spinResponse.getstickyWilds());
         model.setstickyWilds(spinResponse.getstickyWilds());

        //console.log("setScatterWin=="+spinResponse.getScatterWin());
        freeSpinView.bonusTotal(spinResponse.getFreeSpinsWon());
       // model.setCreditsWon(spinResponse.getCreditsWon() - spinResponse.getScatterWin());
       model.setCreditsWon(spinResponse.getCreditsWon());

        model.setReelStops(spinResponse.getReelStops());
        //console.log("Reel stops: ",model.getReelStops());
        model.setBaseReelStops(spinResponse.getReelStops());
        model.setLinesWon(spinResponse.getLinesWon());
        console.log("spinResponse.getWinningLines()",spinResponse.getWinningLines());
        model.setAllPosition(spinResponse.getAllPositions());
        model.setNumFreeSpinsWon(spinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal());
        model.setWinningLines(spinResponse.getWinningLines());
        //model.setFreeSpinsWon(spinResponse.getFreeSpinsWon());

        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.
        if ( spinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
            model.setFreeSpinsTotal(spinResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(spinResponse.getFreeSpinsUsed());
            model.setScatterLength(spinResponse.getScatterLength());

        }
        // set the flag to track whether a spin response has been received from the server
        spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());

        checkForAnticipation();
    }

    /** Handles the Free Spin response. */
    function handleFreeSpinResponse ( freeSpinResponse ) {
        // Record data from the response into the model.
        model.setStreamStack(freeSpinResponse.getStreamingStack());//set streaming stack
        model.setAllPosition(freeSpinResponse.getAllPositions());
        model.setCreditsWon(freeSpinResponse.getCreditsWon());
        model.setReelStops(freeSpinResponse.getReelStops());
        model.setLinesWon(freeSpinResponse.getLinesWon());
        model.setWinningLines(freeSpinResponse.getWinningLines());
       // to get symbols and ways win
        model.setSymbolsWays(freeSpinResponse.getSymbolsAndWays());
        model.setWinningLineId(freeSpinResponse.getWinningLineId());

        //to get the sticky wild data
         model.setstickyWilds(spinResponse.getstickyWilds());


        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then this free spin triggers additional free spins.
        // If so, set the triggered flag to true.
        if ( freeSpinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
        }
    
        // Record the updated free spin data into the model.
        model.setNumFreeSpinsWon(freeSpinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal());
         console.log("handleFreeSpinResponse_setNumFreeSpinsWon=="+(freeSpinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal()));
        model.setFreeSpinsTotal(freeSpinResponse.getFreeSpinsTotal() - model.getNumFreeSpinsWon());
        model.setFreeSpinsUsed(freeSpinResponse.getFreeSpinsUsed());
        model.setFreeSpinsWon(freeSpinResponse.getFreeSpinsWon());
        model.setScatterLength(freeSpinResponse.getScatterLength());

        // set the flag to track whether a spin response has been received from the server
        spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());

        checkForAnticipation();
    }

    /**
     * Handle the confirm hand end response.
     * Confirm hand end is a standard game message that tells the server that the results of the game
     * have been displayed to the player so the game is no longer in an unfinished (resumable) state.
     */
    function handleConfirmHandEndRequest () {
        model.setFlag(false);
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
                autoSpinHas = true;
                autoTimer = setTimeout(autoSpin,(model.spinHasPaylineWins()) ? 2000 : 500 );
            } else {
                enableUI();
               //wonView.startPromoMsg();
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

        soundSystem.stopAllSounds();

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


        wonView.clearWinMsg();
        model.setOneWinAnimationCycleComplete(false);
        // stop all displays related to win animations like symbol animations, payline animations, message displays
        stopWinDisplayForNextSpin();
        // Clear the win field from the previous spin.
        wonView.clearWin();
        // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
         paylineView.hideAllPaylineWins();

        switch ( event.data.id ) {
            case amaya.game.GameConstants.ID_CREDIT_VALUE:
                var index = creditValues.indexOf(event.data.value);
                // Tell the model to set the credit value.
                model.setCreditValue( gameServices.profile.getCreditValues()[index] );
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
		soundManager.PlaySpinButton();
        // Disable the ui so that the player cannot change the bet or click spin again.
        disableUI();

        // Validate to see if the player has satisfied all conditions required to spin.
        // 1. Has the player placed a bet?
        // 2. Does the player have enough money to bet?
        // If so, call the spin method.
        // If not, display a dialog with a message informing the player of the applicable
        // problem and re-enable the ui.
        setTimeout( function () {
            soundSystem.stopAllSounds();
        }, 200);
        if ( model.getBet() > 0 ) {
            if ( model.getBet() <= model.getCredits() ) {
                model.setBalance(model.getBalance() - model.getWager());
                updateFinanceFields();
                spin();
                console.log(" function onSpin");
                 wonView.showGoodLuckMsg();
                setTimeout( function () {
                    startSpinSound();
                }, 300);

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
       soundManager.StopSpinSound();
       console.log("On spin complete");
      // checkStreamingStack();
       // console.log("onSpinComplete()");
         wonView.clearWinMsg();
        // If spin has winning paylines, show the win amount and highlight the winning paylines
        if ( !model.getFreeSpinsTriggered() && model.spinHasPaylineWins() ) {
            if ((model.getCreditsWon()) >= 20*(model.getBet())) {
                //wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                checkBigWin();
               // wonView.showPreBigWin();
            }
            else {
                playBaseGameLineWins();
            }
        }

        if ( model.getFreeSpinsTriggered() ) {
            wonView.showWinMsg(0, model.getScatterWin(),11);
          //  reelView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
           // reelView.playScatterAnimation();
            soundManager.PlayWinAnim(amaya.game.GameConstants.SYM_SCATTER);
            wonView.clearPromoMsg();
            // Check to see if spin triggered free spins.
            // If so, transition to free spin mode.
            // If not, send the confirm hand end request to tell the server that the results have been displayed.
            // If there were base game spins then check once the win animations are complete for triggered free spins and play them
            //playFreeSpinIntro();

        } else if (!gameServices.autoSpin.hasSpins() || !model.spinHasPaylineWins()){
            if (! ((model.getCreditsWon()) >= 20*(model.getBet())) ) {
                  processGameEnd();
            }
        }
       // checkBigWin();
    }

    function processGameEnd() {
		if (model.getFreeSpinsHasWins()) {
			wonView.showWin();
			model.setFreeSpinsHasWins(false);
		}
        model.setGameInProgress(false);

        // Send the confirm hand end request.
        var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
        gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());

        // Notify the Casino 5 framework that the game has now completed.
        // This is required by all games.
        gameServices.notifyGameEnd();
        console.log("After game win update: ",model.getWon(), model.getNetWon());
        wonView.showBaseWinOnTrigger();
    }

    /**
     * Handles the event dispatched when the free spin reel animation has completed.
     * Note: handler is only executed when free spins complete. See onSpinComplete for regular spin handling.
     */
    function onFreeSpinComplete ( event ) {
        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("onFreeSpinComplete addEventListener skipAnimation");
           wonView.clearWinMsg();
        // Update the free spin counter in the free spin view.
        // Since we updated the "used" count at the start of the spin, this won't change either value
        // unless additional free spins were triggered (in which case, the total will update to include new
        // free spins won on this spin).
        if ( !model.getFreeSpinsTriggered() ) {
            if (!((model.getCreditsWon()) >= 20*(model.getBet()))) {
                freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                freeSpinView.showFreeWin(model.getFreeSpinsWon());
            }
        }

        // If this spin has winning paylines, show the win amount and highlight the winning paylines
        if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered() ) {
            if ((model.getCreditsWon()) >= 20*(model.getBet())) {
                display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                 console.log("onFreeSpinComplete  if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered() ) removeEventListener skipAnimation");
                //wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                checkBigWin();
               // wonView.showPreBigWin();
            }
            else {
                 if(model.getWinningLines().length > 1 ){
                    paylineView.showAllPosBg (model.getAllPosition().positions);
                    console.log("model.getAllPosition().positions",model.getAllPosition().positions);
                    /***to display  multiple wins  with all win symbols playing animations without winbox****/
                    winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
                    allWinDisplayTimer = setTimeout(function () {
                                          paylineView.hideAllPosBg();
                                          winAnimationIntegrator.stopAllWinAnimations();
                                          winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                                     }, 2500);
                }
                else{
                    winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                }
                wonView.showWin();
            }
        }
	    else if (model.spinHasPaylineWins() && model.getFreeSpinsTriggered()) {
            //display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
           // console.log("onFreeSpinComplete if (model.spinHasPaylineWins() && model.getFreeSpinsTriggered())  removeEventListener skipAnimation");
            winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
            wonView.showWin();
        }

/*        // Check to see if this spin triggered free spins.
        // If so, transition to free spin mode.
        // If not, send the confirm hand end request to tell the server that the results have been displayed.
        if ( model.getFreeSpinsTriggered() ) {
            // Freespin has been retriggered

            // If there were other payline wins then delay for some time to show those to the user.
            if (!((model.getCreditsWon()) >= 20*(model.getBet()))) {
                if (model.spinHasPaylineWins()) {
                    retriggerTimeOut = setTimeout (function () {
                        playFreeSpinRetrigger();
                    }, 2000);
                } else {
                    display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                    playFreeSpinRetrigger();
                }
            }
        }
*/
        // If the current freeSpinsUsed count is less than the freeSpinsTotal count, then there are
        // additional free spins to execute. Otherwise, this was the last free spin and the game
        // should transition back to the regular spin state.
        else if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) {
			display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
			//console.log("onFreeSpinComplete  if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) removeEventListener skipAnimation");
            // Provide a couple of seconds for the player to view any wins before starting the next spin.
            // Note, at this point, the game state is still in free spin mode and no confirm hand end should
            // be sent until all free spins have completed.
            freespinTime = setTimeout( spin, 2000 );
        } else {
            //console.log("ending freespins");
            if ( !model.spinHasPaylineWins() ) {
                display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                	//console.log("onFreeSpinComplete   if ( !model.spinHasPaylineWins() ) removeEventListener skipAnimation");
                playFreeSpinEnd();
            }
        }

    }

        /* Controls resuming of spin for broken game.
         */
        function spinRestore () {
           wonView.clearWinMsg();
                   model.setOneWinAnimationCycleComplete(false);
                   // stop all displays related to win animations like symbol animations, payline animations, message displays
                   stopWinDisplayForNextSpin();

                   // Remove any event listeners from the reel view.
                   // A new one will be assigned depending on whether is a regular or free spin so that the correct
                   // actions can be taken after the spin animation completes.
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);

                   // Reset the model. Not all values are reset, just those that need to be reinitialized between spins.
                  // model.resetForSpin();

                   // Set a flag indicating the game is in progress. is used to control balance updating.
                   model.setGameInProgress(true);

                   // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
                   paylineView.hideAllPaylineWins();

                   // Check the current game state to determine if a regular spin or a free spin is being initiated.
                   switch ( model.getGameState() ) {

                       // Regular Spin
                       case amaya.game.GameConstants.SPIN_STATE:
                            setTimeout( function () {
                                startSpinSound();
                            }, 300);
                          // console.debug("Initiating base spin");
                           // Clear the win field from the previous spin.
                           wonView.clearWin();
                          // wonView.clearPromoMsg();

                           // Assign an event listener to act when the ReelView dispatches a spin complete event.
                           // The onSpinComplete method handles the completion of regular spins.
                           spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                           spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);

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
                          // console.debug("Initiating free spin");
                           // Turn on the free spin background and counter.
                           backgroundView.showFreeSpinBackground();
                           freeSpinView.showCounter();
                           // Clear the win field from the previous spin.
                           wonView.clearWin();

                           // Update the free spin counter in the free spin view.
                           // Since we are starting the animation at the same time we send the request, the server won't tell
                           // the client the new "used" count until the spin response is received (which is when the model
                           // will be updated). creates an odd display for the player they would only see the used count
                           // update as the spin is stopping instead of when it's starting. So code updates the counter to
                           // show the used count + 1 now in anticipation of receiving that value from the server in when the
                           // response comes in.
                           freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());

                           // Assign an event listener to act when the ReelView dispatches a spin complete event.
                           // The onFreeSpinComplete method handles the completion of free spins.
                           spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                           spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                           break;
                   }

                   // Start the spin animation.
                   // In order to provide a seamless experience for the player, the spin animation is started at the
                   // same time the request is sent to the server (as opposed to waiting for the response).
                   // means the spin animation will have to loop endlessly while waiting for the response to arrive.
                   // In example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
                   // For more information on the animation, see the ReelView class.
                   // An alternate design would be to have class call a method on the ReelView when the response arrives.
           //        reelView.spin();
                   spinIntegrator.startReelSpin();
                   wonView.showGoodLuckMsg();
        }



    function playFreeSpinEnd () {

        wonView.clearFreePromoMsg();
        setTimeout ( function () {
            winAnimationIntegrator.stopAllWinAnimations();
            paylineView.hideAllPaylineWins();
            wonView.clearWinMsg();
        },100);
        // Set a timer to remove the freespin outro banner and then start spin.
        // Provided a few seconds for the player to see the freespin intro banner
       // Adding check to show banner only if there was any win for the free spins and else part is
       //duplicated from removeFreespinOutro to execute the required statements .

        model.setReelStops(model.getBaseReelStops());

        // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
        // model (Set above during model instantiation).
        spinIntegrator.showStaticReels(model.getReelStops());


        if(model.getFreeSpinsWon()>0){
            celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinOutro );
            //celebrationWindowView.playFreeSpinSummmary();
            celebrationWindowView.showFreeSpinOutro(model.getFreeSpinsWon(),model.getFreeSpinsTotal());

            soundManager.StopFreeSpinSound();
		    soundManager.PlayFreeOutroSound();

            freeSpinView.ClearFreeWin();
            wonView.clearWin();


            // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
            model.setGameState(amaya.game.GameConstants.SPIN_STATE);
            model.resetAfterFreeSpinBonus();

            spinIntegrator.setReelSetIndexForBaseGame();

            // Hide the free spin background and counter.
            backgroundView.showMainSpinBackground();
            freeSpinView.hideCounter();

        }
        else{
            soundManager.StopFreeSpinSound();
            clearTimeout(bonusTimeout);

	    if (model.getLinesWon() > 0) {
                freeSpinView.ClearFreeWin();


                // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
                model.setGameState(amaya.game.GameConstants.SPIN_STATE);
                model.resetAfterFreeSpinBonus();

                spinIntegrator.setReelSetIndexForBaseGame();

                // Hide the free spin background and counter.
                backgroundView.showMainSpinBackground();
                freeSpinView.hideCounter();
                wonView.clearWinMsg();

                model.setFreeSpinsHasWins(true);

                model.setCreditsWon(model.getLinesWon());

                if (((model.getCreditsWon()) >= 20*(model.getBet())) ) {
                    setTimeout( function () {
                     //   wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                     checkBigWin();
                     //   wonView.showPreBigWin();
                    }, 50);
                }
                else {
                    processGameEnd();
                }
            }
            else {

            wonView.setPromoMsg();


            // Send the confirm hand end request to tell the server that the trigger spin and all free spins
            // have finished displaying. This takes the game out of a resumable state on the server.
            var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
            gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());

            // Notify the Casino 5 framework that the game has now completed.
            // This is required by all games.
            gameServices.notifyGameEnd();
            console.log("After game win update: ",model.getWon(), model.getNetWon());
            wonView.showBaseWinOnTrigger();

        freeSpinView.ClearFreeWin();
        wonView.clearWin();


        // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
        model.setGameState(amaya.game.GameConstants.SPIN_STATE);
        model.resetAfterFreeSpinBonus();

        model.setGameInProgress(false);

        spinIntegrator.setReelSetIndexForBaseGame();

            // Hide the free spin background and counter.
            backgroundView.showMainSpinBackground();
            freeSpinView.hideCounter();
            wonView.clearWinMsg();
	    }
        }

        // Play the bonus end sound
        //soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_END);
    }

    // Event handler that is called when the player initiates a new series of auto spins.
    // This event only occurs once at the start of the series.
    function onAutoSpinStart ( event ) {
		soundManager.PlaySpinButton();
        autoSpin();
    }

    function onAutoSpinStop ( event ) {
        clearTimeout(autoTimer);
        clearTimeout(payLineTimer);
	if ( model.getBet() <= model.getCredits() ) {
	        if (!model.getGameInProgress()) {
	            if (!model.getFreeSpinsTriggered() && model.spinHasPaylineWins()) {
	                winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
	            }
	            enableUI();
	            gameServices.notifyGameEnd();
	        }
	}
        else if (autoSpinHas) {
            autoSpinHas = false;
            if (!model.getGameInProgress()) {
                if (!model.getFreeSpinsTriggered() && model.spinHasPaylineWins()) {
                    winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                }
                enableUI();
                gameServices.notifyGameEnd();
            }
        }
        else {
            wonView.clearWinMsg();
            model.setOneWinAnimationCycleComplete(false);
            // stop all displays related to win animations like symbol animations, payline animations, message displays
            stopWinDisplayForNextSpin();
            // Clear the win field from the previous spin.
            wonView.clearWin();
            // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
            paylineView.hideAllPaylineWins();
        }
	
		soundManager.PlaySpinButton();
    }

    function checkStreamingStack(id){

       var stack =[];
       var reelID = ['reel2','reel3','reel4'];
       stack = model.getStreamStack();

      // for(i=0;i<stack.length;i++){
      if(!(id==0 || id==4)){
            if(stack[stackPos]==3)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
            else if(stack[stackPos]==4)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
            else if(stack[stackPos]==1)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
          stackPos++;
          if(stackPos==3){stackPos=0;}
       }
    }
    // *** Private Methods *****************************************************************************************
    //
    /**
     * Controls starting of a new spin.
     */
    function spin () {

        // check if 1 cycle of win animation is complete before spinning again
//        if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && !model.hasOneWinAnimationCycleCompleted() && !model.getFreeSpinsTriggered()) {
//            setTimeout( spin, 1000 );
//            return;
//        }

        wonView.clearWinMsg();
        model.setOneWinAnimationCycleComplete(false);
        // stop all displays related to win animations like symbol animations, payline animations, message displays
        stopWinDisplayForNextSpin();

        // Remove any event listeners from the reel view.
        // A new one will be assigned depending on whether is a regular or free spin so that the correct
        // actions can be taken after the spin animation completes.
        spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
        spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
        spinIntegrator.removeEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("spin () removeEventListener skipAnimation");


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
               // console.debug("Initiating base spin");
                // Clear the win field from the previous spin.
                wonView.clearWin();
               // wonView.clearPromoMsg();
                // Create and send a new SpinRequest object, passing the values required from the model.
                var spinRequest = new amaya.game.messaging.SpinRequest ( model.getCreditValue(), model.getCreditsPerLine() );
                gameServices.sendV2XMLRequest( spinRequest.getRequestData() );
    
                // Assign an event listener to act when the ReelView dispatches a spin complete event.
                // The onSpinComplete method handles the completion of regular spins.
                spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);

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
               // console.debug("Initiating free spin");
                // Turn on the free spin background and counter.
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();
                // Clear the win field from the previous spin.
                wonView.clearWin();

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
                spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                break;
        }
    
        // Start the spin animation.
        // In order to provide a seamless experience for the player, the spin animation is started at the
        // same time the request is sent to the server (as opposed to waiting for the response).
        // means the spin animation will have to loop endlessly while waiting for the response to arrive.
        // In example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
        // For more information on the animation, see the ReelView class.
        // An alternate design would be to have class call a method on the ReelView when the response arrives.
//        reelView.spin();
        spinIntegrator.startReelSpin();
        wonView.showGoodLuckMsg();

    }
    
    /**
     * Enables the interactive controls.
     */
    function enableUI () {
        gameServices.showSpinButton();
        gameServices.addEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        updateFinanceFields();
     //        paylineView.enableUI();
    }
    
    /**
     * Disables the interactive controls
     */
    function disableUI () {
        gameServices.hideSpinButton();
        gameServices.removeEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        
        updateFinanceFields();
//        paylineView.disableUI();
    }

    /**
     * Start the next auto spin.
     * Note, it is important that the GameServices.notifyGameStart is called *before* the GameServices.autoSpin.next is called.
     * In this example, the notifyGameStart method is called as part of the "spin()" function.
     */
    function autoSpin () {
       // spin();
       autoSpinHas = false;
        if ( model.getBet() > 0 ) {
                    if ( model.getBet() <= model.getCredits() ) {
                        spin();
                        model.setBalance(model.getBalance() - model.getWager());
                        updateFinanceFields();
                        wonView.showGoodLuckMsg();
                        startSpinSound();
                      gameServices.autoSpin.next();

                    } else {
                        gameServices.showOkDialog("spinWarningLowCredits","You do not have enough credits to place bet.");
                        gameServices.autoSpin.cancel();
                        gameServices.notifyGameEnd();
                        enableUI();
                    }
                } else {
                    gameServices.showOkDialog("spinWarningMinBet","You must bet at least one credit on at least one line to spin.");
                     gameServices.autoSpin.cancel();
                     gameServices.notifyGameEnd();
                    enableUI();
                }

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

    function getSymbolAnimData () {
        var symbolData = [];
        for (var i = 0; i < amaya.game.GameConstants.NUM_SYMBOLS+1; i++) {
            var animData = getAnimation(i);
            if (animData.image != undefined) {
                animData.images = [gameServices.assets.getAsset(animData.image)];
            }
            symbolData.push(animData);
        }

        return symbolData;
    }

    /**
     * @Description Function to get the animation data
     */
    function getAnimation ( id ) {
        var json = gameServices.assets.getAsset("device/main.json");
        switch ( id ) {
            case 1:
                return json.sym1Data;
                break;
            case 2:
                return json.sym2Data;
                break;
            case 3:
                return json.sym3Data;
                break;
            case 4:
                return json.sym4Data;
                break;
            case 5:
                return json.sym5Data;
                break;
            case 6:
                return json.sym6Data;
                break;
            case 7:
                return json.sym7Data;
                break;
            case 8:
                return json.sym8Data;
                break;
            case 9:
                return json.sym9Data;
                break;
            case 10:
                return json.sym10Data;
                break;
            case 11:
                return json.scatterData;
                break;

            default:
                return json.blankData;
                break;

        }

    }

    function onWinAnimCycleComplete ( event ) {
        console.debug("onWinAnimCycleComplete");
        if (model.hasOneWinAnimationCycleCompleted() === false) {
//            console.log("1st Win anim cycle complete");
            // This is to ensure that these statements are triggered only once after a win animation cycle completion
            // since this callback will be called every time a win cycle completes in a win animation round but since
            // we will set the flag to true in the first call this part will not be called in the nezt call backs.

            // call method to display the promo banner

            // Check if freespin was triggered and call the freespin play method
            if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE) {

                if ( model.getFreeSpinsTriggered() && model.getFreeSpinsUsed() > 0) {
        			display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        			console.log("onWinAnimCycleComplete () removeEventListener skipAnimation");

                    // Freespin has been retriggered

                    retriggerTimeOut = setTimeout (function () {
                        stopWinDisplayForNextSpin();
                        playFreeSpinRetrigger();
                    }, 100);
                }

                else if (model.getFreeSpinsUsed() < model.getFreeSpinsTotal()) {
                    setTimeout(spin, 100);
                    wonView.showGoodLuckMsg();
                }
                else {
                    playFreeSpinEnd();
                }
            } /*else if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE && model.getFreeSpinsTotal() === model.getFreeSpinsUsed()) {
                console.log("last free spin completed");
                // This was the last freespin
                playFreeSpinEnd();
            }*/ else if (model.getGameState() === amaya.game.GameConstants.SPIN_STATE && !gameServices.autoSpin.hasSpins()) {
                console.debug("Process game end");
                //processGameEnd();
            }
        }
        model.setOneWinAnimationCycleComplete(true);
        paylineView.hideAllPaylineWins();
    }

    function onLineAnimStart ( event ) {
        console.log("Line anim start:"+event.data.id);
        paylineView.hideAllPaylineWins();
        if (event.data.id === 0)
            paylineView.showScatterBg(event.data.positions);
        else
            paylineView.showPaylineWin(event.data.id,event.data.positions);

        // call message to display the message in the message box
         wonView.showWinMsg( event.data.id,event.data.won,event.data.icon);

        // play the sound for the icon --- event.data.icon
        if (!model.hasOneWinAnimationCycleCompleted())  {
                 soundManager.PlayWinAnim(event.data.icon);
        }
    }

    function onReelStopped ( event ) {
        // Play reel stop sound

         checkStreamingStack(event.data);
        checkAndPlayScatterAppearSound(event.data);
        if (event.data == 3 && !model.getScatterHitSound(event.data)) {
                    reelView.stopReelAnticipation();
                    reelView.stopScatterAnimation();
                    paylineView.hideAllPosBg();
                    scatFlag=0;
        }
        if (event.data == 2 && model.getstickyWilds().length) {
            reelView.playStickyWildAnim();
        }
        soundManager.PlayReelStopSound();
    }

    function checkAndPlayScatterAppearSound(id) {
        if (model.getScatterHitSound(id)) {
            switch (id) {

                case 1:
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_02();
                        reelView.playScatterHalfAnim(id);
                    },200);
                    scatFlag++;
                    break;
                case 2:
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_03();
                        reelView.playScatterHalfAnim(id);
                    },200);
                    reelView.playReelAnticipation();
                    scatFlag++;
                    break;
                case 3:
                    reelView.stopScatterAnimation();
                    paylineView.hideAllPosBg();
                    reelView.stopReelAnticipation();
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_04();
                        if(model.getGameState() == amaya.game.GameConstants.SPIN_STATE){
                            reelView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
                        }
                        reelView.playScatterAnimation();
                    },200);
                    break;

            }
         }
	  else if(scatFlag==1){
	  	 reelView.stopScatterAnimation();
            paylineView.hideAllPosBg();
	  }
    }


    /*
     * @Description stop all displays related to win animations like symbol animations, payline animations, message displays
     */
    function stopWinDisplayForNextSpin() {
        // Stop all animations playing if any
        winAnimationIntegrator.stopAllWinAnimations();
        paylineView.hideAllPaylineWins();
        clearTimeout(allWinDisplayTimer);
    }

    function playFreeSpinRetrigger() {
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("playFreeSpinRetrigger () removeEventListener skipAnimation");
        celebrationWindowTimer = setTimeout( function () {
            removeFreespinRetrigCelebration();
        }, amaya.game.GameConstants.CELEBRATION_WINDOW_TIMER );
        //celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinRetrigCelebration );
        celebrationWindowView.showFreeSpinRetrigged(model.getNumFreeSpinsWon());

		soundManager.PlayFreeRetriggSound();

    }

    function playFreeSpinIntro () {
        reelView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
        stopWinDisplayForNextSpin();

        // If the auto spin system has spins remaining, they must be cancelled.
       // if ( gameServices.autoSpin.hasSpins() ) {
            gameServices.autoSpin.cancel();
        //}

        // Highlight the free spin icons
//            reelView.highlightFreeSpinIcons();

        // Set the game state to free spin so that the next spin is executed as a free spin.
        model.setGameState(amaya.game.GameConstants.FREE_SPIN_STATE);
        spinIntegrator.setReelSetIndexForFreeGame();

        // Set a timer to remove the freespin intro banner and then start spin.
        // At point, the UI is disabled so the player can't bet or spin.
        // Provided a few seconds for the player to see the freespin intro banner
        // spin and then start running the free spins.
        celebrationWindowTimer = setTimeout( function () {
            // Turn on the free spin background.
            backgroundView.showFreeSpinBackground();

            //removeFreespinIntro();
            // Play the bonus start sound
            // Play the free game sound
           //soundManager.PlayFreeSpinSound();

            //playBaseGameLineWinsAfterTrigger();

        }, 2000 );

        // Play the fire sound
         //soundManager.PlayReelFireSound();


        //soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_INTRO);

        celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinIntro );
     //   celebrationWindowView.playFireWolfAnimOnReel();
        celebrationWindowView.showFreeSpinIntro(model.getFreeSpinsTotal());

    }

    function playBaseGameLineWins () {
       // console.log("Line win has occured");
        if (! ((model.getCreditsWon()) >= 20*(model.getBet())) ) {
            checkBigWin();
        }
        if ( gameServices.autoSpin.hasSpins() ) {
          if(model.getWinningLines().length > 1){
                          paylineView.showAllPosBg (model.getAllPosition().positions);
                          /***to display  multiple wins  with all win symbols playing animations without winbox****/
                          winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
           }
           else{
                   winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.SHORT_ANIM_MODE);
           }
            wonView.showWinMsgInAutoPlay(model.getWinningLines().length, model.getCreditsWon(),model.getWinningLines()[0].icon );
//            winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.SHORT_ANIM_MODE);
            processGameEnd();
            payLineTimer = setTimeout(function () {
                paylineView.hideAllPaylineWins();
                winAnimationIntegrator.stopAllWinAnimations();

            }, 2000);
        } else {
            if(model.getWinningLines().length > 1){
                paylineView.showAllPosBg (model.getAllPosition().positions);
                /***to display  multiple wins  with all win symbols playing animations without winbox****/
                winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
                wonView.showAllCreditsWon(model.getCreditsWon());
                allWinDisplayTimer = setTimeout(function () {
                                      paylineView.hideAllPosBg();
                                      winAnimationIntegrator.stopAllWinAnimations();
                                      winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                                 }, 2500);
            }
            else{
                winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
            }
        }

        wonView.showWin();
   //     console.log("inside playBaseGameLineWins");
//        paylineView.showAllPaylineWins();
    }

    function playBaseGameLineWinsAfterTrigger () {
        console.debug("playBaseGameLineWinsAfterTrigger");
        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);

        //wonView.showBaseWinOnTrigger();
        //wonView.showScatterWinOnFSScreenMeter();
//        paylineView.showAllPaylineWins();
    }

    function removeFreespinIntro () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
//        clearTimeout(celebrationWindowTimer);

        celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinIntro);
        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);

        freeSpinView.showCounter();

        // Update the free spin counter in the free spin view.
        // Since we are starting the animation at the same time we send the request, the server won't tell
        // the client the new "used" count until the spin response is received (which is when the model
        // will be updated). creates an odd display for the player they would only see the used count
        // update as the spin is stopping instead of when it's starting. So code updates the counter to
        // show the used count + 1 now in anticipation of receiving that value from the server in when the
        // response comes in.
        freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());

        //reelView.stopFireAnimOnReel();
        //soundManager.StopReelFireSound();

         wonView.setFreePromoMsg();

//        celebrationWindowView.hideFreeSpinIntro();

        playBaseGameLineWinsAfterTrigger();
    }

    function removeFreespinOutro () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
        clearTimeout(celebrationWindowTimer);
      //  soundManager.PlayFreeOutroSound();
        soundManager.StopFreeSpinSound();
        clearTimeout(bonusTimeout);


        celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinOutro);
        //celebrationWindowView.hideFreeSpinOutro();

        wonView.setPromoMsg();

        model.setFreeSpinsHasWins(true);

        model.setCreditsWon(model.getLinesWon());

        if (((model.getCreditsWon()) >= 20*(model.getBet())) ) {
            setTimeout( function () {
               // wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
               checkBigWin();
               // wonView.showPreBigWin();
            }, 50);
        }
        else {
            processGameEnd();
        }
    }

    function removeFreespinRetrigCelebration () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
        clearTimeout(celebrationWindowTimer);

		//soundManager.PlayFreeOutroSound();

        //celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinRetrigCelebration );
        celebrationWindowView.hideFreeSpinRetriggered();

        // update the number of total freespins won
        model.setFreeSpinsTotal(model.getFreeSpinsTotal() + model.getNumFreeSpinsWon());

        freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
        freeSpinView.showFreeWin(model.getFreeSpinsWon());

        // start the next free spin.
        spin();
       // console.log(" removeFreespinRetrigCelebration Spin()");
        wonView.showGoodLuckMsg();

    }

    function removeIntro() {
        gameServices.notifyGameEnd();
        clearTimeout(introTimeout);
         soundManager.StopIntroSound();

        // A set of fake reel stops are created to be displayed on the reels when the game starts.
        // For gaming authority certification purposes, the stops displayed when the game begins must be a real,
        // achievable result. In other words, the player must be able to get result by playing the game.
        //var random_stop_position = Math.floor(Math.random() * randomStartReelGrids.length);

        // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
        // model (Set above during model instantiation).
        spinIntegrator.showStaticReels(model.getReelStops());
        paylineView.hideAllPaylineWins();

    //paylineView.showPaylineWin(20,0,0);

     //   freeSpinView.showCounter();
    //   freeSpinView.updateCounter(15,25);
     //   freeSpinView.bonusTotal(200);
      //  celebrationWindowView.showFreeSpinRetrigged(50);
 // celebrationWindowView.showFreeSpinOutro(500,5);
  //reelView.playFireAnimOnReel();
         //wonView.showWinMsg(0,400000);
      //
     //celebrationWindowView.showFreeSpinIntro(25);

     //celebrationWindowView.playFireWolfAnimOnReel();

     //celebrationWindowView.playFreeSpinSummmary();
    //celebrationWindowView.playFireWolfAnimOnReel();

        introView.hideIntro();

        // Enable the game ui
        enableUI();

       // wonView.startPromoMsg();
       wonView.setPromoMsg();

    }

    function checkForAnticipation () {
        var grid = model.getReelStops();
        var scatter = 0;
        var anticipationData = [0,0,0,0,0];
        var scatterHitSound = [0,0,0,0,0];
        for (var i = 1; i < amaya.game.GameConstants.REELS-1; i++) {
            for (var j = 0; j < amaya.game.GameConstants.ICONS; j++) {
                if (grid[i][j] === amaya.game.GameConstants.SYM_SCATTER) {
                    scatter++;
                    if ( i === 1 && j === (amaya.game.GameConstants.ICONS - 1) && scatter === 0) {
                        // If no scatter was encountered on second reel then return
                        spinIntegrator.setAnticipationSpin([0,0,0,0,0]);
                        model.setScatterHitSound([0,0,0,0,0]);
                        console.log("no anticipation data set");
                        return;
                    }
                    scatterHitSound[i] = 1;
                    if (scatter == 2) {
                        anticipationData[i+1] = 1;
                    }
                } else if (j === (amaya.game.GameConstants.ICONS - 1) && scatter < (i)) {
                    model.setScatterHitSound(scatterHitSound);
                    spinIntegrator.setAnticipationSpin(anticipationData);
                    console.log("anticipation data set "+anticipationData);
                    return;
                }
            }
        }
        model.setScatterHitSound(scatterHitSound);
        spinIntegrator.setAnticipationSpin(anticipationData);
    }

    //Skip animation when tap on screen
    function skipAnimation () {
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
       // console.log(" function skipAnimation () removeEventListener skipAnimation");
        if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) {
            if (model.getFreeSpinsUsed() < model.getFreeSpinsTotal()) {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    console.log("Skip Animation");
                    clearTimeout(freespinTime);
                    freeSpinView.ShowFreeWinAfterSkip();
                    spin();
                   // console.log(" Skip Animation Spin()");

                }
            }
            else {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    clearTimeout(freespinTime);
                    freeSpinView.ShowFreeWinAfterSkip();
                    playFreeSpinEnd();
                }
            }
        }
        else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && model.getFreeSpinsTriggered()) {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    if (model.getFreeSpinsUsed() > 0) {
                        model.setOneWinAnimationCycleComplete(true);
                        stopWinDisplayForNextSpin();
                        playFreeSpinRetrigger();
                    }
                    else {
                        spin();
                      //   console.log("Skip Animation  else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN  Spin()");
                    }
                }
        }
        else if ((model.getCreditsWon()) >= 20*(model.getBet()) && model.getGameState() == amaya.game.GameConstants.SPIN_STATE){
            if (bigWin) {
              bigWin = false;
              wonView.clearBigwinMsg();
            }
        }
    }

    /** Check if total credits won after spin is a BIG WIN or not **/
    	function checkBigWin(){
    	    if ((model.getCreditsWon()) >= 20*(model.getBet())){
    	        if(model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE){
                     soundManager.StopFreeSpinSound();
                }
               // wonView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
               // console.log(" function checkBigWin () addEventListener skipAnimation");
                wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playGameAfterBigWin);
                gameServices.autoSpin.cancel();
                wonView.showBigwinMsg();
                bigWin = true;
            }

    	    else if ((model.getCreditsWon()) >= 10*(model.getBet())){
                gameServices.autoSpin.cancel();
              //  gameServices.notifyGameEnd();
                //console.log("big win");
            }

       	}

    //Below function is for play Animation and win after big win
       	function playGameAfterBigWin () {
            display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
           // console.log(" function playGameAfterBigWin () removeEventListener skipAnimation");
            wonView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, playGameAfterBigWin);
            if (model.getGameState() == amaya.game.GameConstants.SPIN_STATE) {
                if (!model.getFreeSpinsHasWins()) {
                    playBaseGameLineWins();
                }
                processGameEnd();
            }
            else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE) {
                soundManager.PlayFreeSpinSound();
                if ( !model.getFreeSpinsTriggered() ) {
                    freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                    freeSpinView.showFreeWin(model.getFreeSpinsWon());
                }
                if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) {
                    if (bigWin) {
                        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                     //   console.log(" function playGameAfterBigWin ()  if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) addEventListener skipAnimation");
                        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
                        wonView.showWin();
                    }
                }
                /*if ( model.getFreeSpinsTriggered() ) {
                    if (bigWin) {
                        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
                        wonView.showWin();
                    } else {
                        if (model.getFreeSpinsUsed() > 0) {
                            playFreeSpinRetrigger();
                        }
                        else {
                            spin();
                        }
                    }
                }*/
                if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) {
                    if (!bigWin) {
                        clearTimeout(freespinTime);
                        spin();
                      //   console.log(" function playGameAfterBigWin () if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) spin()");
                    }
                } else {
                    if (!bigWin) {
                        playFreeSpinEnd();
                    }
                }
            }
            bigWin = false;
       	}

    return instance;

};
