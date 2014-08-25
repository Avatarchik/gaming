goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.StarsView");

amaya.game.StarsView = function ( container, model ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();

    var json = gameServices.assets.getAsset("device/main.json");

    var tfStarsShadow = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfStarsShadow, json.tfStarsShadow );
    container.addChild(tfStarsShadow);

    var tfStars = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfStars, json.tfStars );
    container.addChild(tfStars);
    
    instance.updateStarsTotal = function () {
        tfStars.text = model.getStarsTotal();
        tfStarsShadow.text = model.getStarsTotal();
    };
        
    instance.updateStarsTotal();
    
    return instance;

};
