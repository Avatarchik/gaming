package com.cwh.sampleslotgame.events {
    import flash.events.Event;

    public class SpinCompleteEvent extends Event {

        public static const SPIN_COMPLETE:String = "spinComplete";

        public function SpinCompleteEvent () {
            super(SPIN_COMPLETE);
        }

    }
}