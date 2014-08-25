goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.events");

goog.provide("amaya.game.events.SpinCompleteEvent");

amaya.game.events.SpinCompleteEvent = function () {
    return new amaya.api.events.Event( amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE,
            {} );
};
amaya.game.events.SpinCompleteEvent.SPIN_COMPLETE = "spinComplete";
