goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.Display");

amaya.game.Display = function ( gameCanvas ) {

    var instance =  new amaya.api.EventDispatcher();

    var gameServices = new amaya.GameServices ();
    gameServices.addEventListener( amaya.api.events.GameResizeEvent.RESIZE, onResize );

    var gameStage = new createjs.Stage(gameCanvas);
    gameStage.addEventListener("click",skipAnimation);

     instance.backgroundContainer = new createjs.Container();
     gameStage.addChild( instance.backgroundContainer );

    instance.reelsBackgroundContainer = new createjs.Container();
    gameStage.addChild( instance.reelsBackgroundContainer );

   // instance.bettingUIContainer = new createjs.Container();
   // gameStage.addChild( instance.bettingUIContainer );

    instance.reelsContainer = new createjs.Container();
    gameStage.addChild( instance.reelsContainer );

    instance.freeSpinContainer = new createjs.Container();
    gameStage.addChild( instance.freeSpinContainer );

    instance.paylineContainer = new createjs.Container();
    gameStage.addChild( instance.paylineContainer );

    instance.symAnimContainer = new createjs.Container();
    gameStage.addChild( instance.symAnimContainer );



    instance.winboxContainer = new createjs.Container();
    gameStage.addChild( instance.winboxContainer );

    instance.reelStreamContainer = new createjs.Container();
    gameStage.addChild( instance.reelStreamContainer );

    instance.wonContainer = new createjs.Container();
    gameStage.addChild( instance.wonContainer );

    instance.winCelebrationContainer = new createjs.Container();
    gameStage.addChild( instance.winCelebrationContainer );

    instance.logoContainer = new createjs.Container();
    gameStage.addChild( instance.logoContainer );

    instance.paytableContainer = new createjs.Container();
    gameStage.addChild( instance.paytableContainer );

    instance.introContainer = new createjs.Container();
    gameStage.addChild( instance.introContainer );

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

    function skipAnimation(event){
        instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
    }

    return instance;

};