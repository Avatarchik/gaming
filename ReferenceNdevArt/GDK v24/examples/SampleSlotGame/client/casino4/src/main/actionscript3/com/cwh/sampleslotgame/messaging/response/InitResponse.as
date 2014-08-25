package com.cwh.sampleslotgame.messaging.response {

    public class InitResponse {

        private var starsTotal:uint;

        public function InitResponse ( responseData:XML ) {
            this.starsTotal = uint(responseData.msgdata.stars.@total);
        }

        public function get StarsTotal () : uint {
            return this.starsTotal;
        }

    }

}