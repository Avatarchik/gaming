package com.cwh.sampleslotgame.messaging.request {
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.request.V2Request;
    import com.cwh.sampleslotgame.messaging.constants.MessagingConstants;

    public class InitRequest extends V2Request {

        public function InitRequest () {
            super(MessagingConstants.INIT_REQUEST);
        }

    }

}