package com.cwh.sampleslotgame.messaging.response {

    public class SpinResponse {

        private var reels:Array;
        private var won:uint;
        private var lines:Array;
        private var linesWon:uint;
        private var starsTotal:uint;
        private var freeSpinsTotal:uint;
        private var freeSpinsUsed:uint;

        public function SpinResponse ( responseData:XML ) {

            this.reels = [];
            for each ( var reelXml:XML in responseData.msgdata.reels.reel ) {
                var stops:Array = reelXml.text().split(",");
                this.reels.push(stops);
            }

            this.won = uint(responseData.msgdata.creditResults.@won);

            this.linesWon = uint(responseData.msgdata.lines.@won);

            this.lines = [];
            for each ( var linesXml:XML in responseData.msgdata.lines.line ) {
                var a:Array = String(linesXml.@positions).split(";");
                var positions:Array = [ a[0].split(","), a[1].split(","), a[2].split(",") ];
                var lineData:Object = { id:linesXml.@id, won:linesXml.@won, icon:linesXml.@icon, positions:positions };
                this.lines.push(lineData);
            }
            
            this.starsTotal = uint(responseData.msgdata.stars.@total);

            if ( responseData.msgdata.hasOwnProperty("freeSpins") ) {
                this.freeSpinsTotal = uint(responseData.msgdata.freeSpins.@total);
                this.freeSpinsUsed = uint(responseData.msgdata.freeSpins.@used);
            }
        }

        public function get ReelStops () : Array {
            return this.reels.slice();
        }

        public function get Won () : uint {
            return this.won;
        }

        public function get LinesWon () : uint {
            return this.linesWon;
        }

        public function get WinningLines () : Array {
            return this.lines;
        }
        
        public function get StarsTotal () : uint {
            return this.starsTotal;
        }

        public function get FreeSpinsTotal ():uint {
            return this.freeSpinsTotal;
        }

        public function get FreeSpinsUsed ():uint {
            return this.freeSpinsUsed;
        }
    }

}