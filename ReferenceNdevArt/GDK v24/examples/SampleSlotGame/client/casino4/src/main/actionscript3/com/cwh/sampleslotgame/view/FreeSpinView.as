package com.cwh.sampleslotgame.view {
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;

    public class FreeSpinView {

        private var model:MainScreenModel;
        private var ui:MovieClip;

        public function FreeSpinView ( ui:MovieClip, model:MainScreenModel ) {
            this.ui = ui;
            this.model = model;
            this.hideCounter();
            this.hideFreeSpinBackground();
        }


        public function showFreeSpinBackground () : void {
            this.ui.mcFreeSpinBackground.visible = true;
        }

        public function hideFreeSpinBackground () : void {
            this.ui.mcFreeSpinBackground.visible = false;
        }

        public function showCounter () : void {
            this.ui.mcFreeSpinCounter.visible = true;
        }

        public function hideCounter () : void {
            this.ui.mcFreeSpinCounter.visible = false;
        }

        public function updateCounter ( used:uint, total:uint ) : void {
            this.ui.mcFreeSpinCounter.tfCounter.text = used + "/" + total;
        }

    }

}