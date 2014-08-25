package com.cwh.sampleslotgame.view {
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;

    public class StarsView {

        private var model:MainScreenModel;
        private var ui:MovieClip;

        public function StarsView ( ui:MovieClip, model:MainScreenModel ) {
            this.ui = ui;
            this.model = model;
            this.updateStarsTotal();
        }

        public function updateStarsTotal () : void {
            this.ui.tfStars.text = this.model.starsTotal;
        }

    }

}