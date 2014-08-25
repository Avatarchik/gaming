goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.BackgroundView");

amaya.game.BackgroundView = function ( container ) {

    var instance = {};

    var gameServices = new amaya.GameServices();

    var mainSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/ui.png") );
    container.addChild(mainSpinBackground);

    var freeSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/freespin_ui.png") );

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

    return instance;

};
