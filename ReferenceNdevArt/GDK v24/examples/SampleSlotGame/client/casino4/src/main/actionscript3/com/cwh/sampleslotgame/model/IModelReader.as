package com.cwh.sampleslotgame.model {

    public interface IModelReader {

        function get lines ():uint;

        function get bet ():uint;

        function get creditsPerLine ():uint;

        function get balance ():Number;

        function get credits ():uint;

        function get creditValue ():Number;

        function get won ():Number;

        function get reelStops ():Array;

        function get linesWon ():uint;

        function get winningLines ():Array;

        function get spinStartTime ():int;

        function get starsTotal ():uint;
        
        function get freeSpinsTotal () : uint;

        function get freeSpinsUsed () : uint;

        function get freeSpinsWon ():uint;

        function get freeSpinsTriggered ():Boolean;

        function get gameState ():uint;

        function get gameInProgress ():Boolean;

        function get wager ():Number;

        function get displayBalance ():Number;

        function get displayCredits ():uint;

        function spinHasPaylineWins () : Boolean;
    }

}