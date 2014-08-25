goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.Display");

amaya.game.Display = function ( gameCanvas ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();
    gameServices.addEventListener( amaya.api.events.GameResizeEvent.RESIZE, onResize );

    var gameStage = new createjs.Stage(gameCanvas);

    instance.backgroundContainer = new createjs.Container();
    gameStage.addChild( instance.backgroundContainer );

    instance.bettingUIContainer = new createjs.Container();
    gameStage.addChild( instance.bettingUIContainer );

    instance.freeSpinContainer = new createjs.Container();
    gameStage.addChild( instance.freeSpinContainer );

    instance.starsContainer = new createjs.Container();
    gameStage.addChild( instance.starsContainer );

    instance.wonContainer = new createjs.Container();
    gameStage.addChild( instance.wonContainer );

    instance.reelsContainer = new createjs.Container();
    gameStage.addChild( instance.reelsContainer );

    instance.paylineContainer = new createjs.Container();
    gameStage.addChild( instance.paylineContainer );

    instance.paytableContainer = new createjs.Container();
    gameStage.addChild( instance.paytableContainer );

    instance.gameSettingsContainer = new createjs.Container();
    gameStage.addChild( instance.gameSettingsContainer );

    // TODO: Optimize this...only enable when in the betting phase of the game.
    gameStage.enableMouseOver();

    createjs.Ticker.setFPS(60);
    createjs.Ticker.addEventListener("tick", gameStage);

    function onResize ( event ) {
        resize();
    }

    function resize () {
        gameStage.scaleX = gameServices.frameworkInfo.scaleX() * gameServices.frameworkInfo.devicePixelRatio();
        gameStage.scaleY = gameServices.frameworkInfo.scaleY() * gameServices.frameworkInfo.devicePixelRatio();
    }

    resize();

    return instance;

};