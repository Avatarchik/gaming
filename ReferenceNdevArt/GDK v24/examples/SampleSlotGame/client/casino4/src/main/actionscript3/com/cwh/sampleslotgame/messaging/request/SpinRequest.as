package com.cwh.sampleslotgame.messaging.request {
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.request.V2Request;
    import com.cwh.sampleslotgame.messaging.constants.MessagingConstants;

    public class SpinRequest extends V2Request {

        public function SpinRequest ( creditValue:Number, creditsPerLine:uint ) {
            super(MessagingConstants.SPIN_REQUEST);
            this.appendChildToBody(new XML("<coinValue>" + creditValue + "</coinValue>"));
            this.appendChildToBody(new XML("<creditsPerLine>" + creditsPerLine + "</creditsPerLine>"));
        }

    }

}