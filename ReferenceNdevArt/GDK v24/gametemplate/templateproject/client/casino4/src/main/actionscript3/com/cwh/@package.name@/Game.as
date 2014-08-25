package com.cwh.@package.name@ {
    import com.cwh.casino4.api.GameServices;
    import com.cwh.casino4.api.events.GameFinanceUpdateEvent;
    import com.cwh.casino4.api.events.IncomingMessageEvent;
    import com.cwh.casino4.sdk.singleplayer.game.V2Game;
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.constants.MessagingConstants;

    public class Game extends V2Game {

        private var gameServices:GameServices;

        public function Game () {}

        override protected function startMain ( startGameResponseData:XML, openingBalance:Number ) : void {
            this.main(openingBalance);
        }

        override protected function resumeMain ( resumeGameResponseData:XML, openingBalance:Number ) : void {
            this.main(openingBalance);
        }

        /** Game setup common to both new and resumed games. */
        private function main ( openingBalance:Number ) : void {
            this.gameServices = new GameServices();
            this.gameServices.addEventListener( IncomingMessageEvent.INCOMING_MESSAGE, this.onIncomingMessage );
            this.gameServices.addEventListener( GameFinanceUpdateEvent.GAME_FINANCE_UPDATE, this.onGameFinanceUpdate );
            this.gameServices.removeLoader();
        }

        private function onIncomingMessage ( event:IncomingMessageEvent ) : void {
            switch (event.Name) {
                // Add cases for your specific request name values
                // Example:
                // case "MyRequestName":
                //    handle response here
                //    break;
            }
        }

        private function onGameFinanceUpdate ( event:GameFinanceUpdateEvent ) : void {
        }

    }

}