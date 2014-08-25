package com.cwh.sampleslotgame.view {
    import com.cwh.sampleslotgame.constants.GameConstants;
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;
    import flash.events.EventDispatcher;
    import flash.events.MouseEvent;

    public class PaylineView extends EventDispatcher {

        private var model:MainScreenModel;
        private var ui:MovieClip;

        public function PaylineView ( ui:MovieClip, model:MainScreenModel ) {
            this.ui = ui;
            this.model = model;

            for ( var i:uint=1; i<=GameConstants.LINES; i++ ) {
                this.ui["btnLine"+i].addEventListener(MouseEvent.ROLL_OVER,this["onLine"+i+"Over"]);
                this.ui["btnLine"+i].addEventListener(MouseEvent.ROLL_OUT,this["onLine"+i+"Out"]);
            }

            this.hideAllPaylines();
            this.hideAllPaylineWinBoxes();
        }

        public function enableUI () : void {
            for ( var i:uint=1; i<=GameConstants.LINES; i++ ) {
                this.ui["btnLine"+i].enabled = true;
                this.ui["btnLine"+i].mouseEnabled = true;
            }
        }

        public function disableUI () : void {
            for ( var i:uint=1; i<=GameConstants.LINES; i++ ) {
                this.ui["btnLine"+i].enabled = false;
                this.ui["btnLine"+i].mouseEnabled = false;
            }
        }

        public function showPaylineWin ( line:uint, won:uint ) : void {
            this.showPayline(line);
            this.showPaylineWinBox(line, won);
        }

        public function hidePaylineWin ( line:uint ) : void {
            this.hidePayline(line);
            this.hidePaylineWinBox(line);
        }

        public function showAllPaylineWins () : void {
            for ( var i:uint=0; i<this.model.winningLines.length; i++ ) {
                this.showPaylineWin( this.model.winningLines[i].id, this.model.winningLines[i].won );
            }
        }

        public function hideAllPaylineWins () : void {
            this.hideAllPaylines();
            this.hideAllPaylineWinBoxes();
        }


        private function showPayline ( line:uint ) : void {
            this.ui["mcLine"+line].visible = true;
        }

        private function hidePayline ( line:uint ) : void {
            this.ui["mcLine"+line].visible = false;
        }

        private function hideAllPaylines () : void {
            for ( var i:uint=1; i<=GameConstants.LINES; i++ ) {
                this.hidePayline(i);
            }
        }

        private function showPaylineWinBox ( id:uint, won:uint ) : void {
            this.ui["mcLine"+id+"Win"].visible = true;
            this.ui["mcLine"+id+"Win"].tfWon.text = won;
        }

        private function hidePaylineWinBox ( id:uint ) : void {
            this.ui["mcLine"+id+"Win"].visible = false;
            this.ui["mcLine"+id+"Win"].tfWon.text = "";
        }

        private function hideAllPaylineWinBoxes () : void {
            for ( var i:uint=1; i<=GameConstants.LINES; i++ ) {
                this.hidePaylineWinBox(i);
            }
        }

        private function onLine1Over ( event:MouseEvent ) : void {
            this.hideAllPaylineWins();
            this.showPayline(1);
        }

        private function onLine1Out ( event:MouseEvent ) : void {
            this.hidePayline(1);
        }

        private function onLine2Over ( event:MouseEvent ) : void {
            this.hideAllPaylineWins();
            this.showPayline(2);
        }

        private function onLine2Out ( event:MouseEvent ) : void {
            this.hidePayline(2);
        }

        private function onLine3Over ( event:MouseEvent ) : void {
            this.hideAllPaylineWins();
            this.showPayline(3);
        }

        private function onLine3Out ( event:MouseEvent ) : void {
            this.hidePayline(3);
        }

        private function onLine4Over ( event:MouseEvent ) : void {
            this.hideAllPaylineWins();
            this.showPayline(4);
        }

        private function onLine4Out ( event:MouseEvent ) : void {
            this.hidePayline(4);
        }

        private function onLine5Over ( event:MouseEvent ) : void {
            this.hideAllPaylineWins();
            this.showPayline(5);
        }

        private function onLine5Out ( event:MouseEvent ) : void {
            this.hidePayline(5);
        }

    }

}