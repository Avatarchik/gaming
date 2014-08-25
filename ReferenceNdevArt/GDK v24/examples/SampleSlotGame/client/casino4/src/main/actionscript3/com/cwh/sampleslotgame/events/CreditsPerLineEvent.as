package com.cwh.sampleslotgame.events {
    import flash.events.Event;

    public class CreditsPerLineEvent extends Event {

        public static const CREDITS:String = "credits";

        public var creditsPerLine:uint;

        public function CreditsPerLineEvent ( creditsPerLine:uint ) {
            super(CREDITS);
            this.creditsPerLine = creditsPerLine;
        }

    }
}