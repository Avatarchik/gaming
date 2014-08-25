goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.SpinRequest");

amaya.game.messaging.SpinRequest = function ( creditValue, creditsPerLine ) {

    var instance = new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.SPIN_REQUEST );

    instance.append('<coinValue>' + creditValue + '</coinValue>');
    instance.append('<creditsPerLine>' + creditsPerLine + '</creditsPerLine>');

    return instance;

};