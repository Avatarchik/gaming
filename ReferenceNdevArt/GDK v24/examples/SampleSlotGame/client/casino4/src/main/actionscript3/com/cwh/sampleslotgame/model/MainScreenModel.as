package com.cwh.sampleslotgame.model {
    import com.cwh.casino4.sdk.core.utils.MathUtils;

    public class MainScreenModel implements IModelReader {

        private var _lines:uint;
        private var _bet:uint;
        private var _creditsPerLine:uint;
        private var _won:Number;
        private var _balance:Number;
        private var _creditValues:Array;
        private var _creditValueIndex:Number;
        private var _reelStops:Array;
        private var _linesWon:uint;
        private var _winningLines:Array;
        private var _spinStartTime:int;
        private var _starsTotal:uint;
        private var _freeSpinsTotal:uint;
        private var _freeSpinsUsed:uint;
        private var _freeSpinsWon:uint;
        private var _freeSpinsTriggered:Boolean;
        private var _gameState:uint; // 0 is Regular Spin, 1 is Free Spin
        private var _gameInProgress:Boolean;

        /*
        public function print () : void {
            LCOLog.log("------------------------------",7);
            LCOLog.log("_lines: " + _lines,7);
            LCOLog.log("_bet: " + _bet,7);
            LCOLog.log("_creditsPerLine: " + _creditsPerLine,7);
            LCOLog.log("_won: " + _won,7);
            LCOLog.log("_balance: " + _balance,7);
            LCOLog.log("_creditValues: " + _creditValues,7);
            LCOLog.log("_creditValueIndex: " + _creditValueIndex,7);
            LCOLog.log("_reelStops: " + _reelStops,7);
            LCOLog.log("_linesWon: " + _linesWon,7);
            LCOLog.log("_winningLines: " + _winningLines,7);
            LCOLog.log("_spinStartTime: " + _spinStartTime,7);
            LCOLog.log("_starsTotal: " + _starsTotal,7);
            LCOLog.log("_freeSpinsTotal: " + _freeSpinsTotal,7);
            LCOLog.log("_freeSpinsUsed: " + _freeSpinsUsed,7);
            LCOLog.log("_freeSpinsWon: " + _freeSpinsWon,7);
            LCOLog.log("_freeSpinsTriggered: " + _freeSpinsTriggered,7);
            LCOLog.log("_gameState: " + _gameState,7);
        }
        */

        public function MainScreenModel ( balance:Number, creditValues:Array, lines:uint ) {
            this._balance = balance;
            this._creditValues = creditValues;
            this._creditValueIndex = 0;
            this._lines = lines;
            this._creditsPerLine = 1;
            this.updateBet();
            this.resetForSpin();
        }

        public function resetForSpin () : void {
            this._reelStops = null;
            this._winningLines = [];
            this._won = 0;
            this._freeSpinsTriggered = false;
        }

        public function resetAfterFreeSpinBonus () : void {
            this._freeSpinsTotal = 0;
            this._freeSpinsUsed = 0;
            this._freeSpinsWon = 0;
        }

        public function get lines ():uint {
            return _lines;
        }


        public function get bet ():uint {
            return _bet;
        }


        public function get creditsPerLine ():uint {
            return _creditsPerLine;
        }

        public function set creditsPerLine (value:uint):void {
            _creditsPerLine = value;
            this.updateBet();
        }


        public function get balance ():Number {
            return _balance;
        }

        public function set balance (value:Number):void {
            _balance = value;
        }

        public function get credits ():uint {
            return MathUtils.correctFloatingPoint(this._balance / this.creditValue);
        }

        public function set creditValue ( value:Number ):void {
            for ( var i:uint=0; i<_creditValues.length; i++ ) {
                if ( _creditValues[i] == value ) {
                    _creditValueIndex = i;
                }
            }
        }

        public function get creditValue ():Number {
            return _creditValues[_creditValueIndex];
        }

        public function increaseCreditValue () : Number {
            if (_creditValueIndex < _creditValues.length-1) {
                _creditValueIndex++;
            }
            return creditValue;
        }

        public function decreaseCreditValue () : Number {
            if (_creditValueIndex > 0) {
                _creditValueIndex--;
            }
            return creditValue;
        }


        public function get won ():Number {
            return _won;
        }

        public function set won (value:Number):void {
            _won = value;
        }


        public function get reelStops ():Array {
            return _reelStops;
        }

        public function set reelStops (value:Array):void {
            _reelStops = value;
        }

        public function get winningLines ():Array {
            return _winningLines;
        }

        public function set winningLines (value:Array):void {
            _winningLines = value;
        }


        public function get spinStartTime ():int {
            return _spinStartTime;
        }

        public function set spinStartTime (value:int):void {
            _spinStartTime = value;
        }

        private function updateBet ():void {
            _bet = this._creditsPerLine * this._lines;
        }


        public function get starsTotal ():uint {
            return _starsTotal;
        }

        public function set starsTotal (value:uint):void {
            _starsTotal = value;
        }

        public function get freeSpinsTotal ():uint {
            return _freeSpinsTotal;
        }

        public function set freeSpinsTotal (value:uint):void {
            _freeSpinsTotal = value;
        }

        public function get freeSpinsUsed ():uint {
            return _freeSpinsUsed;
        }

        public function set freeSpinsUsed (value:uint):void {
            _freeSpinsUsed = value;
        }

        public function get freeSpinsWon ():uint {
            return _freeSpinsWon;
        }

        public function set freeSpinsWon (value:uint):void {
            _freeSpinsWon = value;
        }

        public function get freeSpinsTriggered ():Boolean {
            return _freeSpinsTriggered;
        }

        public function set freeSpinsTriggered (value:Boolean):void {
            _freeSpinsTriggered = value;
        }

        public function get gameState ():uint {
            return _gameState;
        }

        public function set gameState (value:uint):void {
            _gameState = value;
        }

        public function spinHasPaylineWins () : Boolean {
            return this._winningLines.length > 0;
        }


        public function get linesWon ():uint {
            return _linesWon;
        }

        public function set linesWon (value:uint):void {
            _linesWon = value;
        }

        public function get gameInProgress ():Boolean {
            return this._gameInProgress;
        }

        public function set gameInProgress (gameInProgess:Boolean):void {
            this._gameInProgress = gameInProgess;
        }

        public function get wager () : Number {
            return MathUtils.correctFloatingPoint(this.bet * this.creditValue);
        }

        public function get displayBalance () : Number {
            return this.balance - this.wager;
        }

        public function get displayCredits () : uint {
            return this.credits - this.bet;
        }
    }

}