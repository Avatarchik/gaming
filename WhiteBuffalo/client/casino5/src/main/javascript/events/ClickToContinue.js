goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.events");

goog.provide("amaya.game.events.ClickToContinue");

amaya.game.events.ClickToContinueEvent = function () {
    return new amaya.api.events.Event( amaya.game.events.ClickToContinueEvent.Click,
            {} );
};
amaya.game.events.ClickToContinueEvent.Click = "click";
