package com.cwh.sampleslotgame.view {
    import com.cwh.casino4.api.GameServices;
    import com.cwh.casino4.sdk.core.currency.CurrencyFormat;
    import com.cwh.casino4.sdk.core.currency.CurrencyFormatStyle;
    import com.cwh.casino4.sdk.core.sound.SoundSystem;
    import com.cwh.casino4.sdk.core.utils.MathUtils;
    import com.cwh.sampleslotgame.constants.GameConstants;
    import com.cwh.sampleslotgame.events.CreditValueDecreaseEvent;
    import com.cwh.sampleslotgame.events.CreditValueIncreaseEvent;
    import com.cwh.sampleslotgame.events.CreditsPerLineEvent;
    import com.cwh.sampleslotgame.events.SpinEvent;
    import com.cwh.sampleslotgame.constants.SoundConstants;
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;
    import flash.display.SimpleButton;
    import flash.events.EventDispatcher;
    import flash.events.MouseEvent;
    import flash.geom.ColorTransform;

    /**
     * The Betting UI view is responsible for controlling the display of the players betting activity (credit value
     * selection, credits display, bet display, bet selection, spin button). It is also responsible for capturing the
     * users input and notifying the controller.
     */
    public class BettingUIView extends EventDispatcher {

        /** Reference to the model, passed in from Game.as */
        private var model:MainScreenModel;

        /** Reference to the graphics swf on the stage, passed in from Game.as */
        private var ui:MovieClip;

        /** Reference to the sound system object, passed in from Game.as */
        private var soundSystem:SoundSystem;

        /** Game Services instance */
        private var gameServices:GameServices;

        /**
         * Initializes the display of the betting ui.
         * Sets up listeners on buttons to capture user input.
         *
         * @param ui            Reference to the graphics
         * @param model         Reference to the model
         * @param soundSystem   Reference to the sound system
         */
        public function BettingUIView ( ui:MovieClip, model:MainScreenModel, soundSystem:SoundSystem ) {
            this.ui = ui;
            this.model = model;
            this.soundSystem = soundSystem;
            this.gameServices = new GameServices();

            this.ui.mcBettingUI.btnCreditValueIncrease.addEventListener(MouseEvent.CLICK,this.onCreditValueIncrease);
            this.ui.mcBettingUI.btnCreditValueDecrease.addEventListener(MouseEvent.CLICK,this.onCreditValueDecrease);
            this.ui.mcBettingUI.btnCreditPerLine1.addEventListener(MouseEvent.CLICK,this.onCreditPerLine1);
            this.ui.mcBettingUI.btnCreditPerLine2.addEventListener(MouseEvent.CLICK,this.onCreditPerLine2);
            this.ui.mcBettingUI.btnCreditPerLine3.addEventListener(MouseEvent.CLICK,this.onCreditPerLine3);
            this.ui.mcBettingUI.btnCreditPerLine4.addEventListener(MouseEvent.CLICK,this.onCreditPerLine4);
            this.ui.mcBettingUI.btnCreditPerLine5.addEventListener(MouseEvent.CLICK,this.onCreditPerLine5);
            this.ui.mcBettingUI.btnSpin.addEventListener(MouseEvent.CLICK,this.onSpin);

            this.clearWin();
        }

        /**
         * Updates the display of the betting ui based on the current state of the model's data.
         */
        public function updateBettingUI () : void {
            // Update betting ui text fields.
            // Note that the credit value, which is a monetary amount, is formatted as currency.
            this.ui.mcBettingUI.tfCreditValue.text = CurrencyFormat.format(this.model.creditValue, CurrencyFormatStyle.STANDARD, this.gameServices.profile.CurrencyCode);
            this.ui.mcBettingUI.tfCredits.text = this.model.credits;
            this.ui.mcBettingUI.tfBet.text = this.model.bet;

            // Update the balance and wagered fields in the framework ui.
            this.gameServices.setBalanceDisplay( this.model.balance );
            this.gameServices.setWageredDisplay( this.model.wager );

            // Loops over the bet selection buttons setting properties to show the selected button.
            for ( var i:uint=1; i<=GameConstants.MAX_CREDITS_PER_LINE; i++ ) {
                var btn:SimpleButton = SimpleButton(this.ui.mcBettingUI["btnCreditPerLine"+i]);
                if ( i == this.model.creditsPerLine ) {
                    btn.enabled = false;
                    btn.mouseEnabled = false;
                    btn.transform.colorTransform = new ColorTransform(1,1,1,1,50,30,10);
                } else {
                    btn.enabled = true;
                    btn.mouseEnabled = true;
                    btn.transform.colorTransform = new ColorTransform(1,1,1,1,0,0,0);
                }
            }
        }

        /**
         * Updates the won field to show the amount won by the player.
         * Also plays a sound effect to indicate a win.
         */
        public function showWin () : void {
            this.ui.mcBettingUI.tfWon.text = this.model.won;
            this.ui.mcBettingUI.tfWonShadow.text = this.model.won;
            this.soundSystem.playSoundEffect(SoundConstants.WIN);
        }

        /**
         * Clears the won field.
         */
        public function clearWin () : void {
            this.ui.mcBettingUI.tfWon.text = 0;
            this.ui.mcBettingUI.tfWonShadow.text = 0;
        }

        /**
         * Disables all the buttons in the ui.
         * Dims the graphics to show the disabled state.
         */
        public function disableUI () : void {
            this.ui.mcBettingUI.btnCreditValueIncrease.enabled = false;
            this.ui.mcBettingUI.btnCreditValueIncrease.mouseEnabled = false;
            this.ui.mcBettingUI.btnCreditValueDecrease.enabled = false;
            this.ui.mcBettingUI.btnCreditValueDecrease.mouseEnabled = false;
            this.ui.mcBettingUI.btnSpin.enabled = false;
            this.ui.mcBettingUI.btnSpin.mouseEnabled = false;

            for ( var i:uint=1; i<=GameConstants.MAX_CREDITS_PER_LINE; i++ ) {
                var btn:SimpleButton = SimpleButton(this.ui.mcBettingUI["btnCreditPerLine"+i]);
                btn.enabled = false;
                btn.mouseEnabled = false;
            }

            this.ui.mcBettingUI.tfCredits.text = this.model.displayCredits;
            this.gameServices.setBalanceDisplay( this.model.displayBalance );

            this.ui.mcBettingUI.transform.colorTransform = new ColorTransform(1,1,1,1,-40,-40,-40);
        }

        /**
         * Enables the buttons in the ui.
         * Resets the color of graphics to show the enabled state.
         */
        public function enableUI () : void {
            this.ui.mcBettingUI.btnCreditValueIncrease.enabled = true;
            this.ui.mcBettingUI.btnCreditValueIncrease.mouseEnabled = true;
            this.ui.mcBettingUI.btnCreditValueDecrease.enabled = true;
            this.ui.mcBettingUI.btnCreditValueDecrease.mouseEnabled = true;
            this.ui.mcBettingUI.btnSpin.enabled = true;
            this.ui.mcBettingUI.btnSpin.mouseEnabled = true;
            this.updateBettingUI();
            this.ui.mcBettingUI.transform.colorTransform = new ColorTransform(1,1,1,1,0,0,0);
        }

        // *** Event Handlers ******************************************************************************************
        //

        /**
         * Captures the user's generic mouse click event and dispatches a CreditValueIncreaseEvent to notify the controller
         */
        private function onCreditValueIncrease (event:MouseEvent):void {
            this.dispatchEvent( new CreditValueIncreaseEvent() );
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditValueDecreaseEvent to notify the controller
         */
        private function onCreditValueDecrease (event:MouseEvent):void {
            this.dispatchEvent( new CreditValueDecreaseEvent() );
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditsPerLineEvent to notify the controller
         * that the player has elected to bet one credit per line.
         */
        private function onCreditPerLine1 (event:MouseEvent):void {
            this.dispatchEvent( new CreditsPerLineEvent(1) );
            this.soundSystem.playSoundEffect(SoundConstants.BUTTON);
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditsPerLineEvent to notify the controller
         * that the player has elected to bet two credits per line.
         */
        private function onCreditPerLine2 (event:MouseEvent):void {
            this.dispatchEvent( new CreditsPerLineEvent(2) );
            this.soundSystem.playSoundEffect(SoundConstants.BUTTON);
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditsPerLineEvent to notify the controller
         * that the player has elected to bet three credits per line.
         */
        private function onCreditPerLine3 (event:MouseEvent):void {
            this.dispatchEvent( new CreditsPerLineEvent(3) );
            this.soundSystem.playSoundEffect(SoundConstants.BUTTON);
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditsPerLineEvent to notify the controller
         * that the player has elected to bet four credits per line.
         */
        private function onCreditPerLine4 (event:MouseEvent):void {
            this.dispatchEvent( new CreditsPerLineEvent(4) );
            this.soundSystem.playSoundEffect(SoundConstants.BUTTON);
        }

        /**
         * Captures the user's generic mouse click event and dispatches a CreditsPerLineEvent to notify the controller
         * that the player has elected to bet five credits per line.
         */
        private function onCreditPerLine5 (event:MouseEvent):void {
            this.dispatchEvent( new CreditsPerLineEvent(5) );
            this.soundSystem.playSoundEffect(SoundConstants.BUTTON);
        }

        /**
         * Captures the user's generic mouse click event and dispatches a SpinEvent to notify the controller
         * that the player has elected to spin.
         */
        private function onSpin (event:MouseEvent):void {
            this.dispatchEvent( new SpinEvent() );
        }
        //
        // *************************************************************************************************************

    }

}