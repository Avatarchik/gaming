package com.cwh.sampleslotgame.messaging.request {
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.request.V2Request;
    import com.cwh.sampleslotgame.messaging.constants.MessagingConstants;

    public class FreeSpinRequest extends V2Request {

        public function FreeSpinRequest (total:uint, used:uint ) {
            super(MessagingConstants.FREE_SPIN_REQUEST);
            this.appendChildToBody(new XML('<freeSpins total="'+ total +'" used="'+ used +'"/>'));
        }

    }

}