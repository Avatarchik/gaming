package com.cwh.sampleslotgame.view {
    import com.cwh.casino4.api.GameServices;
    import com.cwh.casino4.sdk.core.utils.SWFUtils;
    import com.cwh.sampleslotgame.events.CreditValueDecreaseEvent;
    import com.cwh.sampleslotgame.events.CreditValueIncreaseEvent;
    import com.cwh.sampleslotgame.events.CreditsPerLineEvent;
    import com.cwh.sampleslotgame.events.SpinCompleteEvent;
    import com.cwh.sampleslotgame.events.SpinEvent;
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;
    import flash.display.SimpleButton;
    import flash.display.Sprite;
    import flash.events.MouseEvent;
    import flash.geom.ColorTransform;
    import flash.utils.clearInterval;
    import flash.utils.setInterval;
    import flash.utils.setTimeout;

    public class MainScreenView extends Sprite {

        private var model:MainScreenModel;
        private var gameServices:GameServices;
        private var ui:MovieClip;

        private var pollIntervalId:uint;

        public function MainScreenView ( model:MainScreenModel ) {
            this.model = model;
            this.gameServices = new GameServices();
            this.ui = MovieClip(this.gameServices.assets.getAsset("ui/ui.swf"));
            this.addChild( this.ui );
        }

    }

}