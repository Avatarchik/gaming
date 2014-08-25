goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.FreeSpinView");

amaya.game.FreeSpinView = function ( container, model ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();

    var json = gameServices.assets.getAsset("device/main.json");

    var tfCounterShadow = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounterShadow, json.tfCounterShadow );

    var tfCounter = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounter, json.tfCounter );

    instance.showCounter = function () {
        container.addChild(tfCounterShadow);
        container.addChild(tfCounter);
    };

    instance.hideCounter = function () {
        container.removeChild(tfCounterShadow);
        container.removeChild(tfCounter);
    };

    /**
     *
     * @param {number} used
     * @param {number} total
     */
    instance.updateCounter = function ( used, total ) {
        tfCounter.text = used + "/" + total;
        tfCounterShadow.text = tfCounter.text;
    };

    instance.hideCounter();

    return instance;

};
