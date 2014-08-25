goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.FreeSpinRequest");


amaya.game.messaging.FreeSpinRequest = function ( total, used ) {

    var instance = new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.FREE_SPIN_REQUEST );

    instance.append('<freeSpins total="'+ total +'" used="'+ used +'"/>');

    return instance;

};