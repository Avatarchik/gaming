package com.cwh.sampleslotgame {
    import com.cwh.casino4.api.GameServices;
    import com.cwh.casino4.api.events.GameFinanceUpdateEvent;
    import com.cwh.casino4.api.events.IncomingMessageEvent;
    import com.cwh.casino4.sdk.singleplayer.game.V2Game;
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.constants.MessagingConstants;
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.request.V2ConfirmHandEndRequest;
    import com.cwh.sampleslotgame.constants.AssetConstants;
    import com.cwh.sampleslotgame.constants.GameConstants;
    import com.cwh.sampleslotgame.constants.SoundConstants;
    import com.cwh.sampleslotgame.events.CreditValueDecreaseEvent;
    import com.cwh.sampleslotgame.events.CreditValueIncreaseEvent;
    import com.cwh.sampleslotgame.events.CreditsPerLineEvent;
    import com.cwh.sampleslotgame.events.SpinCompleteEvent;
    import com.cwh.sampleslotgame.events.SpinEvent;
    import com.cwh.sampleslotgame.messaging.constants.MessagingConstants;
    import com.cwh.sampleslotgame.messaging.request.FreeSpinRequest;
    import com.cwh.sampleslotgame.messaging.request.InitRequest;
    import com.cwh.sampleslotgame.messaging.request.SpinRequest;
    import com.cwh.sampleslotgame.messaging.response.FreeSpinResponse;
    import com.cwh.sampleslotgame.messaging.response.InitResponse;
import com.cwh.sampleslotgame.messaging.response.ResumeGameResponse;
import com.cwh.sampleslotgame.messaging.response.SpinResponse;
    import com.cwh.sampleslotgame.model.MainScreenModel;
    import com.cwh.sampleslotgame.view.BettingUIView;
    import com.cwh.sampleslotgame.view.FreeSpinView;
    import com.cwh.sampleslotgame.view.PaylineView;
    import com.cwh.sampleslotgame.view.ReelView;
    import com.cwh.sampleslotgame.view.StarsView;

    import flash.display.MovieClip;
    import flash.utils.getTimer;
    import flash.utils.setTimeout;

    /**
     * Game.as is the main controller for the game. This class is compiled as the main swf file for the game.
     *
     * Note that this class inherits from V2Game which is a partial implementation of a V2 Game developed by Chartwell
     * as a means of making game development simpler. This class handles performing the mandatory start up messaging
     * sequence. For more information, refer to the documentation of the Core SDK Library.
     *
     * In the MVC design pattern, this class performs the function of the controller. It is responsible for the business
     * logic of the game. It handles communicating with the server, updating the model, instructing the views to update
     * at the appropriate time, and handles user input events captured by the view. Usage of this pattern is recommended
     * but is not required.
     */
    public class Game extends V2Game {

        // *** Class Member Variables **********************************************************************************
        //
        /** This object is used to communicate between the game client and the Casino 4.0 framework. */
        private var gameServices:GameServices;

        // Model: Stores the data that defines the current state of the game.
        /** Stores the data that defines the current state of the game. */
        private var mainScreenModel:MainScreenModel;

        // Views: Objects that are responsible for directly working with the display of the game.
        /** Controls the display of the reels and icons */
        private var reelView:ReelView;

        /** Controls the display of the paylines */
        private var paylineView:PaylineView;

        /** Controls the display of the betting ui */
        private var bettingUIView:BettingUIView;

        /** Controls the display of the stars accumulator display */
        private var starsView:StarsView;

        /** Controls the display of the free spin displays */
        private var freeSpinView:FreeSpinView;
        //
        // *************************************************************************************************************



        // *** Constructor *********************************************************************************************
        //
        /**
         * When extending from V2Game, it is recommended to avoid using the constructor for setup.
         * This is because the super class has asynchronous tasks to perform before the game is ready to be played.
         * Use the startMain and resumeMain methods below as the initialization point of the game.
         */
        public function Game () {}
        //
        // *************************************************************************************************************



        // *** Main Methods: Game Initialization ***********************************************************************
        //
        /**
         * This method is called when the framework has finished setting up a new game.
         * At this point, control is turned over to this class.
         */
        override protected function startMain ( startGameResponseData:XML, openingBalance:Number ) : void {
            this.main(openingBalance);
        }

        /**
         * This method is called when the framework has finished setting up a resumed game.
         * At this point, control is turned over to this class.
         */
        override protected function resumeMain ( resumeGameResponseData:XML, openingBalance:Number ) : void {
            this.main(openingBalance);

            var resumeGameResponse:ResumeGameResponse = new ResumeGameResponse(resumeGameResponseData);

            this.mainScreenModel.creditValue = resumeGameResponse.CreditValue;
            this.mainScreenModel.creditsPerLine = resumeGameResponse.CreditsPerLine;
            this.mainScreenModel.won = resumeGameResponse.Won;
            this.mainScreenModel.reelStops = resumeGameResponse.ReelStops;
            this.mainScreenModel.linesWon = resumeGameResponse.LinesWon;
            this.mainScreenModel.winningLines = resumeGameResponse.WinningLines;
            this.mainScreenModel.starsTotal = resumeGameResponse.StarsTotal;

            this.bettingUIView.updateBettingUI();
            this.reelView.forceReels();

            // If the total number of free spins in the response is greater than the number of free spins
            // currently in the model, then spin triggers free spins.
            // If so, record the free spin data and set the triggered flag to true.
            if ( resumeGameResponse.FreeSpinsTotal > mainScreenModel.freeSpinsTotal ) {
                this.mainScreenModel.freeSpinsTriggered = true;
                this.mainScreenModel.freeSpinsTotal = resumeGameResponse.FreeSpinsTotal;
                this.mainScreenModel.freeSpinsUsed = resumeGameResponse.FreeSpinsUsed;

                if ( resumeGameResponse.FreeSpinsUsed > 0 ) {
                    this.freeSpinView.showFreeSpinBackground();
                    this.freeSpinView.showCounter();
                    this.freeSpinView.updateCounter(mainScreenModel.freeSpinsUsed + 1,mainScreenModel.freeSpinsTotal);
                }
            }

            this.onSpinComplete(new SpinCompleteEvent());
        }

        /** Game setup common to both new and resumed games. */
        private function main ( openingBalance:Number ) : void {
            // Create a new instance of GameServices to interact with the framework.
            // Game Services can be instantiated anywhere but since this class will use it repeatedly, we'll assign it to a class member variable.
            this.gameServices = new GameServices();

            // Create a listener for responses from the server.
            this.gameServices.addEventListener( IncomingMessageEvent.INCOMING_MESSAGE, this.onIncomingMessage );

            // Create a listener for balance updates.
            this.gameServices.addEventListener( GameFinanceUpdateEvent.GAME_FINANCE_UPDATE, this.onGameFinanceUpdate );

            // Use GameServices.assets object to access preloaded swf's.
            // The ui.swf contains the graphics for the game.
            // The reference is cast to a movieclip and then added to the display list.
            var ui:MovieClip = MovieClip(this.gameServices.assets.getAsset(AssetConstants.UI));
            this.addChild( ui );

            var sound:MovieClip = MovieClip(this.gameServices.assets.getAsset(AssetConstants.SOUND));
            this.initializeSound( sound );

            // Create the game's model.
            // The model stores the state of the game.
            // A set of fake reel stops are created to be displayed on the reels when the game starts.
            // For gaming authority certification purposes, the stops displayed when the game begins must be a real,
            // achievable result. In other words, the player must be able to get this result by playing the game.
            this.mainScreenModel = new MainScreenModel( openingBalance, this.gameServices.profile.CreditValues, GameConstants.LINES );
            this.mainScreenModel.reelStops = [[2,4,2],[3,5,1],[7,1,3],[6,3,2],[1,3,5]];

            // Create View classes.
            // These classes are responsible for controlling the display of the ui graphics.
            // Each view is passed a reference to the ui (added to the display list above) and a reference to the model.
            //
            // Reel View - Controls the display of the reels (Spin and Icon animations).
            // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
            // model (Set above during model instantiation).
            this.reelView = new ReelView( ui, this.mainScreenModel, this.soundSystem );
            this.reelView.forceReels();

            // Payline View - Controls the display of the paylines.
            this.paylineView = new PaylineView( ui, this.mainScreenModel );

            // Betting UI View - Controls the display of betting area (buttons, fields) and sends user input back to
            // this controller via events.
            this.bettingUIView = new BettingUIView( ui, this.mainScreenModel, this.soundSystem );
            this.bettingUIView.updateBettingUI();
            this.bettingUIView.addEventListener(CreditValueIncreaseEvent.INCREASE,this.onCreditValueIncrease);
            this.bettingUIView.addEventListener(CreditValueDecreaseEvent.DECREASE,this.onCreditValueDecrease);
            this.bettingUIView.addEventListener(CreditsPerLineEvent.CREDITS,this.onCreditsPerLine);
            this.bettingUIView.addEventListener(SpinEvent.SPIN,this.onSpin);

            // Stars View - Controls the display of the star icon accumulator.
            this.starsView = new StarsView( ui, this.mainScreenModel );

            // Free Spin View - Controls the display of the free spin ui. Note that the Reel View still displays the
            // free spin reel animations.
            this.freeSpinView = new FreeSpinView( ui, this.mainScreenModel );

            // Send the init request to the server. This request is used to get initial information from the server
            // to properly set up the game.
            this.gameServices.sendV2XMLRequest( new InitRequest().RequestData );
        }
        //
        // *************************************************************************************************************



        // *** Message Handling ****************************************************************************************
        //
        /**
         * Event handler for responses sent from the server.
         * Requests are sent using GameServices.sendV2XMLRequest.
         * Response are received by listening for IncomingMessageEvent from GameServices.
         */
        private function onIncomingMessage ( event:IncomingMessageEvent ) : void {
            // Add case statements for each request name to this switch to handle each response.
            //
            // Standard system-level message names are declared as constants in
            // com.cwh.casino4.sdk.singleplayer.messaging.v2.constants.MessagingConstants.
            // These are messages which all games use and have pre-defined request and response classes
            //
            // A game's custom messages can be handled as the developer chooses.
            // The common practice used by Chartwell is to create request and response objects which have a method to
            // output the values as XML. Request names are typically stored in a constants file.
            // In this example, the game has its own MessagingConstants class and therefore, the fully qualified
            // class name is used to differentiate between the game's constants and the core_sdk's constants.
            //
            // Another standard practice is to use this method as a type of "router" to various handlers.
            // In this example, the switch statement locates a matching request name and the case statement
            // then simple calls a handler method to actually work with the response. The xml returned in the response
            // is passed to the constructor of the response object that corresponds to the message. This structure is
            // preferred as it prevents scope confusion between any local variables that are used in handling the message.
            switch (event.Name) {
                case com.cwh.sampleslotgame.messaging.constants.MessagingConstants.INIT_REQUEST:
                    this.handleInitResponse( new InitResponse ( event.responseData ) );
                    break;

                case com.cwh.sampleslotgame.messaging.constants.MessagingConstants.SPIN_REQUEST:
                    this.handleSpinResponse( new SpinResponse ( event.responseData ) );
                    break;

                case com.cwh.sampleslotgame.messaging.constants.MessagingConstants.FREE_SPIN_REQUEST:
                    this.handleFreeSpinResponse( new FreeSpinResponse ( event.responseData ) );
                    break;
                
                case com.cwh.casino4.sdk.singleplayer.messaging.v2.constants.MessagingConstants.CONFIRM_HAND_END_REQUEST:
                    this.handleConfirmHandEndRequest();
                    break;
            }
        }

        /** Handles the InitRequest response. */
        private function handleInitResponse ( initResponse:InitResponse ) : void {
            // Record the number of "stars" accumulated from previous sessions and update the stars view.
            this.mainScreenModel.starsTotal = initResponse.StarsTotal;
            this.starsView.updateStarsTotal();

            // When all setup is complete, remove the loader screen to reveal the game.
            this.gameServices.removeLoader();
        }

        /** Handles the regular Spin Response. */
        private function handleSpinResponse ( spinResponse:SpinResponse ) : void {
            // Record data from the response into the model
            this.mainScreenModel.won = spinResponse.Won;
            this.mainScreenModel.reelStops = spinResponse.ReelStops;
            this.mainScreenModel.linesWon = spinResponse.LinesWon;
            this.mainScreenModel.winningLines = spinResponse.WinningLines;
            this.mainScreenModel.starsTotal = spinResponse.StarsTotal;

            // If the total number of free spins in the response is greater than the number of free spins
            // currently in the model, then this spin triggers free spins.
            // If so, record the free spin data and set the triggered flag to true.
            if ( spinResponse.FreeSpinsTotal > this.mainScreenModel.freeSpinsTotal ) {
                this.mainScreenModel.freeSpinsTriggered = true;
                this.mainScreenModel.freeSpinsTotal = spinResponse.FreeSpinsTotal;
                this.mainScreenModel.freeSpinsUsed = spinResponse.FreeSpinsUsed;
            }
        }

        /** Handles the Free Spin response. */
        private function handleFreeSpinResponse ( freeSpinResponse:FreeSpinResponse ) : void {
            // Record data from the response into the model.
            this.mainScreenModel.won = freeSpinResponse.Won;
            this.mainScreenModel.reelStops = freeSpinResponse.ReelStops;
            this.mainScreenModel.linesWon = freeSpinResponse.LinesWon;
            this.mainScreenModel.winningLines = freeSpinResponse.WinningLines;
            this.mainScreenModel.starsTotal = freeSpinResponse.StarsTotal;

            // If the total number of free spins in the response is greater than the number of free spins
            // currently in the model, then this free spin triggers additional free spins.
            // If so, set the triggered flag to true.
            if ( freeSpinResponse.FreeSpinsTotal > this.mainScreenModel.freeSpinsTotal ) {
                this.mainScreenModel.freeSpinsTriggered = true;
            }

            // Record the updated free spin data into the model.
            this.mainScreenModel.freeSpinsTotal = freeSpinResponse.FreeSpinsTotal;
            this.mainScreenModel.freeSpinsUsed = freeSpinResponse.FreeSpinsUsed;
            this.mainScreenModel.freeSpinsWon = freeSpinResponse.FreeSpinsWon;

            // Update the free spin counter in the free spin view.
            //this.freeSpinView.updateCounter(this.mainScreenModel.freeSpinsUsed,this.mainScreenModel.freeSpinsTotal);
        }

        /**
         * Handle the confirm hand end response.
         * Confirm hand end is a standard game message that tells the server that the results of the game
         * have been displayed to the player so the game is no longer in an unfinished (resumable) state.
         */
        private function handleConfirmHandEndRequest () : void {
            // Responsible gaming regulations require that the 3 seconds must elapse from the start of one spin
            // to the start of the next. When the spin was initiated, the start time was recorded (See spin method).
            // This time is compared to the current time to see if 3 seconds have elapsed.
            // If so, the ui is enabled so that the player can spin again.
            // If not, a timer is set to enable the ui when the remaining portion of the 3 seconds has elapsed.
            var elapsedSpinTime:int = getTimer() - this.mainScreenModel.spinStartTime;
            if ( elapsedSpinTime >= 3000 ) {
                this.enableUI();
            } else {
                setTimeout(this.enableUI,3000-elapsedSpinTime);
            }
        }

        /**
         * Handle the balance updates from the server.
         */
        private function onGameFinanceUpdate ( event:GameFinanceUpdateEvent ) : void {
            this.mainScreenModel.balance = event.balance;
            if ( !this.mainScreenModel.gameInProgress ) {
                this.bettingUIView.updateBettingUI();
            }
        }
        //
        // *************************************************************************************************************



        // *** Event Handlers ******************************************************************************************
        //
        /** Handles the event dispatched when the player clicks the Credit Value Increase button. */
        private function onCreditValueIncrease (event:CreditValueIncreaseEvent):void {
            // Tell the model to increase the credit value.
            this.mainScreenModel.increaseCreditValue();

            // Update the betting ui so that the new credit value will display.
            this.bettingUIView.updateBettingUI();
        }

        /** Handles the event dispatched when the player clicks the Credit Value Decrease button. */
        private function onCreditValueDecrease (event:CreditValueDecreaseEvent):void {
            // Tell the model to decrease the credit value.
            this.mainScreenModel.decreaseCreditValue();

            // Update the betting ui so that the new credit value will display.
            this.bettingUIView.updateBettingUI();
        }

        /** Handles the event dispatched when the player clicks one of the five bet buttons. */
        private function onCreditsPerLine (event:CreditsPerLineEvent):void {
            // Once the user has started modifying the bet, hide any winning paylines that are being displayed
            // from the previous spin.
            this.paylineView.hideAllPaylineWins();

            // Update the model with the players new bet amount.
            this.mainScreenModel.creditsPerLine = event.creditsPerLine;

            // Update the betting ui so that the new bet amount is displayed.
            this.bettingUIView.updateBettingUI();
        }

        /** Handles the event dispatched when the player clicks the spin button. */
        private function onSpin (event:SpinEvent):void {
            // Disable the ui so that the player cannot change the bet or click spin again.
            this.disableUI();

            // Validate to see if the player has satisfied all conditions required to spin.
            // 1. Has the player placed a bet?
            // 2. Does the player have enough money to bet?
            // If so, call the spin method.
            // If not, display a dialog with a message informing the player of the applicable
            // problem and re-enable the ui.
            if ( this.mainScreenModel.bet > 0 ) {
                if ( this.mainScreenModel.bet <= this.mainScreenModel.credits ) {
                    this.spin();
                } else {
                    this.gameServices.showOkDialog("spinWarningLowCredits","You do not have enough credits to place this bet.");
                    this.enableUI();
                }
            } else {
                this.gameServices.showOkDialog("spinWarningMinBet","You must bet at least one credit on at least one line to spin.");
                this.enableUI();
            }
        }

        /**
         * Handles the event dispatched when the regular reel spin animation has completed.
         * Note: This handler is only executed when regular spins complete. See onFreeSpinComplete for free spin handling.
         */
        private function onSpinComplete ( event:SpinCompleteEvent ) : void {
            // Highlight any stars collected on this spin.
            this.reelView.highlightStarIcons();
            this.starsView.updateStarsTotal();

            // If this spin has winning paylines, show the win amount and highlight the winning paylines
            if ( this.mainScreenModel.spinHasPaylineWins() ) {
                this.bettingUIView.showWin();
                this.paylineView.showAllPaylineWins();
            }

            // Check to see if this spin triggered free spins.
            // If so, transition to free spin mode.
            // If not, send the confirm hand end request to tell the server that the results have been displayed.
            if ( this.mainScreenModel.freeSpinsTriggered ) {
                // Highlight the free spin icons
                this.reelView.highlightFreeSpinIcons();

                // Show the free spin counter so the player can see how many free spins they have.
                //this.freeSpinView.showCounter();
                //this.freeSpinView.updateCounter(this.mainScreenModel.freeSpinsUsed,this.mainScreenModel.freeSpinsTotal);

                // Set the game state to free spin so that the next spin is executed as a free spin.
                this.mainScreenModel.gameState = GameConstants.FREE_SPIN_STATE;

                // Play the bonus start sound
                this.soundSystem.playSoundEffect(SoundConstants.FREE_SPIN_INTRO);

                // Set a timer to start the first free spin.
                // At this point, the UI is disabled so the player can't bet or spin.
                // Provided a few seconds for the player to see the wins from the trigger
                // spin and then start running the free spins.
                setTimeout( this.spin, 3500 );
            } else {
                this.mainScreenModel.gameInProgress = false;

                // Send the confirm hand end request.
                var confirmHandEndRequest:V2ConfirmHandEndRequest = new V2ConfirmHandEndRequest();
                this.gameServices.sendV2XMLRequest(confirmHandEndRequest.RequestData);
            }
        }

        /**
         * Handles the event dispatched when the free spin reel animation has completed.
         * Note: This handler is only executed when free spins complete. See onSpinComplete for regular spin handling.
         */
        private function onFreeSpinComplete ( event:SpinCompleteEvent ) : void {
            // Highlight any stars collected on this spin.
            this.reelView.highlightStarIcons();
            this.starsView.updateStarsTotal();

            // Update the free spin counter in the free spin view.
            // Since we updated the "used" count at the start of the spin, this won't change either value
            // unless additional free spins were triggered (in which case, the total will update to include new
            // free spins won on this spin).
            this.freeSpinView.updateCounter(this.mainScreenModel.freeSpinsUsed,this.mainScreenModel.freeSpinsTotal);    


            // If this spin has winning paylines, show the win amount and highlight the winning paylines
            if ( this.mainScreenModel.spinHasPaylineWins() ) {
                this.bettingUIView.showWin();
                this.paylineView.showAllPaylineWins();
            }

            // Check to see if this spin triggered free spins.
            // If so, transition to free spin mode.
            // If not, send the confirm hand end request to tell the server that the results have been displayed.
            if ( this.mainScreenModel.freeSpinsTriggered ) {
                // Highlight the free spin icons
                this.reelView.highlightFreeSpinIcons();
            }

            // If the current freeSpinsUsed count is less than the freeSpinsTotal count, then there are
            // additional free spins to execute. Otherwise, this was the last free spin and the game
            // should transition back to the regular spin state.
            if ( this.mainScreenModel.freeSpinsUsed < this.mainScreenModel.freeSpinsTotal ) {
                // Provide a couple of seconds for the player to view any wins before starting the next spin.
                // Note, at this point, the game state is still in free spin mode and no confirm hand end should
                // be sent until all free spins have completed.
                setTimeout( this.spin, 2000 );
            } else {
                // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
                this.mainScreenModel.gameState = GameConstants.SPIN_STATE;
                this.mainScreenModel.resetAfterFreeSpinBonus();

                this.mainScreenModel.gameInProgress = false;

                // Hide the free spin background and counter.
                this.freeSpinView.hideFreeSpinBackground();
                this.freeSpinView.hideCounter();

                // Play the bonus end sound
                this.soundSystem.playSoundEffect(SoundConstants.FREE_SPIN_END);

                // Send the confirm hand end request to tell the server that the trigger spin and all free spins
                // have finished displaying. This takes the game out of a resumable state on the server.
                var confirmHandEndRequest:V2ConfirmHandEndRequest = new V2ConfirmHandEndRequest();
                this.gameServices.sendV2XMLRequest(confirmHandEndRequest.RequestData);
            }
        }
        //
        // *************************************************************************************************************



        // *** Private Methods *****************************************************************************************
        //
        /**
         * Controls starting of a new spin.
         */
        private function spin () : void {
            // Remove any event listeners from the reel view.
            // A new one will be assigned depending on whether this is a regular or free spin so that the correct
            // actions can be taken after the spin animation completes.
            this.reelView.removeEventListener(SpinCompleteEvent.SPIN_COMPLETE,this.onSpinComplete);
            this.reelView.removeEventListener(SpinCompleteEvent.SPIN_COMPLETE,this.onFreeSpinComplete);

            // Reset the model. Not all values are reset, just those that need to be reinitialized between spins.
            this.mainScreenModel.resetForSpin();

            // Set a flag indicating the game is in progress. This is used to control balance updating.
            this.mainScreenModel.gameInProgress = true;

            // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
            this.paylineView.hideAllPaylineWins();

            // Check the current game state to determine if a regular spin or a free spin is being initiated.
            switch ( this.mainScreenModel.gameState ) {

                // Regular Spin
                case GameConstants.SPIN_STATE:
                    // Clear the win field from the previous spin.
                    this.bettingUIView.clearWin();

                    // Create and send a new SpinRequest object, passing the values required from the model.
                    var spinRequest:SpinRequest = new SpinRequest ( this.mainScreenModel.creditValue, this.mainScreenModel.creditsPerLine );
                    this.gameServices.sendV2XMLRequest( spinRequest.RequestData );

                    // Assign an event listener to act when the ReelView dispatches a spin complete event.
                    // The onSpinComplete method handles the completion of regular spins.
                    this.reelView.addEventListener(SpinCompleteEvent.SPIN_COMPLETE,this.onSpinComplete);

                    // Mark the time the spin starts.
                    // Responsible Gaming regulations require that 3 seconds must elapse between the start of the one
                    // regular spin and the start of the next. On completion of the spin, the time will be checked and
                    // if 3 seconds has not elapsed, the ui will remain disabled until it has.
                    // Note: See the handleConfirmHandEndResponse() event handler in this class.
                    this.mainScreenModel.spinStartTime = getTimer();
                    break;


                // Free Spin
                case GameConstants.FREE_SPIN_STATE:
                    // Turn on the free spin background and counter.
                    this.freeSpinView.showFreeSpinBackground();
                    this.freeSpinView.showCounter();

                    // Update the free spin counter in the free spin view.
                    // Since we are starting the animation at the same time we send the request, the server won't tell
                    // the client the new "used" count until the spin response is received (which is when the model
                    // will be updated). This creates an odd display for the player they would only see the used count
                    // update as the spin is stopping instead of when it's starting. So this code updates the counter to
                    // show the used count + 1 now in anticipation of receiving that value from the server in when the
                    // response comes in.
                    this.freeSpinView.updateCounter(this.mainScreenModel.freeSpinsUsed + 1,this.mainScreenModel.freeSpinsTotal);

                    // Create and send a new FreeSpinRequest object, passing the values required from the model.
                    var freeSpinRequest:FreeSpinRequest = new FreeSpinRequest( this.mainScreenModel.freeSpinsTotal, this.mainScreenModel.freeSpinsUsed );
                    this.gameServices.sendV2XMLRequest(freeSpinRequest.RequestData);

                    // Assign an event listener to act when the ReelView dispatches a spin complete event.
                    // The onFreeSpinComplete method handles the completion of free spins.
                    this.reelView.addEventListener(SpinCompleteEvent.SPIN_COMPLETE,this.onFreeSpinComplete);
                    break;
            }

            // Start the spin animation.
            // In order to provide a seamless experience for the player, the spin animation is started at the
            // same time the request is sent to the server (as opposed to waiting for the response).
            // This means the spin animation will have to loop endlessly while waiting for the response to arrive.
            // In this example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
            // For more information on the animation, see the ReelView class.
            // An alternate design would be to have this class call a method on the ReelView when the response arrives.
            this.reelView.spin();
        }

        /**
         * Enables the interactive controls.
         */
        private function enableUI () : void {
            this.bettingUIView.enableUI();
            this.paylineView.enableUI();
        }

        /**
         * Disables the interactive controls
         */
        private function disableUI () : void {
            this.bettingUIView.disableUI();
            this.paylineView.disableUI();
        }
        //
        // *************************************************************************************************************

    }

}