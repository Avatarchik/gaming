goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.Game");

amaya.game.Game = function () {

    var instance = {};

    var gameServices = new amaya.GameServices();
    var gameCanvas = new amaya.sdk.ui.canvas.CanvasManager();

    amaya.sdk.singleplayer.V2Game.init( startMain, resumeMain );

    function startMain ( data, openingBalance ) {
        main(openingBalance);
    }

    function resumeMain ( data, openingBalance ) {
        main(openingBalance);
    }

    function main ( openingBalance ) {
        gameServices = new amaya.GameServices();
        gameServices.addEventListener( amaya.api.events.IncomingMessageEvent.INCOMING_MESSAGE, onIncomingMessage );
        gameServices.addEventListener( amaya.api.events.GameFinanceUpdateEvent.GAME_FINANCE_UPDATE, onGameFinanceUpdate );
        gameServices.removeLoader();
    }

    function onIncomingMessage ( event ) {
        switch (event.data.name) {
            // Add cases for your specific request name values
            // Example:
            // case "MyRequestName":
            //    handle response here
            //    break;
        }
    }

    function onGameFinanceUpdate ( event ) {
    }

    return instance;

};
