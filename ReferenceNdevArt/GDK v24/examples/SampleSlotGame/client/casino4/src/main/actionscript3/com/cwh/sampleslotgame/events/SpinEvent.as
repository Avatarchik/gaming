package com.cwh.sampleslotgame.events {
    import flash.events.Event;

    public class SpinEvent extends Event {

        public static const SPIN:String = "spin";

        public function SpinEvent () {
            super(SPIN);
        }

    }
}