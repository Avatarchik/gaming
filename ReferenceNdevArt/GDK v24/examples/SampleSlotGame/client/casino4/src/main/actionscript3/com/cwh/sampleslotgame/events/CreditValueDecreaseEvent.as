package com.cwh.sampleslotgame.events {
    import flash.events.Event;

    public class CreditValueDecreaseEvent extends Event {

        public static const DECREASE:String = "decrease";

        public function CreditValueDecreaseEvent () {
            super(DECREASE);
        }

    }
}