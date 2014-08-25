goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.WonView");

amaya.game.WonView = function ( container, model, soundSystem ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();

    var json = gameServices.assets.getAsset("device/main.json");

    if ( !gameServices.frameworkInfo.isLowQuality() ) {
        var tfWonShadow = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfWonShadow, json.tfWonShadow );
        container.addChild(tfWonShadow);

        var tfWon = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfWon, json.tfWon );
        container.addChild(tfWon);
    }

    /**
     * Updates the won field to show the amount won by the player.
     * Also plays a sound effect to indicate a win.
     */
    instance.showWin = function () {
        if ( !gameServices.frameworkInfo.isLowQuality() ) {
            tfWon.text = model.getCreditsWon();
            tfWonShadow.text = model.getCreditsWon();
        }
        gameServices.setWonDisplay( model.getWon(), model.getNetWon() );
        gameServices.setCreditsWonDisplay( model.getCreditsWon(), model.getNetCreditsWon() );
        soundSystem.play(amaya.game.SoundConstants.WIN);
    };

    instance.clearWin = function () {
        if ( !gameServices.frameworkInfo.isLowQuality() ) {
            tfWon.text = 0;
            tfWonShadow.text = 0;
        }
        gameServices.setWonDisplay( 0, 0 );
        gameServices.setCreditsWonDisplay( 0, 0 );
    };

    instance.clearWin();

    return instance;

};