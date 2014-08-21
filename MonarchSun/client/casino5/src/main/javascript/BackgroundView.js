goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.BackgroundView");

amaya.game.BackgroundView = function ( container, logoContainer ) {

    var instance = {};

    var gameServices = new amaya.GameServices();

    var mainSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Bg_reel.jpg") );
    container.addChild(mainSpinBackground);

    var freeSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Freegame.jpg") );

    var json = gameServices.assets.getAsset("device/main.json");
    var gamelogo = new createjs.Bitmap( gameServices.assets.getAsset("device/images/logo.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( gamelogo, json.gamelogo );

    //var powerlogo = new createjs.Bitmap( gameServices.assets.getAsset("device/images/powerLogo.png") );
    //amaya.sdk.utils.JSONUtils.fromJSON( powerlogo, json.powerlogo );

    instance.showMainSpinBackground = function () {
        if ( container.contains(freeSpinBackground) ) {
            container.removeChild(freeSpinBackground);
        }
        if ( !container.contains(mainSpinBackground) ) {
            container.addChild(mainSpinBackground);
        }
    };

    instance.showFreeSpinBackground = function () {
        if ( container.contains(mainSpinBackground) ) {
            container.removeChild(mainSpinBackground);
        }
        if ( !container.contains(freeSpinBackground) ) {
            container.addChild(freeSpinBackground);
        }
    };

    instance.hideLogo = function () {
        logoContainer.removeChildAt(0);
    }

    instance.showLogo = function () {
       // if (logoContainer.getNumChildren() === 0)
            logoContainer.addChild(gamelogo);
            //logoContainer.addChild(powerlogo);
    }

    return instance;

};
