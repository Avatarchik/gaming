package com.cwh.sampleslotgame.view {
    import com.cwh.casino4.sdk.core.sound.SoundSystem;
    import com.cwh.casino4.sdk.core.utils.SWFUtils;
    import com.cwh.sampleslotgame.constants.GameConstants;
    import com.cwh.sampleslotgame.events.SpinCompleteEvent;
    import com.cwh.sampleslotgame.constants.SoundConstants;
    import com.cwh.sampleslotgame.model.MainScreenModel;

    import flash.display.MovieClip;
    import flash.display.Sprite;
    import flash.events.EventDispatcher;
    import flash.utils.clearInterval;
    import flash.utils.getTimer;
    import flash.utils.setInterval;
    import flash.utils.setTimeout;

    public class ReelView extends EventDispatcher {

        private var model:MainScreenModel;
        private var ui:MovieClip;
        private var soundSystem:SoundSystem;
        private var pollIntervalId:uint;
        private var spinStartTime:uint;
        private var reelSpinSoundId:uint;

        public function ReelView ( ui:MovieClip, model:MainScreenModel, soundSystem:SoundSystem ) {
            this.ui = ui;
            this.model = model;
            this.soundSystem = soundSystem;
        }

        public function forceReels () : void {
            for ( var i:uint=1; i<=5; i++ ) {
                if ( this.ui["mcReel"+i].numChildren > 0 ) {
                    this.ui["mcReel"+i].removeChildAt(0);
                }
                this.showReel(i);
            }
        }

        public function highlightStarIcons () : void {
            var soundPlayed:Boolean;
            for ( var i:uint=1; i<=GameConstants.REELS; i++ ) {
                for ( var j:uint=0; j<GameConstants.ICONS; j++ ) {
                    if ( this.model.reelStops[i-1][j] == GameConstants.REEL_STOP_STAR ) {
                        this.ui["mcReel"+i].getChildAt(0).getChildAt(j).gotoAndPlay(2);
                        if (!soundPlayed) {
                            this.soundSystem.playSoundEffect(SoundConstants.STAR);
                            soundPlayed = true;
                        }
                    }
                }
            }
        }

        public function highlightFreeSpinIcons () : void {
            for ( var i:uint=1; i<=GameConstants.REELS; i++ ) {
                for ( var j:uint=0; j<GameConstants.ICONS; j++ ) {
                    if ( this.model.reelStops[i-1][j] == GameConstants.REEL_STOP_FREE_SPIN ) {
                        this.ui["mcReel"+i].getChildAt(0).getChildAt(j).gotoAndPlay(2);
                    }
                }
            }
        }

        public function spin () : void {
            setTimeout(spinReel,200,1);
        }

        private function spinReel ( id:uint ) : void {
            if ( id == 1 ) {
                this.reelSpinSoundId = this.soundSystem.playSoundEffect(SoundConstants.REEL_SPIN,1000);
            }
            this.ui["mcReel"+id].removeChildAt(0);
            var blur:MovieClip = MovieClip(SWFUtils.getLibraryInstance( this.ui, "Blur" ));
            this.ui["mcReel"+id].addChild(blur);
            if ( id < GameConstants.REELS ) {
                setTimeout(spinReel,150,id+1);
            } else {
                this.spinStartTime = getTimer();
                this.pollIntervalId = setInterval(checkForReelStops,100);
            }
        }

        private function checkForReelStops () : void {
            var elapsedSpinTime:uint = getTimer() - this.spinStartTime;
            if ( this.model.reelStops != null && elapsedSpinTime >= 1350 ) {
                clearInterval(this.pollIntervalId);
                this.stopReel(1);
            }
        }

        private function stopReel ( id:uint ) : void {
            this.ui["mcReel"+id].removeChildAt(0);
            this.showReel(id);
            this.soundSystem.playSoundEffect(SoundConstants.REEL_STOP,0,0,1);
            if ( id < GameConstants.REELS ) {
                setTimeout(stopReel,200,id+1);
            } else {
                this.soundSystem.stopSound(this.reelSpinSoundId);
                this.dispatchEvent( new SpinCompleteEvent() );
            }
        }

        private function showReel ( id:uint ) : void {
            var icons:Sprite = new Sprite ();
            for ( var i:uint=0; i<GameConstants.ICONS; i++ ) {
                var icon:MovieClip = MovieClip(SWFUtils.getLibraryInstance( this.ui, "Icon" + this.model.reelStops[id-1][i] ));
                icons.addChild(icon);
                icon.y = i*100;
            }
            this.ui["mcReel"+id].addChild(icons);
        }

    }
}