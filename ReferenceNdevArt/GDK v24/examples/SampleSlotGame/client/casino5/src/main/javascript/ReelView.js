goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.ReelView");

amaya.game.ReelView = function ( container, model, soundSystem ) {

    var instance = new amaya.api.EventDispatcher();

    var gameServices = new amaya.GameServices();

    /**
     * @type {number}
     */
    var pollIntervalId;

    /**
     * @type {number}
     */
    var spinStartTime;

    /**
     * @type {number}
     */
    var reelSpinSound;

    var json = gameServices.assets.getAsset("device/reels.json");
    var mainjson = gameServices.assets.getAsset("device/main.json");

    amaya.sdk.utils.JSONUtils.fromJSON( container, mainjson.reels );

    var reel1 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel1, json.reel1 );
    container.addChild(reel1);

    var reel2 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel2, json.reel2 );
    container.addChild(reel2);

    var reel3 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel3, json.reel3 );
    container.addChild(reel3);

    var reel4 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel4, json.reel4 );
    container.addChild(reel4);

    var reel5 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel5, json.reel5 );
    container.addChild(reel5);

    var reels = {
        "1": reel1,
        "2": reel2,
        "3": reel3,
        "4": reel4,
        "5": reel5
    };

    instance.forceReels = function () {
        showReel(1);
        showReel(2);
        showReel(3);
        showReel(4);
        showReel(5);
    };
    
    instance.highlightStarIcons = function () {
        var soundPlayed;
        for ( var i=1; i<=amaya.game.GameConstants.REELS; i++ ) {
            for ( var j=0; j<amaya.game.GameConstants.ICONS; j++ ) {
                if ( model.getReelStop(i-1,j) === amaya.game.GameConstants.REEL_STOP_STAR ) {
                    reels[i].getChildAt(0).getChildAt(j).gotoAndPlay("star");
                    if (!soundPlayed) {
                        soundSystem.play(amaya.game.SoundConstants.STAR);
                        soundPlayed = true;
                    }
                }
            }
        }
    };
    
    instance.highlightFreeSpinIcons = function () {
        for ( var i=1; i<=amaya.game.GameConstants.REELS; i++ ) {
            for ( var j=0; j<amaya.game.GameConstants.ICONS; j++ ) {
                if ( model.getReelStop(i-1,j) === amaya.game.GameConstants.REEL_STOP_FREE_SPIN ) {
                    reels[i].getChildAt(0).getChildAt(j).gotoAndPlay("freespin");
                }
            }
        }
    };
    
    instance.spin = function () {
        setTimeout(function(){spinReel(1)},200);
    };

    /**
     * 
     * @param {number} id
     */
    function spinReel ( id ) {
        if ( id == 1 ) {
            reelSpinSound = soundSystem.play(amaya.game.SoundConstants.REEL_SPIN,1000);
        }
        reels[id].removeChildAt(0);

        var blurData = json.blurData;
        blurData.images = [gameServices.assets.getAsset(json.blurData.image)];
        var blurSS = new createjs.SpriteSheet(json.blurData);
        var blur = new createjs.Sprite(blurSS);
        blur.gotoAndPlay(0);

        reels[id].addChild(blur);

        if ( id < amaya.game.GameConstants.REELS ) {
            setTimeout(function(){ spinReel(id+1) },150);
        } else {
            spinStartTime = new Date().getTime();
            pollIntervalId = setInterval(checkForReelStops,100);
        }
    }
    
    function checkForReelStops () {
        var elapsedSpinTime = new Date().getTime() - spinStartTime;
        if ( model.hasReelStops() && elapsedSpinTime >= 1350 ) {
            clearInterval(pollIntervalId);
            stopReel(1);
        }
    }

    /**
     *
     * @param {number} id
     */
    function stopReel ( id ) {
        reels[id].removeChildAt(0);
        showReel(id);
        soundSystem.play(amaya.game.SoundConstants.REEL_STOP);
        if ( id < amaya.game.GameConstants.REELS ) {
            setTimeout(function(){ stopReel(id+1) },200);
        } else {
            reelSpinSound.stop();
            instance.dispatchEvent( new amaya.game.events.SpinCompleteEvent() );
        }
    }

    /**
     *
     * @param {number} id
     */
    function showReel ( id ) {
        var icons = new createjs.Container();
        for ( var i=0; i<amaya.game.GameConstants.ICONS; i++ ) {
            var icon = getIcon(model.getReelStop(id-1,i));
            icons.addChild(icon);
            icon.y = i*json.icons.spacing;
        }
        reels[id].addChild(icons);
    }

    function getIcon ( id ) {
        var iconsData = json.iconsData;
        iconsData.images = [gameServices.assets.getAsset(json.iconsData.image)];
        var iconsSS = new createjs.SpriteSheet(json.iconsData);
        var icon = new createjs.Sprite(iconsSS);
        icon.gotoAndStop(id-1);
        return icon;
    }

    return instance;

};
