goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.InitRequest");


amaya.game.messaging.InitRequest = function () {

    return new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.INIT_REQUEST );

};