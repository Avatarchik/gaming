package com.cwh.sampleslotgame.events {
    import flash.events.Event;

    public class CreditValueIncreaseEvent extends Event {

        public static const INCREASE:String = "increase";

        public function CreditValueIncreaseEvent () {
            super(INCREASE);
        }

    }
}