/**
 * Created with IntelliJ IDEA.
 * User: amansoor
 * Date: 12/5/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.ReelView");

amaya.game.ReelView = function ( container, symAnimContainer, stickyWildContainer,  model, backgroundContainer, reelStreamContainer, soundManager ) {

    var instance = new amaya.api.EventDispatcher();

    var gameServices = new amaya.GameServices();

    var paylineView;

    instance.getPaylineView = function ( value ) {
        paylineView = value;
    };

   // var json = gameServices.assets.getAsset("device/reels.json");
    var mainjson = gameServices.assets.getAsset("device/main.json");

    amaya.sdk.utils.JSONUtils.fromJSON( container, mainjson.reels );

    var reel1 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel1, mainjson.reel1 );
    container.addChild(reel1);

    var reel2 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel2, mainjson.reel2 );
    container.addChild(reel2);

    var reel3 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel3, mainjson.reel3 );
    container.addChild(reel3);

    var reel4 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel4, mainjson.reel4 );
    container.addChild(reel4);

    var reel5 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( reel5, mainjson.reel5 );
    container.addChild(reel5);

    var reels = {
        "0": reel1,
        "1": reel2,
        "2": reel3,
        "3": reel4,
        "4": reel5
    };

    amaya.sdk.utils.JSONUtils.fromJSON( symAnimContainer, mainjson.reels );

    var animReel1 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( animReel1, mainjson.reel1 );
    symAnimContainer.addChild(animReel1);

    var animReel2 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( animReel2, mainjson.reel2 );
    symAnimContainer.addChild(animReel2);

    var animReel3 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( animReel3, mainjson.reel3 );
    symAnimContainer.addChild(animReel3);

    var animReel4 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( animReel4, mainjson.reel4 );
    symAnimContainer.addChild(animReel4);

    var animReel5 = new createjs.Container();
    amaya.sdk.utils.JSONUtils.fromJSON( animReel5, mainjson.reel5 );
    symAnimContainer.addChild(animReel5);

    var animReels = {
        "0": animReel1,
        "1": animReel2,
        "2": animReel3,
        "3": animReel4,
        "4": animReel5
    };

    var reelAreaMask = new createjs.Shape();
    reelAreaMask.id = "reelMAskShape";
    reelAreaMask.graphics.beginFill("#fff").drawRect(
        mainjson.reelAreaMask1.SlotBoxLeft,
        mainjson.reelAreaMask1.SlotBoxTop,
        mainjson.reelAreaMask1.width + mainjson.reelAreaMask1.reelGap,
         mainjson.reelAreaMask1.height);

    reelAreaMask.graphics.beginFill("#fff").drawRect(
        mainjson.reelAreaMask2.SlotBoxLeft,
        mainjson.reelAreaMask2.SlotBoxTop,
        mainjson.reelAreaMask2.width + mainjson.reelAreaMask2.reelGap,
        mainjson.reelAreaMask2.height);

    reelAreaMask.graphics.beginFill("#fff").drawRect(
        mainjson.reelAreaMask3.SlotBoxLeft,
        mainjson.reelAreaMask3.SlotBoxTop,
        mainjson.reelAreaMask3.width + mainjson.reelAreaMask3.reelGap,
        mainjson.reelAreaMask3.height);
    container.mask = reelAreaMask;

    //container.addChild(reelAreaMask);
    //console.log("Reel conatiner",container);

    var fireAnimContainer = new createjs.Container();
    symAnimContainer.addChild(fireAnimContainer);

    instance.getSymbolData = function () {
        var symbolData = [];
        var iconsData = mainjson.iconsData;
        iconsData.images = [];
        for ( var i = 0; i < mainjson.iconsData.image.length; i++ ) {
            iconsData.images[i] = gameServices.assets.getAsset(mainjson.iconsData.image[i]);
        }
        symbolData = iconsData;
        //console.log("symbolData"+symbolData);
        return symbolData;
    }

    instance.getReelContainer = function () {
        return reels;
    }

    instance.getSymAnimContainer = function () {
        return animReels;
    }

    /*function getIcon ( id ) {
        var iconsData = mainjson.iconsData;
        iconsData.images = [gameServices.assets.getAsset(mainjson.iconsData.image)];
        var iconsSS = new createjs.SpriteSheet(mainjson.iconsData);
        var icon = new createjs.Sprite(iconsSS);
        icon.gotoAndStop(id);
        return icon;
    }
*/
    instance.playFireAnimOnReel = function () {
        var animData = mainjson.fireAnimData;
        fireAnimContainer.removeAllChildren();
        animData.images = [gameServices.assets.getAsset(mainjson.fireAnimData.image)];
        var spriteSheet = new createjs.SpriteSheet(animData);
        var sprite = new createjs.Sprite(spriteSheet);
        sprite.x = animData.pos.x;
        sprite.y = animData.pos.y;
        sprite.scaleX = animData.scale.x;
        sprite.scaleY = animData.scale.y;
        sprite.gotoAndPlay("lightFire");
        fireAnimContainer.addChild(sprite);
    }

    /*instance.stopFireAnimOnReel = function () {
        fireAnimContainer.removeAllChildren();
    }
*/
    function getScatterData ( ) {
        var scatterData = mainjson.scatterTriggerData;
        scatterData.images = [gameServices.assets.getAsset(mainjson.scatterTriggerData.image)];
        var scatterAnimSS = new createjs.SpriteSheet(mainjson.scatterTriggerData);
        var scatterAnim = new createjs.Sprite(scatterAnimSS);
        scatterAnim.gotoAndStop(0);
        scatterAnim.gotoAndPlay("scatterAnim");
        return scatterAnim;
    }

    instance.playScatterAnimation = function () {
        console.log("Play Scatter Animation");
        var icons1 = new createjs.Container();
        var icons2 = new createjs.Container();
        var icons3 = new createjs.Container();
        var icons4 = new createjs.Container();
        var icons5 = new createjs.Container();
        var icons = {
            "0": icons1,
            "1": icons2,
            "2": icons3,
            "3": icons4,
            "4": icons5
        };
       // var scatterPos; // use this commented one if no. of scatter positions are not more than no. of reels
        /*for ( var i = 0; i<model.getWinningLines().length; i++) {
            if (model.getWinningLines()[i].icon === amaya.game.GameConstants.SYM_SCATTER)
            scatterPos = model.getWinningLines()[i].positions;
        }*/
        //check on reels 2,3,4 (i.e 1,2,3 as starting from 0) for scatter and play animation
        for (var i=1; i<amaya.game.GameConstants.REELS - 1; i++) {
            scatterPos = [];
            temp = [];
            scatterPos = model.getReelStops() ;
            temp = scatterPos[i];
            for (var j=0; j<amaya.game.GameConstants.ICONS; j++) {
                var icon;
                if ( model.getReelStop(i,j) === amaya.game.GameConstants.SYM_SCATTER ) {
                    paylineView.showScatterMarkerBg( i, temp);
                    icon = getScatterData();
                    icon.y = j*mainjson.icons.spacing;
                    icons[i].addChild(icon);
                    animReels[i].addChild(icons[i]);
                    icon.addEventListener("animationend",function () {
                        instance.stopScatterAnimation();
                        instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
                    });
                    var reelIcon = reels[i].getChildAt(0).getChildAt(j);
                    reelIcon.alpha = 0;
                }
            }
        }
    };

     function getScatterHalfData ( ) {
            var scatterHalfData = mainjson.scatterTriggerHalfData;
            scatterHalfData.images = [gameServices.assets.getAsset(mainjson.scatterTriggerHalfData.image)];
            var scatterHalfAnimSS = new createjs.SpriteSheet(scatterHalfData);
            var scatterHalfAnim = new createjs.Sprite(scatterHalfAnimSS);
            scatterHalfAnim.gotoAndStop(0);
            scatterHalfAnim.gotoAndPlay("scatterHalf");
            return scatterHalfAnim;
        }

  //  var allscatterPos = [];
  var temp = [];

    instance.playScatterHalfAnim = function (id) {
        console.log("Play ScatterHalf Anim");
        var icons1 = new createjs.Container();
        var icons2 = new createjs.Container();
        var icons3 = new createjs.Container();
        var icons4 = new createjs.Container();
        var icons5 = new createjs.Container();
        var icons = {
            "0": icons1,
            "1": icons2,
            "2": icons3,
            "3": icons4,
            "4": icons5
        };
        scatterPos = [];
        temp = [];
        scatterPos = model.getReelStops() ;
        temp = scatterPos[id];
       // allscatterPos = allscatterPos.concat(temp);

        console.log("playScatterHalfAnim temp",temp);

        for (var i=0; i<temp.length; i++) {
                var icon;
                if ( temp[i] === amaya.game.GameConstants.SYM_SCATTER ) {
                    paylineView.showScatterMarkerBg(id, temp);
                    icon = getScatterHalfData();
                    icon.y = i*mainjson.icons.spacing;
                    console.log("playScatterHalfAnim icon.y ",icon.y);
                    icons[id].addChild(icon);
                    animReels[id].addChild(icons[id]);
                    var reelIcon = reels[id].getChildAt(0).getChildAt(i);
                    reelIcon.alpha = 0;
                }
        }
    };

    /*instance.stopScatterHalfAnim = function () {
            console.log("stop ScatterHalf Anim");

            var scatterPos = model.getReelStops() ;

            console.log("stopScatterHalfAnim allscatterPos",allscatterPos);
            for (var i=0; i<allscatterPos.length; i++) {
                    var icon;
                    if ( allscatterPos[i] === amaya.game.GameConstants.SYM_SCATTER ) {
                        var reelIcon = reels[i].getChildAt(0).getChildAt(i);
                        reelIcon.alpha = 1;
                    }
            }
            for ( var i=0; i<allscatterPos.length; i++ ) {
                do
                  {
                    animReels[i].removeChildAt(0);
                  }
                while (animReels[i].getChildAt(0));
            }

           // allscatterPos = [];
        };*/


    instance.stopScatterAnimation = function () {
        for (var i=0; i<amaya.game.GameConstants.REELS; i++) {
            for (var j=0; j<amaya.game.GameConstants.ICONS; j++) {
                if ( model.getReelStop(i,j) === amaya.game.GameConstants.SYM_SCATTER ) {
                    var reelIcon = reels[i].getChildAt(0).getChildAt(j);
                    reelIcon.alpha = 1;
                }
            }
        }
		for ( var i=0; i<amaya.game.GameConstants.REELS; i++ ) {
			do
              {
    			animReels[i].removeChildAt(0);
              }
            while (animReels[i].getChildAt(0));
		}
		temp = [];
    };


    function getReelAnticipationData ( ) {
            var reelAnticipationData = mainjson.reelAnticipationData;
            reelAnticipationData.images = [gameServices.assets.getAsset(mainjson.reelAnticipationData.image)];
            var reelAnticipationSS = new createjs.SpriteSheet(mainjson.reelAnticipationData);
            var reelAnticipation = new createjs.Sprite(reelAnticipationSS);
            reelAnticipation.gotoAndStop(0);
            reelAnticipation.gotoAndPlay("reelAnticipation");
            return reelAnticipation;
        }

        var reelAnimation;
        instance.playReelAnticipation = function (id) {
            reelAnimation = getReelAnticipationData();
            reelAnimation.y = mainjson.reel4.y - mainjson.reelAnticipationData.spacing.y;
            reelAnimation.x = mainjson.reel4.x - mainjson.reelAnticipationData.spacing.x;
            reelAnimation.scaleX = mainjson.reelAnticipationData.scale.x;
            reelAnimation.scaleY = mainjson.reelAnticipationData.scale.y;
            backgroundContainer.addChild(reelAnimation);
            soundManager.PlayAnticipation();
        };

        instance.stopReelAnticipation = function () {
            soundManager.StopAnticipation();
            backgroundContainer.removeChild(reelAnimation);
        };

        function getBlueStreamData (x1) {
                var reelBlueStreamData = mainjson.BlueStreamData;
                if(x1==reel3){
                reelBlueStreamData.images = [gameServices.assets.getAsset(mainjson.BlueStreamDataSmall.image)];
                }else{
                reelBlueStreamData.images = [gameServices.assets.getAsset(mainjson.BlueStreamData.image)];
                }
                var  reelBlueStreamSS = new createjs.SpriteSheet(reelBlueStreamData);
                var reelBlueStream = new createjs.Sprite( reelBlueStreamSS);
                reelBlueStream.gotoAndStop(0);
                reelBlueStream.gotoAndPlay("reelBlueStreamAnim");
                return reelBlueStream;
        }

        instance.playBlueStream = function(x1){
            soundManager.PlayReelSrikeSound();
            var blueStream = getBlueStreamData(x1);
            //console.log("playBlueStream ",x1);
            blueStream.y =  mainjson[x1].y;
            blueStream.x =   mainjson[x1].x;
            blueStream.scaleX = mainjson.BlueStreamData.scale.x;
            blueStream.scaleY = mainjson.BlueStreamData.scale.y;
            reelStreamContainer.addChild(blueStream);
            blueStream.addEventListener("animationend",function () {
                                    reelStreamContainer.removeChild(blueStream);
                                });

        }




/*
 function getReelAnticipationData ( ) {
            var reelAnticipationData = mainjson.reelAnticipationData;
            reelAnticipationData.images = [gameServices.assets.getAsset(mainjson.reelAnticipationData.image)];
            var reelAnticipationSS = new createjs.SpriteSheet(mainjson.reelAnticipationData);
            var reelAnticipation = new createjs.Sprite(reelAnticipationSS);
            reelAnticipation.gotoAndStop(0);
            reelAnticipation.gotoAndPlay("reelAnticipation");
            return reelAnticipation;
        }

        var reelAnimation;
        instance.playReelAnticipation = function (id) {
            reelAnimation = getReelAnticipationData();
            reelAnimation.y = mainjson.reel4.y - mainjson.reelAnticipationData.spacing.y;
            reelAnimation.x = mainjson.reel4.x - mainjson.reelAnticipationData.spacing.x;
            reelAnimation.scaleX = mainjson.reelAnticipationData.scale.x;
            reelAnimation.scaleY = mainjson.reelAnticipationData.scale.y;
            backgroundContainer.addChild(reelAnimation);
            soundManager.PlayAnticipation();
        };
        */

                function getStickyWildAnimData () {
                    var stickyWildAnimData = mainjson.stickyWildAnimData;
                    stickyWildAnimData.images = [gameServices.assets.getAsset(mainjson.stickyWildAnimData.image)];
                    var stickyWildAnimSS = new createjs.SpriteSheet(mainjson.stickyWildAnimData);
                    var stickyWildAnim = new createjs.Sprite(stickyWildAnimSS);
                    stickyWildAnim.gotoAndStop(0);
                    stickyWildAnim.gotoAndPlay("stickyWildAnim");
                    return stickyWildAnim;
                };
                var stickyWildAnimAnimation;
                instance.playStickyWildAnim = function (id) {
                    stickyWildAnimAnimation = getStickyWildAnimData();
                    stickyWildAnimAnimation.y = mainjson.reel3.y - mainjson.stickyWildAnimData.spacing.y;
                    stickyWildAnimAnimation.x = mainjson.reel3.x - mainjson.stickyWildAnimData.spacing.x;
                    stickyWildAnimAnimation.scaleX = mainjson.stickyWildAnimData.scale.x;
                    stickyWildAnimAnimation.scaleY = mainjson.stickyWildAnimData.scale.y;
                    stickyWildContainer.addChild(stickyWildAnimAnimation);
                    //soundManager.PlayAnticipation();
                };


                function getStickyWildLoopData () {
                    var stickyWildLoopData = mainjson.stickyWildLoopData;
                    stickyWildLoopData.images = [gameServices.assets.getAsset(mainjson.stickyWildLoopData.image)];
                    var stickyWildLoopSS = new createjs.SpriteSheet(mainjson.stickyWildLoopData);
                    var stickyWildLoop = new createjs.Sprite(stickyWildLoopSS);
                    stickyWildLoop.gotoAndStop(0);
                    stickyWildLoop.gotoAndPlay("stickyWildLoop");
                    return stickyWildLoop;
                };
                var stickyWildLoopAnimation;
                instance.playStickyWildLoop = function (id) {
                    stickyWildLoopAnimation = getStickyWildLoopData();
                    stickyWildLoopAnimation.y = mainjson.reel3.y - mainjson.stickyWildLoopData.spacing.y;
                    stickyWildLoopAnimation.x = mainjson.reel3.x - mainjson.stickyWildLoopData.spacing.x;
                    stickyWildLoopAnimation.scaleX = mainjson.stickyWildLoopData.scale.x;
                    stickyWildLoopAnimation.scaleY = mainjson.stickyWildLoopData.scale.y;
                    stickyWildContainer.addChild(stickyWildLoopAnimation);
                    //soundManager.PlayAnticipation();
                };

                function getStickyWildStartData () {
                    var stickyWildStartData = mainjson.stickyWildStartData;
                    stickyWildStartData.images = [gameServices.assets.getAsset(mainjson.stickyWildStartData.image)];
                    var stickyWildStartSS = new createjs.SpriteSheet(mainjson.stickyWildStartData);
                    var stickyWildStart = new createjs.Sprite(stickyWildStartSS);
                    stickyWildStart.gotoAndStop(0);
                    stickyWildStart.gotoAndPlay("stickyWildStart");
                    return stickyWildStart;
                };
                var stickyWildStartAnimation;
                instance.playStickyWildStart = function (id) {
                    stickyWildStartAnimation = getStickyWildStartData();
                    stickyWildStartAnimation.y = mainjson.reel3.y - mainjson.stickyWildStartData.spacing.y;
                    stickyWildStartAnimation.x = mainjson.reel3.x - mainjson.stickyWildStartData.spacing.x;
                    stickyWildStartAnimation.scaleX = mainjson.stickyWildStartData.scale.x;
                    stickyWildStartAnimation.scaleY = mainjson.stickyWildStartData.scale.y;
                    stickyWildContainer.addChild(stickyWildStartAnimation);
                    //soundManager.PlayAnticipation();
                };


    return instance;
}
