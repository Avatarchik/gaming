// Note: The amaya and amaya.game objects are already created on the page before the game
// script is ever loaded. Those objects are declared here again to satisfy the Google Closure Compiler
// so that it can sort the dependencies properly. Without them, it optimizes the scripts during compilation
// and injects it's own declaration of the amaya object which overwrites the current one on the page,
// effectively killing the entire framework.
amaya = amaya || {};
amaya.game = amaya.game || {};
amaya.game.events = amaya.game.events || {};
amaya.game.messaging = amaya.game.messaging || {};
amaya.game.BackgroundView = function ( container, logoContainer ) {
    var instance = {};
    var gameServices = new amaya.GameServices();
    var mainSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Bg_reel.jpg") );
    container.addChild(mainSpinBackground);
    var freeSpinBackground = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Freegame.jpg") );
    var json = gameServices.assets.getAsset("device/main.json");
    var gamelogo = new createjs.Bitmap( gameServices.assets.getAsset("device/images/logo.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( gamelogo, json.gamelogo );
    //var powerlogo = new createjs.Bitmap( gameServices.assets.getAsset("device/images/powerLogo.png") );
    //amaya.sdk.utils.JSONUtils.fromJSON( powerlogo, json.powerlogo );
    instance.showMainSpinBackground = function () {
        if ( container.contains(freeSpinBackground) ) {
            container.removeChild(freeSpinBackground);
        }
        if ( !container.contains(mainSpinBackground) ) {
            container.addChild(mainSpinBackground);
        }
    };
    instance.showFreeSpinBackground = function () {
        if ( container.contains(mainSpinBackground) ) {
            container.removeChild(mainSpinBackground);
        }
        if ( !container.contains(freeSpinBackground) ) {
            container.addChild(freeSpinBackground);
        }
    };
    instance.hideLogo = function () {
        logoContainer.removeChildAt(0);
    }
    instance.showLogo = function () {
       // if (logoContainer.getNumChildren() === 0)
            logoContainer.addChild(gamelogo);
            //logoContainer.addChild(powerlogo);
    }
    return instance;
};
/**
 * Created with IntelliJ IDEA.
 * User: amansoor
 * Date: 12/11/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
amaya.game.CelebrationWindow = function ( container, model ,soundManager) {
    var instance = new amaya.api.EventDispatcher();
    var gameServices = new amaya.GameServices();
    var CelebrationWindow = new createjs.Container();
    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");
    var celebrationWindowData = json.celebrationWindowData;
    var celebrationWindowIntroData = json.celebrationWindowIntroData;
    var celebrationOutroWindowData = json.celebrationOutroWindowData;
    var FreeIntroBg =  new createjs.Bitmap(gameServices.assets.getAsset("device/images/Celebration_win/Celebration_windowIntroBG.jpg"));
    var CelebrationIntroScreen =  new createjs.Bitmap(gameServices.assets.getAsset(celebrationWindowIntroData.image));
    amaya.framework.utils.JSON.fromJSON( CelebrationIntroScreen,celebrationWindowIntroData.position );
    var FreeIntroScreen = new createjs.Bitmap(gameServices.assets.getAsset(celebrationWindowData.image));
    amaya.framework.utils.JSON.fromJSON( FreeIntroScreen, celebrationWindowData.position );
     var FreeRetrigBg =  new createjs.Bitmap(gameServices.assets.getAsset("device/images/Celebration_win/Celebration_windowIntroBG.jpg"));
    var FreeOutroBg =  new createjs.Bitmap(gameServices.assets.getAsset("device/images/Celebration_win/summary.jpg"));
    var FreeOutroScreen = new createjs.Bitmap(gameServices.assets.getAsset(celebrationOutroWindowData.image));
    //amaya.framework.utils.JSON.fromJSON( container, celebrationOutroWindowData.position );
   // container.x = json.reel1.x;
  //  container.y = json.reels.y;
 // celebrationWindowIntroData.position.y=json.reels.y;
    //CelebrationWindow.addEventListener("click",ClickToStart);
    var tfContinueShadow = new createjs.Text(langFile.tap_to_continue);
    amaya.sdk.utils.JSONUtils.fromJSON( tfContinueShadow, json.tfContinueShadow );
    var tfContinue = new createjs.Text(langFile.tap_to_continue);
    amaya.sdk.utils.JSONUtils.fromJSON( tfContinue, json.tfContinue );
    //tfContinue.text = langFile.tap_to_continue;
    var banner = new createjs.Container();
  //  banner.visible = false;
    /*instance.playFireWolfAnimOnReel = function () {
        instance.showFreeSpinIntro(model.getFreeSpinsTotal());
        soundManager.PlayFireWolfSound();
        var animData = json.fireWolfAnimData;
        animData.images = [gameServices.assets.getAsset(json.fireWolfAnimData.image)];
        var spriteSheet = new createjs.SpriteSheet(animData);
        var sprite = new createjs.Sprite(spriteSheet);
        sprite.x = animData.pos.x;
        sprite.y = animData.pos.y;
        sprite.scaleX = animData.scale.x;
        sprite.scaleY = animData.scale.y;
        container.addChild(CelebrationWindow);
        CelebrationWindow.addChild(sprite);
        sprite.gotoAndPlay("introAnim");
        sprite.addEventListener("animationend",function(){
            endFireWolfAnimOnReel();
        });
    }*/
    instance.showFreeSpinIntro = function(numFreeSpinsWon){
        /*number of free spins won */
        var tfFsWon1 = new createjs.Text();
        var tfFsWon2 = new createjs.Text();
        var tfFsWon3 = new createjs.Text();
        var tfFsWon4 = new createjs.Text("X");
        var tfFsWon5 = new createjs.Text();
        var tfFsWon6 = new createjs.Text();
        var tfFsWon7 = new createjs.Text();
        var tfFsWon8 = new createjs.Text();
        var tfFsWon9 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon1, json.tfFsWon1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon2, json.tfFsWon2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon3, json.tfFsWon3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon4, json.tfFsWon4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon5, json.tfFsWon5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon6, json.tfFsWon6 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon7, json.tfFsWon7 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon8, json.tfFsWon8 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWon9, json.tfFsWon9 );
        /*number of free spins won */
        var tfFsWonShadow1 = new createjs.Text();
        var  tfFsWonShadow2 = new createjs.Text();
        var  tfFsWonShadow3 = new createjs.Text();
        var  tfFsWonShadow4 = new createjs.Text("X");
        var  tfFsWonShadow5 = new createjs.Text();
        var  tfFsWonShadow6 = new createjs.Text();
        var  tfFsWonShadow7 = new createjs.Text();
        var  tfFsWonShadow8 = new createjs.Text();
        var  tfFsWonShadow9 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow1, json.tfFsWonShadow1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow2, json.tfFsWonShadow2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow3, json.tfFsWonShadow3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow4, json.tfFsWonShadow4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow5, json.tfFsWonShadow5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow6, json.tfFsWonShadow6 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow7, json.tfFsWonShadow7 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow8, json.tfFsWonShadow8 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsWonShadow9, json.tfFsWonShadow9 );
        tfFsWon1.text = langFile.freespin_outro_1;
        tfFsWonShadow1.text = langFile.freespin_outro_1;
        //tfFsWon2.text = 10;
        //tfFsWonShadow2.text = 10;
        tfFsWon2.text = numFreeSpinsWon/10;
        tfFsWonShadow2.text = numFreeSpinsWon/10;
        //tfFsWon3.text = langFile.freespin_intro_2;
        //tfFsWonShadow3.text = langFile.freespin_intro_2;
        tfFsWon3.text = langFile.freespin_intro_3;
        tfFsWonShadow3.text = langFile.freespin_intro_3;
        //use 4 for image
        var fsintro_ad_image1 = new createjs.Bitmap(gameServices.assets.getAsset("device/images/introBonus.png"));
        amaya.sdk.utils.JSONUtils.fromJSON( fsintro_ad_image1, json.fsintro_ad_image1 );
        tfFsWon5.text = langFile.freespin_intro_4;
        tfFsWonShadow5.text = langFile.freespin_intro_4;
        tfFsWon6.text = 10;
        tfFsWonShadow6.text = 10;
        tfFsWon7.text = langFile.freespin_intro_2;
        tfFsWonShadow7.text = langFile.freespin_intro_2;
        tfFsWon8.text =  langFile.freespin_intro_1;
        tfFsWonShadow8.text = langFile.freespin_intro_1;
        //9 FOR IMAGE
        var fsintro_ad_image2 = new createjs.Bitmap(gameServices.assets.getAsset("device/images/xStaticWild.png"));
        amaya.sdk.utils.JSONUtils.fromJSON( fsintro_ad_image2, json.fsintro_ad_image2 );
        //tfFsWon9.text =  langFile.freespin_intro_4;
        //tfFsWonShadow9.text =  langFile.freespin_intro_4;
        var hrLine = new createjs.Shape();
        hrLine.id = "hrLineShape";
        hrLine.graphics.beginFill("#000").drawRect(
        json.hrLineShape.Left,
        json.hrLineShape.Top,
        json.hrLineShape.width,
        json.hrLineShape.height);
        //ADd text containers
        banner.addChild(tfFsWon1);
        banner.addChild(tfFsWonShadow1);
        banner.addChild(tfFsWon2);
        banner.addChild(tfFsWonShadow2);
        banner.addChild(tfFsWon3);
        banner.addChild(tfFsWonShadow3);
        banner.addChild(fsintro_ad_image1);
        //banner.addChild(tfFsWonShadow4);
        banner.addChild(tfFsWon5);
        banner.addChild(tfFsWonShadow5);
        banner.addChild(tfFsWon6);
        banner.addChild(tfFsWonShadow6);
        banner.addChild(tfFsWon7);
        banner.addChild(tfFsWonShadow7);
        banner.addChild(tfFsWon8);
        banner.addChild(tfFsWonShadow8);
        banner.addChild(fsintro_ad_image2);
        //banner.addChild(tfFsWonShadow9);
        //banner.addChild(hrLine);
       soundManager.PlayFreeSpinSound();
        CelebrationIntroScreen.alpha = 0;
        banner.alpha =0;
        CelebrationWindow.addChild(FreeIntroBg);
        CelebrationWindow.addChild(CelebrationIntroScreen);
        CelebrationWindow.addChild(banner);
        container.addChild(CelebrationWindow);
        var alphaInterval = setInterval ( function () {
            CelebrationIntroScreen.alpha = CelebrationIntroScreen.alpha + 0.02;
            banner.alpha = banner.alpha + 0.02;
        },10);
        setTimeout ( function () {
            clearInterval(alphaInterval);
            CelebrationIntroScreen.alpha = 1;
             banner.alpha =1;
        },500);
        setTimeout ( function () {
                       instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
                        instance.hideFreeSpinIntro();
                },3000);
    }
    instance.hideFreeSpinIntro = function(){
        banner.removeAllChildren();
        CelebrationWindow.removeAllChildren();
        container.removeChild(CelebrationWindow);
    }
     /*function endFireWolfAnimOnReel ( ) {
        var fireWolfEndAnimContainer = new createjs.Container();
        var animData = json.fireWolfAnimDataEnd;
        animData.images = [gameServices.assets.getAsset(json.fireWolfAnimDataEnd.image)];
        var spriteSheet = new createjs.SpriteSheet(animData);
        var sprite = new createjs.Sprite(spriteSheet);
        sprite.x = animData.pos.x;
        sprite.y = animData.pos.y;
        sprite.scaleX = animData.scale.x;
        sprite.scaleY = animData.scale.y;
        CelebrationWindow.removeAllChildren();
        CelebrationWindow.addChild(sprite);
        sprite.gotoAndStop(0);
        soundManager.PlayFreeSpinSound();
        CelebrationWindow.addChild(banner);
        banner.visible = true;
        setTimeout(function(){
           banner.removeAllChildren();
           CelebrationWindow.removeChild(banner);
           sprite.gotoAndPlay("introAnimEnd2");
           soundManager.PlayFreeSpinIntroSound();
           sprite.addEventListener("animationend",function () {
               instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
               CelebrationWindow.removeAllChildren();
           });
        },3500);
    }*/
  /* var summaryBanner = new createjs.Container();
   amaya.framework.utils.JSON.fromJSON( summaryBanner, celebrationOutroWindowData.position );
      summaryBanner.visible = false;
     instance.playFreeSpinSummmary = function () {
            instance.showFreeSpinOutro(model.getFreeSpinsWon(),model.getFreeSpinsTotal());
            var animData = json.fireWolfSummaryData;
            animData.images = [gameServices.assets.getAsset(json.fireWolfSummaryData.image)];
            var spriteSheet = new createjs.SpriteSheet(animData);
            var sprite = new createjs.Sprite(spriteSheet);
            sprite.x = animData.pos.x;
            sprite.y = animData.pos.y;
            sprite.scaleX = animData.scale.x;
            sprite.scaleY = animData.scale.y;
            sprite.gotoAndPlay("outroAnim");
            container.addChild(CelebrationWindow);
            CelebrationWindow.addChild(sprite);
            sprite.addEventListener("animationend",function () {
                CelebrationWindow.addChild(summaryBanner);
                summaryBanner.visible = true;
                setTimeout(function(){
                    summaryBanner.removeAllChildren();
                    instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
                    CelebrationWindow.removeAllChildren();
                },2000);
                    //instance.fireWolfSummaryEndData();
            });
    }*/
     var summaryBanner = new createjs.Container();
       amaya.framework.utils.JSON.fromJSON( summaryBanner, celebrationOutroWindowData.position );
    instance.showFreeSpinOutro = function(wonAmount,numFreeSpinsWon){
        /*number of free spins won */
        var tfFsAmountWon1 = new createjs.Text();
        var tfFsAmountWon2 = new createjs.Text();
        var tfFsAmountWon3 = new createjs.Text();
        var tfFsAmountWon4 = new createjs.Text();
        var tfFsAmountWon5 = new createjs.Text();
        var tfFsAmountWon6 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon1, json.tfFsAmountWon1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon2, json.tfFsAmountWon2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon3, json.tfFsAmountWon3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon4, json.tfFsAmountWon4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon5, json.tfFsAmountWon5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWon6, json.tfFsAmountWon6 );
        /*number of free spins won */
        var tfFsAmountWonShadow1 = new createjs.Text();
        var tfFsAmountWonShadow2 = new createjs.Text();
        var tfFsAmountWonShadow3 = new createjs.Text();
        var tfFsAmountWonShadow4 = new createjs.Text();
        var tfFsAmountWonShadow5 = new createjs.Text();
        var tfFsAmountWonShadow6 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow1, json.tfFsAmountWonShadow1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow2, json.tfFsAmountWonShadow2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow3, json.tfFsAmountWonShadow3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow4, json.tfFsAmountWonShadow4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow5, json.tfFsAmountWonShadow5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsAmountWonShadow6, json.tfFsAmountWonShadow6 );
        tfFsAmountWon1.text = langFile.freespin_outro_1;
        tfFsAmountWonShadow1.text = langFile.freespin_outro_1;
        tfFsAmountWon2.text = wonAmount;
        tfFsAmountWonShadow2.text = wonAmount;
        tfFsAmountWon3.text = langFile.freespin_outro_3;
        tfFsAmountWonShadow3.text = langFile.freespin_outro_3;
        tfFsAmountWon4.text = langFile.freespin_outro_4;
        tfFsAmountWonShadow4.text = langFile.freespin_outro_4;
        //tfFsAmountWon5.text = numFreeSpinsWon;
        //tfFsAmountWonShadow5.text = numFreeSpinsWon;
        tfFsAmountWon5.text = langFile.freespin_outro_5;
        tfFsAmountWonShadow5.text = langFile.freespin_outro_5;
        tfFsAmountWon6.text = langFile.freespin_intro_2;
        tfFsAmountWonShadow6.text = langFile.freespin_intro_2;
        summaryBanner.addChild(FreeOutroScreen);
        //ADd text containers
        summaryBanner.addChild(tfFsAmountWonShadow1);
        summaryBanner.addChild(tfFsAmountWonShadow2);
        summaryBanner.addChild(tfFsAmountWonShadow3);
        summaryBanner.addChild(tfFsAmountWonShadow4);
        summaryBanner.addChild(tfFsAmountWonShadow5);
        summaryBanner.addChild(tfFsAmountWonShadow6);
        summaryBanner.addChild(tfFsAmountWon1);
        summaryBanner.addChild(tfFsAmountWon2);
        summaryBanner.addChild(tfFsAmountWon3);
        summaryBanner.addChild(tfFsAmountWon4);
        summaryBanner.addChild(tfFsAmountWon5);
        summaryBanner.addChild(tfFsAmountWon6);
       // CelebrationWindow.addChild(tfContinueShadow);
       // CelebrationWindow.addChild(tfContinue);
       // container.addChild(CelebrationWindow);
       //CelebrationIntroScreen.alpha = 0;
           summaryBanner.alpha =0;
           CelebrationWindow.addChild(FreeOutroBg);
           CelebrationWindow.addChild(summaryBanner);
           container.addChild(CelebrationWindow);
           var alphaInterval = setInterval ( function () {
               summaryBanner.alpha = summaryBanner.alpha + 0.02;
           },10);
           setTimeout ( function () {
               clearInterval(alphaInterval);
                summaryBanner.alpha =1;
           },500);
           setTimeout ( function () {
                          instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
                           instance.hideFreeSpinOutro();
           },3000);
    }
    instance.hideFreeSpinOutro = function(){
        summaryBanner.removeAllChildren();
        CelebrationWindow.removeAllChildren();
        container.removeChild(CelebrationWindow);
    }
    instance.showFreeSpinRetrigged = function(numExtraFreeSpinsWon){
        /*number of free spins won */
         var tfFsRetrigWon0 = new createjs.Text();
        var tfFsRetrigWon1 = new createjs.Text();
        var tfFsRetrigWon2 = new createjs.Text();
        var tfFsRetrigWon3 = new createjs.Text("X");
        var tfFsRetrigWon4 = new createjs.Text();
        var tfFsRetrigWon5 = new createjs.Text();
        var tfFsRetrigWon6 = new createjs.Text();
        var tfFsRetrigWon7 = new createjs.Text();
        var tfFsRetrigWon8 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon0, json.tfFsRetrigWon0 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon1, json.tfFsRetrigWon1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon2, json.tfFsRetrigWon2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon3, json.tfFsRetrigWon3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon4, json.tfFsRetrigWon4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon5, json.tfFsRetrigWon5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon6, json.tfFsRetrigWon6 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon7, json.tfFsRetrigWon7 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWon8, json.tfFsRetrigWon8 );
        //*number of free spins won *//*
         var  tfFsRetrigWonShadow0 = new createjs.Text();
        var  tfFsRetrigWonShadow1 = new createjs.Text();
        var  tfFsRetrigWonShadow2 = new createjs.Text();
        var  tfFsRetrigWonShadow3 = new createjs.Text("X");
        var  tfFsRetrigWonShadow4 = new createjs.Text();
        var  tfFsRetrigWonShadow5 = new createjs.Text();
        var  tfFsRetrigWonShadow6 = new createjs.Text();
        var  tfFsRetrigWonShadow7 = new createjs.Text();
        var  tfFsRetrigWonShadow8 = new createjs.Text();
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow0, json.tfFsRetrigWonShadow0 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow1, json.tfFsRetrigWonShadow1 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow2, json.tfFsRetrigWonShadow2 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow3, json.tfFsRetrigWonShadow3 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow4, json.tfFsRetrigWonShadow4 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow5, json.tfFsRetrigWonShadow5 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow6, json.tfFsRetrigWonShadow6 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow7, json.tfFsRetrigWonShadow7 );
        amaya.sdk.utils.JSONUtils.fromJSON( tfFsRetrigWonShadow8, json.tfFsRetrigWonShadow8 );
        tfFsRetrigWon0.text = langFile.freespin_outro_1;
        tfFsRetrigWonShadow0.text = langFile.freespin_outro_1;
        tfFsRetrigWon1.text = 8;
        tfFsRetrigWonShadow1.text = 8;
        tfFsRetrigWon2.text = langFile.freespin_retrig_1;
        tfFsRetrigWonShadow2.text = langFile.freespin_retrig_1;
        tfFsRetrigWon4.text = numExtraFreeSpinsWon/8;
        tfFsRetrigWonShadow4.text = numExtraFreeSpinsWon/8;
        tfFsRetrigWon5.text =langFile.freespin_intro_3;
        tfFsRetrigWonShadow5.text = langFile.freespin_intro_3;
        tfFsRetrigWon6.text = numExtraFreeSpinsWon;
        tfFsRetrigWonShadow6.text = numExtraFreeSpinsWon;
        tfFsRetrigWon7.text =  langFile.freespin_retrig_3;
        tfFsRetrigWonShadow7.text = langFile.freespin_retrig_3;
        tfFsRetrigWon8.text =  langFile.freespin_intro_4;
        tfFsRetrigWonShadow8.text =  langFile.freespin_intro_4;
        var hrLineRetrig = new createjs.Shape();
        hrLineRetrig.id = "hrLineRetrigShape";
        hrLineRetrig.graphics.beginFill("#000").drawRect(
        json.hrLineRetrigShape.Left,
        json.hrLineRetrigShape.Top,
        json.hrLineRetrigShape.width,
        json.hrLineRetrigShape.height);
        CelebrationWindow.addChild(FreeRetrigBg);
        CelebrationWindow.addChild(FreeIntroScreen);
        //ADd text containers
         CelebrationWindow.addChild(tfFsRetrigWonShadow0);
        CelebrationWindow.addChild(tfFsRetrigWonShadow1);
        CelebrationWindow.addChild(tfFsRetrigWonShadow2);
        CelebrationWindow.addChild(tfFsRetrigWonShadow3);
        CelebrationWindow.addChild(tfFsRetrigWonShadow4);
        CelebrationWindow.addChild(tfFsRetrigWonShadow5);
        CelebrationWindow.addChild(tfFsRetrigWonShadow6);
        CelebrationWindow.addChild(tfFsRetrigWonShadow7);
        CelebrationWindow.addChild(tfFsRetrigWonShadow8);
        CelebrationWindow.addChild(tfFsRetrigWon0);
        CelebrationWindow.addChild(tfFsRetrigWon1);
        CelebrationWindow.addChild(tfFsRetrigWon2);
        CelebrationWindow.addChild(tfFsRetrigWon3);
        CelebrationWindow.addChild(tfFsRetrigWon4);
        CelebrationWindow.addChild(tfFsRetrigWon5);
        CelebrationWindow.addChild(tfFsRetrigWon6);
        CelebrationWindow.addChild(tfFsRetrigWon7);
        CelebrationWindow.addChild(tfFsRetrigWon8);
        CelebrationWindow.addChild(hrLineRetrig);
         //CelebrationWindow.addChild(tfContinueShadow);
        //CelebrationWindow.addChild(tfContinue);
        container.addChild(CelebrationWindow);
    }
    instance.hideFreeSpinRetriggered = function(){
        CelebrationWindow.removeAllChildren();
        container.removeChild(CelebrationWindow);
    }
    function ClickToStart(event){
        instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
    }
    return instance;
};
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
amaya.game.FreeSpinView = function ( container, model ) {
    var instance = {};
    var gameServices = new amaya.GameServices ();
    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");
	var temp = 0;
	var win =0;
	var num =0;
	var freeinterval;
	var freeTimer;
	var FGdivider =0;
	var FGdiv=0;
	var totalWon;
    /*
    var rightMask = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Mask/Right_CornerFeathers.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( rightMask, json.rightMask );
    container.addChild(rightMask);
    var leftMask = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Mask/Left_CornerFeathers.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( leftMask, json.leftMask );
    container.addChild(leftMask);
    */
    var freeGamesMeter = new createjs.Bitmap(gameServices.assets.getAsset("device/images/Win_meter.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( freeGamesMeter, json.freeGamesMeter );
    //container.addChild(freeGamesMeter);
    var tfCounterShadow = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounterShadow, json.tfCounterShadow );
    var tfCounter = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounter, json.tfCounter );
    var tfCounterTextShadow = new createjs.Text(langFile.FreeSpin);
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounterTextShadow, json.tfCounterTextShadow );
    var tfCounterText = new createjs.Text(langFile.FreeSpin);
    amaya.sdk.utils.JSONUtils.fromJSON( tfCounterText, json.tfCounterText );
    var totalWinMeter= new createjs.Bitmap(gameServices.assets.getAsset("device/images/Win_meter.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( totalWinMeter, json.totalWinMeter );
    var freeWonValue = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( freeWonValue, json.freeWonValue );
    var freeWonTextShadow = new createjs.Text(langFile.Bonus);
    amaya.sdk.utils.JSONUtils.fromJSON( freeWonTextShadow, json.freeWonTextShadow );
    var freeWonText = new createjs.Text(langFile.Bonus);
    amaya.sdk.utils.JSONUtils.fromJSON( freeWonText, json.freeWonText );
    instance.bonusTotal = function (value){
        if (model.getFreeSpinsUsed() > 0) {
            freeWonValue.text = value - model.getCreditsWon();
            temp = value - model.getCreditsWon();
        }
        else {
            freeWonValue.text = value;
            temp = value;
        }
    }
    //freeWonValue.text = 2322;
    instance.showFreeWin = function(totalWon){
    		 win = temp ;
    		 num = totalWon;
    		if(model.getCreditsWon()){
    			freeinterval = setInterval(function(){
    								if(win<=num){
    									FGdiv = num - win;
    									FGdivider = counterCheck(FGdiv);
    									win = Math.ceil( win + (FGdiv/FGdivider));
    									//freeWonValue.text = win++;
       									if (win >= num) {
       									    clearInterval(freeinterval);
    										freeWonValue.text = num;
    										temp = num;
    								    }
    									else{
    										freeWonValue.text = win;
    									}
    								}
    								}, 30);
    			freeTimer = setTimeout(function(){
    					        freeWonValue.text = num;
    					        temp = num;
    					        clearInterval(freeinterval);
    					    }, 1500);
    		}
    	};
        instance.ClearFreeWin = function(){
            temp = 0;
            win =0;
            num =0;
            FGdivider=0;
            freeWonValue.text =0;
            clearInterval(freeinterval);
            clearTimeout(freeTimer);
        }
     instance.ShowFreeWinAfterSkip = function(){
        freeWonValue.text = num;
        temp = num;
        clearInterval(freeinterval);
        clearTimeout(freeTimer);
     }
 	function counterCheck(FGdiv){
 	    if(FGdiv > 50){
 			return 50;
 		}
 		else{
           	return model.getCreditsWon();
        }
 	}
    instance.showCounter = function () {
        container.addChild(freeGamesMeter);
        container.addChild(tfCounterShadow);
        container.addChild(tfCounter);
        container.addChild(tfCounterTextShadow);
        container.addChild(tfCounterText);
        container.addChild(totalWinMeter);
        container.addChild(freeWonValue);
        container.addChild(freeWonTextShadow);
        container.addChild(freeWonText);
    };
    instance.hideCounter = function () {
        container.removeChild(tfCounterShadow);
        container.removeChild(tfCounter);
        container.removeChild(tfCounterTextShadow);
        container.removeChild(tfCounterText);
        container.removeChild(freeGamesMeter);
        container.removeChild(totalWinMeter);
        container.removeChild(freeWonValue);
        container.removeChild(freeWonTextShadow);
        container.removeChild(freeWonText);
    };
    /**
     *
     * @param {number} used
     * @param {number} total
     */
    instance.updateCounter = function ( used, total ) {
        tfCounter.text = used + " / " + total;
        tfCounterShadow.text = tfCounter.text;
    };
    instance.hideCounter();
    return instance;
};
amaya.game.Game = function () {
    var instance = {};
    var gameServices = new amaya.GameServices();
    var gameCanvasManager = new amaya.sdk.ui.canvas.CanvasManager();
    var soundSystem = new amaya.sdk.sound.SoundSystem();
    var model;
    var display;
    var backgroundView;
    var wonView;
    var reelView;
    var paylineView;
    var freeSpinView;
    var celebrationWindowView;
    var introView;
    var introTimeout;
    var bigWin = false;
    var spinIntegrator;
    var winAnimationIntegrator;
	var soundplay = false;
	var bonusTimeout;
	var spinTimeout;
	var freespinTime;
	var soundManager;
    var creditValues = [];
	var retriggerTimeOut;
	var autoTimer;
	var autoSpinHas = false;
	var payLineTimer;
	var stackPos =0;
	var scatFlag =0;
    var allWinDisplayTimer;
    var noInitFlag = false;
	var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");
   // var randomStartReelGrids = [[[6,5,6,3],[7,10,3,10],[5,8,12,1],[7,11,13,1],[1,8,11,1]],[[12,13,9,1],[6,10,2,1],[6,12,7,1],[4,9,6,1],[3,8,9,1]],[[10,8,6,1],[10,3,12,1],[9,13,11,1],[5,9,3,1],[6,11,13,1]],[[10,5,8,1],[6,10,4,1],[11,7,5,1],[9,6,12,1],[8,11,2,1]],[[11,6,9,1],[11,9,6,1],[3,7,10,1],[9,6,12,1],[3,10,5,1]]];
    var celebrationWindowTimer;
    amaya.sdk.singleplayer.V2Game.init( startMain, resumeMain );
    function startMain ( data, openingBalance ) {
        gameServices.notifyGameStart();
        main(openingBalance);
    }
    function resumeMain ( data, openingBalance ) {
        gameServices.notifyGameStart();
        noInitFlag = true;
        main(openingBalance);
        console.log("resume main");
        console.log(data);
        var resumeGameResponse = new amaya.game.messaging.ResumeGameResponse(data);
        model.setFlag(true);
        model.setGameInProgress(true);
        model.setCreditValue(resumeGameResponse.getCreditValue());
        model.setCreditsPerLine(resumeGameResponse.getCreditsPerLine());
        //model.setCreditsWon(resumeGameResponse.getCreditsWon()- resumeGameResponse.getScatterWin());
        model.setCreditsWon(resumeGameResponse.getCreditsWon());
        model.setReelStops(resumeGameResponse.getReelStops());
        model.setLinesWon(resumeGameResponse.getLinesWon());
        model.setWinningLines(resumeGameResponse.getWinningLines());
        // to get symbols and ways win
        model.setSymbolsWays(resumeGameResponse.getSymbolsAndWays());
        model.setWinningLineId(resumeGameResponse.getWinningLineId());
        model.setStreamStack(resumeGameResponse.getStreamingStack());//set streaming stack
        model.setAllPosition(resumeGameResponse.getAllPositions());//set positions stack for all wins
        model.setScatterAllPositions(resumeGameResponse.getScatterWays());//set  scatter positions stack for all wins
        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDIT_VALUE, resumeGameResponse.getCreditValue());
        gameServices.gameSettings.setGameSetting(amaya.game.GameConstants.ID_CREDITS_PER_LINE, resumeGameResponse.getCreditsPerLine());
        if (openingBalance >= model.getWon()) {
            if (resumeGameResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal()) {
                if (resumeGameResponse.getFreeSpinsUsed() === resumeGameResponse.getFreeSpinsTotal()) {
                    model.setBalance(openingBalance - model.getWon());
                }
                else {
                    model.setBalance(openingBalance);
                }
            }
            else {
                model.setBalance(openingBalance - model.getWon());
            }
        }
        else {
            model.setBalance(openingBalance);
        }
        updateFinanceFields();
        model.setOneWinAnimationCycleComplete(false);
        spinIntegrator.showStaticReels(model.getReelStops());
        //reelView.forceReels();
        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.
        if ( resumeGameResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            spinIntegrator.setReelSetIndexForFreeGame();
            if (resumeGameResponse.hasFreeSpinsTriggered()){
                model.setFreeSpinsTriggered(true);
                 model.setScatterWin(resumeGameResponse.getScatterWin());
            }
            model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(resumeGameResponse.getFreeSpinsUsed());
            model.setFreeSpinsWon (resumeGameResponse.getFreeSpinsWon());
            model.setScatterLength(resumeGameResponse.getScatterLength());
            freeSpinView.bonusTotal(resumeGameResponse.getFreeSpinsWon());
           /* this check is kept outside from following check of no. of free spins used
            so that NumFreeSpinsWon can be set in model for broken at just start of triggering free spins*/
            if (resumeGameResponse.hasFreeSpinsTriggered()){
               console.log("hasFreeSpinsTriggered setNumFreeSpinsWons");
             //  model.setNumFreeSpinsWon(amaya.game.GameConstants.NUM_FREESPINS_WON[resumeGameResponse.getNumScatterSymbols() - 1]);
             //  model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal());
              model.setNumFreeSpinsWon(resumeGameResponse.getScatterWays().freespins);
            }
            if ( resumeGameResponse.getFreeSpinsUsed() > 0 ) {
                soundManager.PlayFreeSpinSound();
                if (resumeGameResponse.hasFreeSpinsTriggered()){
                   // model.setNumFreeSpinsWon(amaya.game.GameConstants.NUM_FREESPINS_WON[resumeGameResponse.getNumScatterSymbols() - 1]);
                   // model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal() - model.getNumFreeSpinsWon());
                    model.setFreeSpinsTotal(resumeGameResponse.getFreeSpinsTotal() - resumeGameResponse.getScatterWays().freespins);
                }
                model.setGameState(amaya.game.GameConstants.FREE_SPIN_STATE);
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();
                wonView.setFreePromoMsg();
                freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                model.setBaseReelStops(resumeGameResponse.getBaseReelStops());
                handleInitResponse(resumeGameResponse);
              //  onFreeSpinComplete( new ingenuity.gdk.events.SpinCompleteEvent() );
                console.log("execute");
                return;
            }
        }
         model.setBaseReelStops(resumeGameResponse.getReelStops());
         wonView.setPromoMsg();
         handleInitResponse(resumeGameResponse);
        //onSpinComplete( new ingenuity.gdk.events.SpinCompleteEvent() );
    }
    function main ( openingBalance ) {
        console.log("main");
        console.log(gameServices.profile.getCreditValues());
        // Create a new instance of GameServices to interact with the framework.
        // Game Services can be instantiated anywhere but since class will use it repeatedly, we'll assign it to a class member variable.
        gameServices = new amaya.GameServices();
        for (var i = 0; i < gameServices.profile.getCreditValues().length; i++) {
            var numToString = amaya.sdk.utils.NumberUtils.toShorthandFormat( gameServices.profile.getCreditValues()[i], gameServices.session.lang() );
            if (gameServices.profile.getCreditValues()[i] >= 1000) {
                creditValues[i] = gameServices.profile.getCurrencyCode() + numToString;
            }
            else {
                creditValues[i] = gameServices.profile.getCreditValues()[i];
            }
        }
        // Create a stepper control for selecting credit value
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDIT_VALUE,
            langFile.Credit_Value,
            creditValues,
            creditValues[0],
            true );
        // Create a stepper control for selecting credits per line
        gameServices.gameSettings.addStepperControl(
            amaya.game.GameConstants.ID_CREDITS_PER_LINE,
            langFile.Credits_Per_Line,
            [1,2,3,4,5],
            1,
            false );
        // Create an autoplay control
        gameServices.gameSettings.addAutoSpinControl(
            amaya.game.GameConstants.ID_AUTO_SPIN,
            langFile.Auto_Spin );
        // The auto play control has additional events that must be integrated to make the auto play system function properly.
        gameServices.addEventListener( amaya.api.events.AutoSpinStartEvent.START, onAutoSpinStart );
        gameServices.addEventListener( amaya.api.events.AutoSpinStopEvent.STOP, onAutoSpinStop );
        // Create a listener for responses from the server.
        gameServices.addEventListener( amaya.api.events.IncomingMessageEvent.INCOMING_MESSAGE, onIncomingMessage );
        // Create a listener for balance updates.
        gameServices.addEventListener( amaya.api.events.GameFinanceUpdateEvent.GAME_FINANCE_UPDATE, onGameFinanceUpdate );
        gameServices.addEventListener( amaya.api.events.DialogResultEvent.DIALOG_RESULT, onDialogResult );
        gameServices.addEventListener( amaya.api.events.ErrorDisplayEvent.ERROR_DISPLAYED, onErrorDisplayed );
        gameServices.addEventListener( amaya.api.events.ModalDialogChangeEvent.MODAL_DIALOG_CHANGE, onModalDialogChange );
        gameServices.addEventListener( amaya.api.events.OptionChangeEvent.OPTION_CHANGE, onOptionChange );
        gameServices.addEventListener( amaya.api.events.GameSettingChangeEvent.CHANGE, onGameSettingChange );
        gameServices.addEventListener( amaya.api.events.OrientationChangeEvent.CHANGE, onOrientationChange );
        // Enable the use of credit displays within the framework.
        // This allows the display in the bottom status bar to be toggled by the user between cash and credit displays.
        gameServices.enableCreditDisplays();
        display = new amaya.game.Display( gameCanvasManager.getCanvas() );
        // Create the game's model.
        // The model stores the state of the game.
        model = new amaya.game.Model( openingBalance, gameServices.profile.getCreditValues(), amaya.game.GameConstants.NUM_LINES );
        //Sound Manger for play all sound
        soundManager = new amaya.game.SoundManager ( model, soundSystem );
        // Create View classes.
        // These classes are responsible for controlling the display of the ui graphics.
        // Each view is passed a reference to the ui (added to the display list above) and a reference to the model.
        //
        // Background View.
        backgroundView = new amaya.game.BackgroundView( display.backgroundContainer, display.logoContainer );
        backgroundView.showLogo();
        // Reel View - Controls the display of the reels (Spin and Icon animations).
        reelView = new amaya.game.ReelView( display.reelsContainer, display.symAnimContainer, model, display.winboxContainer , display.reelStreamContainer, soundManager);
        // Payline View - Controls the display of the paylines.
        paylineView = new amaya.game.PaylineView( display.paylineContainer, display.winboxContainer, model, reelView );
        // Won View - Controls the display of the won field and sounds
        wonView = new amaya.game.WonView( display.wonContainer, model, soundManager, backgroundView );
        // Update the balance and wagered fields
        updateFinanceFields();
        // Stars View - Controls the display of the star icon accumulator.
//        starsView = new amaya.game.StarsView( display.starsContainer, model );
        // Free Spin View - Controls the display of the free spin ui. Note that the Reel View still displays the
        // free spin reel animations.
        freeSpinView = new amaya.game.FreeSpinView( display.freeSpinContainer, model );
        celebrationWindowView = new amaya.game.CelebrationWindow ( display.winCelebrationContainer, model, soundManager );
//        introView = new amaya.game.IntroView(display.introContainer);
        // Setup the common model for Ingenuity Mobile Engine
        var commonModel = ingenuity.gdk.IngenuitySlotLibrary.Common.CommonModel();
        commonModel.setReelIndexStartIndex(amaya.game.GameConstants.REEL_INDEX_START_INDEX);
        commonModel.setSymbolIndexStartIndex(amaya.game.GameConstants.SYMBOL_INDEX_START_INDEX);
        // Reel SpinIntegrator - integrates the ingenuity gaming reel spin module
        // setup the data for spin module into an object
        var spinData = { numReels:amaya.game.GameConstants.REELS, numDisplayIcons:amaya.game.GameConstants.ICONS,
            spacing:(gameServices.assets.getAsset("device/main.json")).icons.spacing, numReelSpinRows:amaya.game.GameConstants.NUM_REEL_SPIN_ROWS,
            numSymbols:amaya.game.GameConstants.NUM_SYMBOLS, symbolData:reelView.getSymbolData()
        };
        spinIntegrator = new ingenuity.gdk.Integrators.CJIntegrators.SpinIntegrator(reelView.getReelContainer(), new amaya.api.EventDispatcher(), commonModel);
/*
        // Reel spin configuration data
        var sym_height = spinData.spacing;
        var reelSpinConfigData = {};
        reelSpinConfigData.spin_max_speed = Math.round(22 * sym_height / 100);
        reelSpinConfigData.spin_speed_incr = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_speed_decr = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_min_speed = Math.round(4 * sym_height / 100);
        reelSpinConfigData.spin_delay = 300;
        reelSpinConfigData.spin_reverse_time = Math.round(8 * sym_height / 100);
        reelSpinConfigData.spin_speed_reverse = Math.round(-2 * sym_height / 100);
        reelSpinConfigData.spin_first_reel_cont_time = 200;
        reelSpinConfigData.spin_anticipation_time = 1200;
        spinIntegrator.setReelSpinConfigurationData(reelSpinConfigData);
*/
        spinIntegrator.powerUpSpin(spinData);
        // Win Animation Integrator - integrates the ingenuity gaming win animation module
        var winAnimData = { spacing:(gameServices.assets.getAsset("device/main.json")).icons.spacing, symbolData:getSymbolAnimData(),
        numReels:amaya.game.GameConstants.REELS, numDisplayedReelIcons:amaya.game.GameConstants.ICONS};
        winAnimationIntegrator = new ingenuity.gdk.Integrators.CJIntegrators.WinAnimationIntegrator(reelView.getSymAnimContainer(), new amaya.api.EventDispatcher(), commonModel);
        winAnimationIntegrator.addEventListener(ingenuity.gdk.events.WinAnimationCycleCompleteEvent.WIN_ANIM_CYCLE_COMPLETED,onWinAnimCycleComplete);
        winAnimationIntegrator.addEventListener(ingenuity.gdk.events.LineAnimationStartEvent.LINE_ANIMATION_STARTED,onLineAnimStart);
        winAnimationIntegrator.powerupAnimations(winAnimData);
        // Send the init request to the server. request is used to get initial information from the server
        // to properly set up the game.
        if(!noInitFlag){
        var initReq = new amaya.game.messaging.InitRequest();
        gameServices.sendV2XMLRequest( initReq.getRequestData() );
        }
        else{noInitFlag = false;}
    }
    function onIncomingMessage ( event ) {
    		console.log(event.data.response);
        switch (event.data.name) {
            case amaya.game.messaging.MessagingConstants.INIT_REQUEST:
                handleInitResponse( new amaya.game.messaging.InitResponse( event.data.response ) );
                break;
            case amaya.game.messaging.MessagingConstants.SPIN_REQUEST:
                handleSpinResponse( new amaya.game.messaging.SpinResponse ( event.data.response ) );
                break;
            case amaya.game.messaging.MessagingConstants.FREE_SPIN_REQUEST:
                handleFreeSpinResponse( new amaya.game.messaging.FreeSpinResponse ( event.data.response ) );
                break;
            case amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq.CONFIRM_HAND_END_REQUEST:
                handleConfirmHandEndRequest();
                break;
        }
    }
    /**
     * Handles the InitRequest response.
     */
    function handleInitResponse ( initResponse ) {
        //Setup reels
       // console.log("Setup reels");
        // Record the reel strips received in the response. This game has the same reel strips for base and free games
        spinIntegrator.setReelStrips(initResponse.getReels(), initResponse.getFreeReels());
        if (model.getFlag()) {
            spinRestore();
            // set the flag to track whether a spin response has been received from the server
            spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());
            checkForAnticipation();
        }
        else {
            model.setReelStops(initResponse.getReelStops());
            // Intro View - Controls the display of the game's intro screen
            introView = new amaya.game.IntroView( display.introContainer);
            introView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeIntro );
            introTimeout= setTimeout(function() {removeIntro();}, 10000);
            // Play the intro sound
            soundManager.PlayIntro();
        }
        // When all setup is complete, remove the loader screen to reveal the game.
        gameServices.removeLoader();
    }
	function startBonusSound() {
	    soundplay = true;
	    //soundManager.PlayFreeSpinSound();
	}
	function startSpinSound() {
        soundManager.PlaySpinSound();
    }
    /**
     * Handles the regular Spin Response.
     * @param {amaya.game.messaging.SpinResponse} spinResponse
     */
    function handleSpinResponse ( spinResponse ) {
        // Record data from the response into the model
         model.setStreamStack(spinResponse.getStreamingStack());//set streaming stack
         model.setScatterWin(spinResponse.getScatterWin());
         // to get symbols and ways win
         model.setSymbolsWays(spinResponse.getSymbolsAndWays());
         model.setWinningLineId(spinResponse.getWinningLineId());
        //console.log("setScatterWin=="+spinResponse.getScatterWin());
        freeSpinView.bonusTotal(spinResponse.getFreeSpinsWon());
       // model.setCreditsWon(spinResponse.getCreditsWon() - spinResponse.getScatterWin());
       model.setCreditsWon(spinResponse.getCreditsWon());
        model.setReelStops(spinResponse.getReelStops());
        //console.log("Reel stops: ",model.getReelStops());
        model.setBaseReelStops(spinResponse.getReelStops());
        model.setLinesWon(spinResponse.getLinesWon());
        console.log("spinResponse.getWinningLines()",spinResponse.getWinningLines());
        model.setAllPosition(spinResponse.getAllPositions());
        model.setNumFreeSpinsWon(spinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal());
        model.setWinningLines(spinResponse.getWinningLines());
        //model.setFreeSpinsWon(spinResponse.getFreeSpinsWon());
        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then spin triggers free spins.
        // If so, record the free spin data and set the triggered flag to true.
        if ( spinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
            model.setFreeSpinsTotal(spinResponse.getFreeSpinsTotal());
            model.setFreeSpinsUsed(spinResponse.getFreeSpinsUsed());
            model.setScatterLength(spinResponse.getScatterLength());
        }
        // set the flag to track whether a spin response has been received from the server
        spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());
        checkForAnticipation();
    }
    /** Handles the Free Spin response. */
    function handleFreeSpinResponse ( freeSpinResponse ) {
        // Record data from the response into the model.
        model.setStreamStack(freeSpinResponse.getStreamingStack());//set streaming stack
        model.setAllPosition(freeSpinResponse.getAllPositions());
        model.setCreditsWon(freeSpinResponse.getCreditsWon());
        model.setReelStops(freeSpinResponse.getReelStops());
        model.setLinesWon(freeSpinResponse.getLinesWon());
        model.setWinningLines(freeSpinResponse.getWinningLines());
       // to get symbols and ways win
        model.setSymbolsWays(freeSpinResponse.getSymbolsAndWays());
        model.setWinningLineId(freeSpinResponse.getWinningLineId());
        // If the total number of free spins in the response is greater than the number of free spins
        // currently in the model, then this free spin triggers additional free spins.
        // If so, set the triggered flag to true.
        if ( freeSpinResponse.getFreeSpinsTotal() > model.getFreeSpinsTotal() ) {
            model.setFreeSpinsTriggered(true);
        }
        // Record the updated free spin data into the model.
        model.setNumFreeSpinsWon(freeSpinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal());
         console.log("handleFreeSpinResponse_setNumFreeSpinsWon=="+(freeSpinResponse.getFreeSpinsTotal() - model.getFreeSpinsTotal()));
        model.setFreeSpinsTotal(freeSpinResponse.getFreeSpinsTotal() - model.getNumFreeSpinsWon());
        model.setFreeSpinsUsed(freeSpinResponse.getFreeSpinsUsed());
        model.setFreeSpinsWon(freeSpinResponse.getFreeSpinsWon());
        model.setScatterLength(freeSpinResponse.getScatterLength());
        // set the flag to track whether a spin response has been received from the server
        spinIntegrator.setSpinResponseReceivedFromServer(model.getReelStops());
        checkForAnticipation();
    }
    /**
     * Handle the confirm hand end response.
     * Confirm hand end is a standard game message that tells the server that the results of the game
     * have been displayed to the player so the game is no longer in an unfinished (resumable) state.
     */
    function handleConfirmHandEndRequest () {
        model.setFlag(false);
        // Responsible gaming regulations require that the 3 seconds must elapse from the start of one spin
        // to the start of the next. When the spin was initiated, the start time was recorded (See spin method).
        // time is compared to the current time to see if 3 seconds have elapsed.
        // If so, the ui is enabled so that the player can spin again.
        // If not, a timer is set to enable the ui when the remaining portion of the 3 seconds has elapsed.
        var elapsedSpinTime = new Date().getTime() - model.getSpinStartTime();
        var delay = 3000 - elapsedSpinTime;
        if ( delay < 0 ) delay = 0;
        setTimeout(function () {
            // Now that the spin is completed, the UI should be re-enabled unless there are additional auto spins.
            // If so, the ui should not re-enable and a slight delay should be included to allow the user to see
            // the results. If the spin has no wins, the delay should be shorter.
            if (gameServices.autoSpin.hasSpins()) {
                autoSpinHas = true;
                autoTimer = setTimeout(autoSpin,(model.spinHasPaylineWins()) ? 2000 : 500 );
            } else {
                enableUI();
               //wonView.startPromoMsg();
            }
        },delay);
    }
    /**
     * Handle the balance updates from the server.
     */
    function onGameFinanceUpdate ( event ) {
        model.setBalance(event.data.balance);
        if ( !model.getGameInProgress() ) {
            updateFinanceFields();
        }
    }
    function onDialogResult ( event ) {
    }
    function onErrorDisplayed ( event ) {
        soundSystem.stopAllSounds();
    }
    function onModalDialogChange ( event ) {
    }
    function onOptionChange ( event ) {
    }
    function onOrientationChange ( event ) {
        if ( event.data.isPortrait() ) {
        } else if ( event.data.isLandscape() ) {
        }
    }
    // This method handles game setting change events from Game Services
    function onGameSettingChange ( event ) {
        wonView.clearWinMsg();
        model.setOneWinAnimationCycleComplete(false);
        // stop all displays related to win animations like symbol animations, payline animations, message displays
        stopWinDisplayForNextSpin();
        // Clear the win field from the previous spin.
        wonView.clearWin();
        // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
         paylineView.hideAllPaylineWins();
        switch ( event.data.id ) {
            case amaya.game.GameConstants.ID_CREDIT_VALUE:
                var index = creditValues.indexOf(event.data.value);
                // Tell the model to set the credit value.
                model.setCreditValue( gameServices.profile.getCreditValues()[index] );
                break;
            case amaya.game.GameConstants.ID_CREDITS_PER_LINE:
                // Update the model with the players new bet amount.
                model.setCreditsPerLine(event.data.value);
                break;
        }
        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }
    /**
     * Handles the event dispatched when the player clicks the Credit Value Increase button.
     */
    function onCreditValueIncrease ( event ) {
        // Tell the model to increase the credit value.
        model.increaseCreditValue();
        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }
    /**
     * Handles the event dispatched when the player clicks the Credit Value Decrease button.
     */
    function onCreditValueDecrease ( event ) {
        // Tell the model to decrease the credit value.
        model.decreaseCreditValue();
        // Update the betting ui so that the new credit value will display.
        updateFinanceFields();
    }
    function onCreditsPerLine ( event ) {
        // Once the user has started modifying the bet, hide any winning paylines that are being displayed
        // from the previous spin.
        paylineView.hideAllPaylineWins();
        // Update the model with the players new bet amount.
        model.setCreditsPerLine(event.data.creditsPerLine);
        // Update the betting ui so that the new bet amount is displayed.
        updateFinanceFields();
    }
    /** 
     * Handles the event dispatched when the player clicks the spin button.
     */
    function onSpin ( event ) {
		soundManager.PlaySpinButton();
        // Disable the ui so that the player cannot change the bet or click spin again.
        disableUI();
        // Validate to see if the player has satisfied all conditions required to spin.
        // 1. Has the player placed a bet?
        // 2. Does the player have enough money to bet?
        // If so, call the spin method.
        // If not, display a dialog with a message informing the player of the applicable
        // problem and re-enable the ui.
        setTimeout( function () {
            soundSystem.stopAllSounds();
        }, 200);
        if ( model.getBet() > 0 ) {
            if ( model.getBet() <= model.getCredits() ) {
                model.setBalance(model.getBalance() - model.getWager());
                updateFinanceFields();
                spin();
                console.log(" function onSpin");
                 wonView.showGoodLuckMsg();
                setTimeout( function () {
                    startSpinSound();
                }, 300);
            } else {
                gameServices.showOkDialog("spinWarningLowCredits","You do not have enough credits to place bet.");
                enableUI();
            }
        } else {
            gameServices.showOkDialog("spinWarningMinBet","You must bet at least one credit on at least one line to spin.");
            enableUI();
        }
    }
    /**
     * Handles the event dispatched when the regular reel spin animation has completed.
     * Note: handler is only executed when regular spins complete. See onFreeSpinComplete for free spin handling.
     */
    function onSpinComplete ( event ) {
       soundManager.StopSpinSound();
       console.log("On spin complete");
      // checkStreamingStack();
       // console.log("onSpinComplete()");
         wonView.clearWinMsg();
        // If spin has winning paylines, show the win amount and highlight the winning paylines
        if ( !model.getFreeSpinsTriggered() && model.spinHasPaylineWins() ) {
            if ((model.getCreditsWon()) >= 20*(model.getBet())) {
                //wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                checkBigWin();
               // wonView.showPreBigWin();
            }
            else {
                playBaseGameLineWins();
            }
        }
        if ( model.getFreeSpinsTriggered() ) {
            wonView.showWinMsg(0, model.getScatterWin(),11);
          //  reelView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
           // reelView.playScatterAnimation();
            soundManager.PlayWinAnim(amaya.game.GameConstants.SYM_SCATTER);
            wonView.clearPromoMsg();
            // Check to see if spin triggered free spins.
            // If so, transition to free spin mode.
            // If not, send the confirm hand end request to tell the server that the results have been displayed.
            // If there were base game spins then check once the win animations are complete for triggered free spins and play them
            //playFreeSpinIntro();
        } else if (!gameServices.autoSpin.hasSpins() || !model.spinHasPaylineWins()){
            if (! ((model.getCreditsWon()) >= 20*(model.getBet())) ) {
                  processGameEnd();
            }
        }
       // checkBigWin();
    }
    function processGameEnd() {
		if (model.getFreeSpinsHasWins()) {
			wonView.showWin();
			model.setFreeSpinsHasWins(false);
		}
        model.setGameInProgress(false);
        // Send the confirm hand end request.
        var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
        gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());
        // Notify the Casino 5 framework that the game has now completed.
        // This is required by all games.
        gameServices.notifyGameEnd();
        console.log("After game win update: ",model.getWon(), model.getNetWon());
        wonView.showBaseWinOnTrigger();
    }
    /**
     * Handles the event dispatched when the free spin reel animation has completed.
     * Note: handler is only executed when free spins complete. See onSpinComplete for regular spin handling.
     */
    function onFreeSpinComplete ( event ) {
        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("onFreeSpinComplete addEventListener skipAnimation");
           wonView.clearWinMsg();
        // Update the free spin counter in the free spin view.
        // Since we updated the "used" count at the start of the spin, this won't change either value
        // unless additional free spins were triggered (in which case, the total will update to include new
        // free spins won on this spin).
        if ( !model.getFreeSpinsTriggered() ) {
            if (!((model.getCreditsWon()) >= 20*(model.getBet()))) {
                freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                freeSpinView.showFreeWin(model.getFreeSpinsWon());
            }
        }
        // If this spin has winning paylines, show the win amount and highlight the winning paylines
        if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered() ) {
            if ((model.getCreditsWon()) >= 20*(model.getBet())) {
                display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                 console.log("onFreeSpinComplete  if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered() ) removeEventListener skipAnimation");
                //wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                checkBigWin();
               // wonView.showPreBigWin();
            }
            else {
                 if(model.getWinningLines().length > 1 ){
                    paylineView.showAllPosBg (model.getAllPosition().positions);
                    console.log("model.getAllPosition().positions",model.getAllPosition().positions);
                    /***to display  multiple wins  with all win symbols playing animations without winbox****/
                    winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
                    allWinDisplayTimer = setTimeout(function () {
                                          paylineView.hideAllPosBg();
                                          winAnimationIntegrator.stopAllWinAnimations();
                                          winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                                     }, 2500);
                }
                else{
                    winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                }
                wonView.showWin();
            }
        }
	    else if (model.spinHasPaylineWins() && model.getFreeSpinsTriggered()) {
            //display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
           // console.log("onFreeSpinComplete if (model.spinHasPaylineWins() && model.getFreeSpinsTriggered())  removeEventListener skipAnimation");
            winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
            wonView.showWin();
        }
/*        // Check to see if this spin triggered free spins.
        // If so, transition to free spin mode.
        // If not, send the confirm hand end request to tell the server that the results have been displayed.
        if ( model.getFreeSpinsTriggered() ) {
            // Freespin has been retriggered
            // If there were other payline wins then delay for some time to show those to the user.
            if (!((model.getCreditsWon()) >= 20*(model.getBet()))) {
                if (model.spinHasPaylineWins()) {
                    retriggerTimeOut = setTimeout (function () {
                        playFreeSpinRetrigger();
                    }, 2000);
                } else {
                    display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                    playFreeSpinRetrigger();
                }
            }
        }
*/
        // If the current freeSpinsUsed count is less than the freeSpinsTotal count, then there are
        // additional free spins to execute. Otherwise, this was the last free spin and the game
        // should transition back to the regular spin state.
        else if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) {
			display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
			//console.log("onFreeSpinComplete  if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) removeEventListener skipAnimation");
            // Provide a couple of seconds for the player to view any wins before starting the next spin.
            // Note, at this point, the game state is still in free spin mode and no confirm hand end should
            // be sent until all free spins have completed.
            freespinTime = setTimeout( spin, 2000 );
        } else {
            //console.log("ending freespins");
            if ( !model.spinHasPaylineWins() ) {
                display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                	//console.log("onFreeSpinComplete   if ( !model.spinHasPaylineWins() ) removeEventListener skipAnimation");
                playFreeSpinEnd();
            }
        }
    }
        /* Controls resuming of spin for broken game.
         */
        function spinRestore () {
           wonView.clearWinMsg();
                   model.setOneWinAnimationCycleComplete(false);
                   // stop all displays related to win animations like symbol animations, payline animations, message displays
                   stopWinDisplayForNextSpin();
                   // Remove any event listeners from the reel view.
                   // A new one will be assigned depending on whether is a regular or free spin so that the correct
                   // actions can be taken after the spin animation completes.
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                   spinIntegrator.removeEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                   // Reset the model. Not all values are reset, just those that need to be reinitialized between spins.
                  // model.resetForSpin();
                   // Set a flag indicating the game is in progress. is used to control balance updating.
                   model.setGameInProgress(true);
                   // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
                   paylineView.hideAllPaylineWins();
                   // Check the current game state to determine if a regular spin or a free spin is being initiated.
                   switch ( model.getGameState() ) {
                       // Regular Spin
                       case amaya.game.GameConstants.SPIN_STATE:
                            setTimeout( function () {
                                startSpinSound();
                            }, 300);
                          // console.debug("Initiating base spin");
                           // Clear the win field from the previous spin.
                           wonView.clearWin();
                          // wonView.clearPromoMsg();
                           // Assign an event listener to act when the ReelView dispatches a spin complete event.
                           // The onSpinComplete method handles the completion of regular spins.
                           spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                           spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                           // Mark the time the spin starts.
                           // Responsible Gaming regulations require that 3 seconds must elapse between the start of the one
                           // regular spin and the start of the next. On completion of the spin, the time will be checked and
                           // if 3 seconds has not elapsed, the ui will remain disabled until it has.
                           // Note: See the handleConfirmHandEndResponse() event handler in class.
                           model.setSpinStartTime(new Date().getTime());
                           // Notify the Casino 5 framework that the game is now in progress.
                           // This is required by all games.
                           gameServices.notifyGameStart();
                           break;
                       // Free Spin
                       case amaya.game.GameConstants.FREE_SPIN_STATE:
                          // console.debug("Initiating free spin");
                           // Turn on the free spin background and counter.
                           backgroundView.showFreeSpinBackground();
                           freeSpinView.showCounter();
                           // Clear the win field from the previous spin.
                           wonView.clearWin();
                           // Update the free spin counter in the free spin view.
                           // Since we are starting the animation at the same time we send the request, the server won't tell
                           // the client the new "used" count until the spin response is received (which is when the model
                           // will be updated). creates an odd display for the player they would only see the used count
                           // update as the spin is stopping instead of when it's starting. So code updates the counter to
                           // show the used count + 1 now in anticipation of receiving that value from the server in when the
                           // response comes in.
                           freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                           // Assign an event listener to act when the ReelView dispatches a spin complete event.
                           // The onFreeSpinComplete method handles the completion of free spins.
                           spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                           spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                           break;
                   }
                   // Start the spin animation.
                   // In order to provide a seamless experience for the player, the spin animation is started at the
                   // same time the request is sent to the server (as opposed to waiting for the response).
                   // means the spin animation will have to loop endlessly while waiting for the response to arrive.
                   // In example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
                   // For more information on the animation, see the ReelView class.
                   // An alternate design would be to have class call a method on the ReelView when the response arrives.
           //        reelView.spin();
                   spinIntegrator.startReelSpin();
                   wonView.showGoodLuckMsg();
        }
    function playFreeSpinEnd () {
        wonView.clearFreePromoMsg();
        setTimeout ( function () {
            winAnimationIntegrator.stopAllWinAnimations();
            paylineView.hideAllPaylineWins();
            wonView.clearWinMsg();
        },100);
        // Set a timer to remove the freespin outro banner and then start spin.
        // Provided a few seconds for the player to see the freespin intro banner
       // Adding check to show banner only if there was any win for the free spins and else part is
       //duplicated from removeFreespinOutro to execute the required statements .
        model.setReelStops(model.getBaseReelStops());
        // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
        // model (Set above during model instantiation).
        spinIntegrator.showStaticReels(model.getReelStops());
        if(model.getFreeSpinsWon()>0){
            celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinOutro );
            //celebrationWindowView.playFreeSpinSummmary();
            celebrationWindowView.showFreeSpinOutro(model.getFreeSpinsWon(),model.getFreeSpinsTotal());
            soundManager.StopFreeSpinSound();
		    soundManager.PlayFreeOutroSound();
            freeSpinView.ClearFreeWin();
            wonView.clearWin();
            // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
            model.setGameState(amaya.game.GameConstants.SPIN_STATE);
            model.resetAfterFreeSpinBonus();
            spinIntegrator.setReelSetIndexForBaseGame();
            // Hide the free spin background and counter.
            backgroundView.showMainSpinBackground();
            freeSpinView.hideCounter();
        }
        else{
            soundManager.StopFreeSpinSound();
            clearTimeout(bonusTimeout);
	    if (model.getLinesWon() > 0) {
                freeSpinView.ClearFreeWin();
                // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
                model.setGameState(amaya.game.GameConstants.SPIN_STATE);
                model.resetAfterFreeSpinBonus();
                spinIntegrator.setReelSetIndexForBaseGame();
                // Hide the free spin background and counter.
                backgroundView.showMainSpinBackground();
                freeSpinView.hideCounter();
                wonView.clearWinMsg();
                model.setFreeSpinsHasWins(true);
                model.setCreditsWon(model.getLinesWon());
                if (((model.getCreditsWon()) >= 20*(model.getBet())) ) {
                    setTimeout( function () {
                     //   wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                     checkBigWin();
                     //   wonView.showPreBigWin();
                    }, 50);
                }
                else {
                    processGameEnd();
                }
            }
            else {
            wonView.setPromoMsg();
            // Send the confirm hand end request to tell the server that the trigger spin and all free spins
            // have finished displaying. This takes the game out of a resumable state on the server.
            var confirmHandEndRequest = new amaya.sdk.singleplayer.messaging.V2ConfirmHandEndReq();
            gameServices.sendV2XMLRequest(confirmHandEndRequest.getRequestData());
            // Notify the Casino 5 framework that the game has now completed.
            // This is required by all games.
            gameServices.notifyGameEnd();
            console.log("After game win update: ",model.getWon(), model.getNetWon());
            wonView.showBaseWinOnTrigger();
        freeSpinView.ClearFreeWin();
        wonView.clearWin();
        // Set the game state back to the regular spin state so that the next spin executes as a normal spin.
        model.setGameState(amaya.game.GameConstants.SPIN_STATE);
        model.resetAfterFreeSpinBonus();
        model.setGameInProgress(false);
        spinIntegrator.setReelSetIndexForBaseGame();
            // Hide the free spin background and counter.
            backgroundView.showMainSpinBackground();
            freeSpinView.hideCounter();
            wonView.clearWinMsg();
	    }
        }
        // Play the bonus end sound
        //soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_END);
    }
    // Event handler that is called when the player initiates a new series of auto spins.
    // This event only occurs once at the start of the series.
    function onAutoSpinStart ( event ) {
		soundManager.PlaySpinButton();
        autoSpin();
    }
    function onAutoSpinStop ( event ) {
        clearTimeout(autoTimer);
        clearTimeout(payLineTimer);
	if ( model.getBet() <= model.getCredits() ) {
	        if (!model.getGameInProgress()) {
	            if (!model.getFreeSpinsTriggered() && model.spinHasPaylineWins()) {
	                winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
	            }
	            enableUI();
	            gameServices.notifyGameEnd();
	        }
	}
        else if (autoSpinHas) {
            autoSpinHas = false;
            if (!model.getGameInProgress()) {
                if (!model.getFreeSpinsTriggered() && model.spinHasPaylineWins()) {
                    winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                }
                enableUI();
                gameServices.notifyGameEnd();
            }
        }
        else {
            wonView.clearWinMsg();
            model.setOneWinAnimationCycleComplete(false);
            // stop all displays related to win animations like symbol animations, payline animations, message displays
            stopWinDisplayForNextSpin();
            // Clear the win field from the previous spin.
            wonView.clearWin();
            // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
            paylineView.hideAllPaylineWins();
        }
		soundManager.PlaySpinButton();
    }
    function checkStreamingStack(id){
       var stack =[];
       var reelID = ['reel2','reel3','reel4'];
       stack = model.getStreamStack();
      // for(i=0;i<stack.length;i++){
      if(!(id==0 || id==4)){
            if(stack[stackPos]==3)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
            else if(stack[stackPos]==4)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
            else if(stack[stackPos]==1)
            {
                reelView.playBlueStream(reelID[stackPos]);
            }
          stackPos++;
          if(stackPos==3){stackPos=0;}
       }
    }
    // *** Private Methods *****************************************************************************************
    //
    /**
     * Controls starting of a new spin.
     */
    function spin () {
        // check if 1 cycle of win animation is complete before spinning again
//        if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && !model.hasOneWinAnimationCycleCompleted() && !model.getFreeSpinsTriggered()) {
//            setTimeout( spin, 1000 );
//            return;
//        }
        wonView.clearWinMsg();
        model.setOneWinAnimationCycleComplete(false);
        // stop all displays related to win animations like symbol animations, payline animations, message displays
        stopWinDisplayForNextSpin();
        // Remove any event listeners from the reel view.
        // A new one will be assigned depending on whether is a regular or free spin so that the correct
        // actions can be taken after the spin animation completes.
        spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
        spinIntegrator.removeEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
        spinIntegrator.removeEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("spin () removeEventListener skipAnimation");
        // Reset the model. Not all values are reset, just those that need to be reinitialized between spins.
        model.resetForSpin();
        // Set a flag indicating the game is in progress. is used to control balance updating.
        model.setGameInProgress(true);
        // Tell the payline view to hide any payline wins that are currently being displayed from the last spin.
        paylineView.hideAllPaylineWins();
        // Check the current game state to determine if a regular spin or a free spin is being initiated.
        switch ( model.getGameState() ) {
            // Regular Spin
            case amaya.game.GameConstants.SPIN_STATE:
               // console.debug("Initiating base spin");
                // Clear the win field from the previous spin.
                wonView.clearWin();
               // wonView.clearPromoMsg();
                // Create and send a new SpinRequest object, passing the values required from the model.
                var spinRequest = new amaya.game.messaging.SpinRequest ( model.getCreditValue(), model.getCreditsPerLine() );
                gameServices.sendV2XMLRequest( spinRequest.getRequestData() );
                // Assign an event listener to act when the ReelView dispatches a spin complete event.
                // The onSpinComplete method handles the completion of regular spins.
                spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onSpinComplete);
                spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                // Mark the time the spin starts.
                // Responsible Gaming regulations require that 3 seconds must elapse between the start of the one
                // regular spin and the start of the next. On completion of the spin, the time will be checked and
                // if 3 seconds has not elapsed, the ui will remain disabled until it has.
                // Note: See the handleConfirmHandEndResponse() event handler in class.
                model.setSpinStartTime(new Date().getTime());
                // Notify the Casino 5 framework that the game is now in progress.
                // This is required by all games.
                gameServices.notifyGameStart();
                break;
            // Free Spin
            case amaya.game.GameConstants.FREE_SPIN_STATE:
               // console.debug("Initiating free spin");
                // Turn on the free spin background and counter.
                backgroundView.showFreeSpinBackground();
                freeSpinView.showCounter();
                // Clear the win field from the previous spin.
                wonView.clearWin();
                // Update the free spin counter in the free spin view.
                // Since we are starting the animation at the same time we send the request, the server won't tell
                // the client the new "used" count until the spin response is received (which is when the model
                // will be updated). creates an odd display for the player they would only see the used count
                // update as the spin is stopping instead of when it's starting. So code updates the counter to
                // show the used count + 1 now in anticipation of receiving that value from the server in when the
                // response comes in.
                freeSpinView.updateCounter(model.getFreeSpinsUsed() + 1,model.getFreeSpinsTotal());
                // Create and send a new FreeSpinRequest object, passing the values required from the model.
                var freeSpinRequest = new amaya.game.messaging.FreeSpinRequest( model.getFreeSpinsTotal(), model.getFreeSpinsUsed() );
                gameServices.sendV2XMLRequest(freeSpinRequest.getRequestData());
                // Assign an event listener to act when the ReelView dispatches a spin complete event.
                // The onFreeSpinComplete method handles the completion of free spins.
                spinIntegrator.addEventListener(ingenuity.gdk.events.SpinCompleteEvent.SPIN_COMPLETE,onFreeSpinComplete);
                spinIntegrator.addEventListener(ingenuity.gdk.events.ReelStoppedEvent.REEL_STOPPED,onReelStopped);
                break;
        }
        // Start the spin animation.
        // In order to provide a seamless experience for the player, the spin animation is started at the
        // same time the request is sent to the server (as opposed to waiting for the response).
        // means the spin animation will have to loop endlessly while waiting for the response to arrive.
        // In example, the ReelView checks for stop numbers to be written to the model (See the spin complete handlers).
        // For more information on the animation, see the ReelView class.
        // An alternate design would be to have class call a method on the ReelView when the response arrives.
//        reelView.spin();
        spinIntegrator.startReelSpin();
        wonView.showGoodLuckMsg();
    }
    /**
     * Enables the interactive controls.
     */
    function enableUI () {
        gameServices.showSpinButton();
        gameServices.addEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        updateFinanceFields();
     //        paylineView.enableUI();
    }
    /**
     * Disables the interactive controls
     */
    function disableUI () {
        gameServices.hideSpinButton();
        gameServices.removeEventListener(amaya.api.events.SpinButtonEvent.CLICK,onSpin);
        updateFinanceFields();
//        paylineView.disableUI();
    }
    /**
     * Start the next auto spin.
     * Note, it is important that the GameServices.notifyGameStart is called *before* the GameServices.autoSpin.next is called.
     * In this example, the notifyGameStart method is called as part of the "spin()" function.
     */
    function autoSpin () {
       // spin();
       autoSpinHas = false;
        if ( model.getBet() > 0 ) {
                    if ( model.getBet() <= model.getCredits() ) {
                        spin();
                        model.setBalance(model.getBalance() - model.getWager());
                        updateFinanceFields();
                        wonView.showGoodLuckMsg();
                        startSpinSound();
                      gameServices.autoSpin.next();
                    } else {
                        gameServices.showOkDialog("spinWarningLowCredits","You do not have enough credits to place bet.");
                        gameServices.autoSpin.cancel();
                        gameServices.notifyGameEnd();
                        enableUI();
                    }
                } else {
                    gameServices.showOkDialog("spinWarningMinBet","You must bet at least one credit on at least one line to spin.");
                     gameServices.autoSpin.cancel();
                     gameServices.notifyGameEnd();
                    enableUI();
                }
    }
    function updateFinanceFields () {
        // Update the balance and wagered fields in the framework ui.
        //
        // Update the values in cash
        gameServices.setBalanceDisplay( model.getBalance() );
        gameServices.setWageredDisplay( model.getWager() );
        //
        // Update the values in credits
        gameServices.setCreditsDisplay( model.getCredits() );
        gameServices.setCreditsWageredDisplay( model.getBet() );
    }
    //
    // *************************************************************************************************************
    function getSymbolAnimData () {
        var symbolData = [];
        for (var i = 0; i < amaya.game.GameConstants.NUM_SYMBOLS+1; i++) {
            var animData = getAnimation(i);
            if (animData.image != undefined) {
                animData.images = [gameServices.assets.getAsset(animData.image)];
            }
            symbolData.push(animData);
        }
        return symbolData;
    }
    /**
     * @Description Function to get the animation data
     */
    function getAnimation ( id ) {
        var json = gameServices.assets.getAsset("device/main.json");
        switch ( id ) {
            case 1:
                return json.sym1Data;
                break;
            case 2:
                return json.sym2Data;
                break;
            case 3:
                return json.sym3Data;
                break;
            case 4:
                return json.sym4Data;
                break;
            case 5:
                return json.sym5Data;
                break;
            case 6:
                return json.sym6Data;
                break;
            case 7:
                return json.sym7Data;
                break;
            case 8:
                return json.sym8Data;
                break;
            case 9:
                return json.sym9Data;
                break;
            case 10:
                return json.sym10Data;
                break;
            case 11:
                return json.scatterData;
                break;
            default:
                return json.blankData;
                break;
        }
    }
    function onWinAnimCycleComplete ( event ) {
        console.debug("onWinAnimCycleComplete");
        if (model.hasOneWinAnimationCycleCompleted() === false) {
//            console.log("1st Win anim cycle complete");
            // This is to ensure that these statements are triggered only once after a win animation cycle completion
            // since this callback will be called every time a win cycle completes in a win animation round but since
            // we will set the flag to true in the first call this part will not be called in the nezt call backs.
            // call method to display the promo banner
            // Check if freespin was triggered and call the freespin play method
            if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE) {
                if ( model.getFreeSpinsTriggered() && model.getFreeSpinsUsed() > 0) {
        			display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        			console.log("onWinAnimCycleComplete () removeEventListener skipAnimation");
                    // Freespin has been retriggered
                    retriggerTimeOut = setTimeout (function () {
                        stopWinDisplayForNextSpin();
                        playFreeSpinRetrigger();
                    }, 100);
                }
                else if (model.getFreeSpinsUsed() < model.getFreeSpinsTotal()) {
                    setTimeout(spin, 100);
                    wonView.showGoodLuckMsg();
                }
                else {
                    playFreeSpinEnd();
                }
            } /*else if (model.getGameState() === amaya.game.GameConstants.FREE_SPIN_STATE && model.getFreeSpinsTotal() === model.getFreeSpinsUsed()) {
                console.log("last free spin completed");
                // This was the last freespin
                playFreeSpinEnd();
            }*/ else if (model.getGameState() === amaya.game.GameConstants.SPIN_STATE && !gameServices.autoSpin.hasSpins()) {
                console.debug("Process game end");
                //processGameEnd();
            }
        }
        model.setOneWinAnimationCycleComplete(true);
        paylineView.hideAllPaylineWins();
    }
    function onLineAnimStart ( event ) {
        console.log("Line anim start:"+event.data.id);
        paylineView.hideAllPaylineWins();
        if (event.data.id === 0)
            paylineView.showScatterBg(event.data.positions);
        else
            paylineView.showPaylineWin(event.data.id,event.data.positions);
        // call message to display the message in the message box
         wonView.showWinMsg( event.data.id,event.data.won,event.data.icon);
        // play the sound for the icon --- event.data.icon
        if (!model.hasOneWinAnimationCycleCompleted())  {
                 soundManager.PlayWinAnim(event.data.icon);
        }
    }
    function onReelStopped ( event ) {
        // Play reel stop sound
         checkStreamingStack(event.data);
        checkAndPlayScatterAppearSound(event.data);
        if (event.data == 3 && !model.getScatterHitSound(event.data)) {
                    reelView.stopReelAnticipation();
                    reelView.stopScatterAnimation();
                    paylineView.hideAllPosBg();
                    scatFlag=0;
        }
        soundManager.PlayReelStopSound();
    }
    function checkAndPlayScatterAppearSound(id) {
        if (model.getScatterHitSound(id)) {
            switch (id) {
                case 1:
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_02();
                        reelView.playScatterHalfAnim(id);
                    },200);
                    scatFlag++;
                    break;
                case 2:
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_03();
                        reelView.playScatterHalfAnim(id);
                    },200);
                    reelView.playReelAnticipation();
                    scatFlag++;
                    break;
                case 3:
                    reelView.stopScatterAnimation();
                    paylineView.hideAllPosBg();
                    reelView.stopReelAnticipation();
                    setTimeout( function () {
                        soundManager.PlayBonus_marker_04();
                        if(model.getGameState() == amaya.game.GameConstants.SPIN_STATE){
                            reelView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
                        }
                        reelView.playScatterAnimation();
                    },200);
                    break;
            }
         }
	  else if(scatFlag==1){
	  	 reelView.stopScatterAnimation();
            paylineView.hideAllPosBg();
	  }
    }
    /*
     * @Description stop all displays related to win animations like symbol animations, payline animations, message displays
     */
    function stopWinDisplayForNextSpin() {
        // Stop all animations playing if any
        winAnimationIntegrator.stopAllWinAnimations();
        paylineView.hideAllPaylineWins();
        clearTimeout(allWinDisplayTimer);
    }
    function playFreeSpinRetrigger() {
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        console.log("playFreeSpinRetrigger () removeEventListener skipAnimation");
        celebrationWindowTimer = setTimeout( function () {
            removeFreespinRetrigCelebration();
        }, amaya.game.GameConstants.CELEBRATION_WINDOW_TIMER );
        //celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinRetrigCelebration );
        celebrationWindowView.showFreeSpinRetrigged(model.getNumFreeSpinsWon());
		soundManager.PlayFreeRetriggSound();
    }
    function playFreeSpinIntro () {
        reelView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, playFreeSpinIntro);
        stopWinDisplayForNextSpin();
        // If the auto spin system has spins remaining, they must be cancelled.
       // if ( gameServices.autoSpin.hasSpins() ) {
            gameServices.autoSpin.cancel();
        //}
        // Highlight the free spin icons
//            reelView.highlightFreeSpinIcons();
        // Set the game state to free spin so that the next spin is executed as a free spin.
        model.setGameState(amaya.game.GameConstants.FREE_SPIN_STATE);
        spinIntegrator.setReelSetIndexForFreeGame();
        // Set a timer to remove the freespin intro banner and then start spin.
        // At point, the UI is disabled so the player can't bet or spin.
        // Provided a few seconds for the player to see the freespin intro banner
        // spin and then start running the free spins.
        celebrationWindowTimer = setTimeout( function () {
            // Turn on the free spin background.
            backgroundView.showFreeSpinBackground();
            //removeFreespinIntro();
            // Play the bonus start sound
            // Play the free game sound
           //soundManager.PlayFreeSpinSound();
            //playBaseGameLineWinsAfterTrigger();
        }, 2000 );
        // Play the fire sound
         //soundManager.PlayReelFireSound();
        //soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_INTRO);
        celebrationWindowView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinIntro );
     //   celebrationWindowView.playFireWolfAnimOnReel();
        celebrationWindowView.showFreeSpinIntro(model.getFreeSpinsTotal());
    }
    function playBaseGameLineWins () {
       // console.log("Line win has occured");
        if (! ((model.getCreditsWon()) >= 20*(model.getBet())) ) {
            checkBigWin();
        }
        if ( gameServices.autoSpin.hasSpins() ) {
          if(model.getWinningLines().length > 1){
                          paylineView.showAllPosBg (model.getAllPosition().positions);
                          /***to display  multiple wins  with all win symbols playing animations without winbox****/
                          winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
           }
           else{
                   winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.SHORT_ANIM_MODE);
           }
            wonView.showWinMsgInAutoPlay(model.getWinningLines().length, model.getCreditsWon(),model.getWinningLines()[0].icon );
//            winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.SHORT_ANIM_MODE);
            processGameEnd();
            payLineTimer = setTimeout(function () {
                paylineView.hideAllPaylineWins();
                winAnimationIntegrator.stopAllWinAnimations();
            }, 2000);
        } else {
            if(model.getWinningLines().length > 1){
                paylineView.showAllPosBg (model.getAllPosition().positions);
                /***to display  multiple wins  with all win symbols playing animations without winbox****/
                winAnimationIntegrator.playScatterWinAnimations(model.getAllPosition(), model.getReelStops());
                wonView.showAllCreditsWon(model.getCreditsWon());
                allWinDisplayTimer = setTimeout(function () {
                                      paylineView.hideAllPosBg();
                                      winAnimationIntegrator.stopAllWinAnimations();
                                      winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
                                 }, 2500);
            }
            else{
                winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
            }
        }
        wonView.showWin();
   //     console.log("inside playBaseGameLineWins");
//        paylineView.showAllPaylineWins();
    }
    function playBaseGameLineWinsAfterTrigger () {
        console.debug("playBaseGameLineWinsAfterTrigger");
        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.LONG_ANIM_MODE);
        //wonView.showBaseWinOnTrigger();
        //wonView.showScatterWinOnFSScreenMeter();
//        paylineView.showAllPaylineWins();
    }
    function removeFreespinIntro () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
//        clearTimeout(celebrationWindowTimer);
        celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinIntro);
        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
        freeSpinView.showCounter();
        // Update the free spin counter in the free spin view.
        // Since we are starting the animation at the same time we send the request, the server won't tell
        // the client the new "used" count until the spin response is received (which is when the model
        // will be updated). creates an odd display for the player they would only see the used count
        // update as the spin is stopping instead of when it's starting. So code updates the counter to
        // show the used count + 1 now in anticipation of receiving that value from the server in when the
        // response comes in.
        freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
        //reelView.stopFireAnimOnReel();
        //soundManager.StopReelFireSound();
         wonView.setFreePromoMsg();
//        celebrationWindowView.hideFreeSpinIntro();
        playBaseGameLineWinsAfterTrigger();
    }
    function removeFreespinOutro () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
        clearTimeout(celebrationWindowTimer);
      //  soundManager.PlayFreeOutroSound();
        soundManager.StopFreeSpinSound();
        clearTimeout(bonusTimeout);
        celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinOutro);
        //celebrationWindowView.hideFreeSpinOutro();
        wonView.setPromoMsg();
        model.setFreeSpinsHasWins(true);
        model.setCreditsWon(model.getLinesWon());
        if (((model.getCreditsWon()) >= 20*(model.getBet())) ) {
            setTimeout( function () {
               // wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
               checkBigWin();
               // wonView.showPreBigWin();
            }, 50);
        }
        else {
            processGameEnd();
        }
    }
    function removeFreespinRetrigCelebration () {
        // clear the timeout so that the method is not called twice on touch dismissal of the celebration window
        clearTimeout(celebrationWindowTimer);
		//soundManager.PlayFreeOutroSound();
        //celebrationWindowView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, removeFreespinRetrigCelebration );
        celebrationWindowView.hideFreeSpinRetriggered();
        // update the number of total freespins won
        model.setFreeSpinsTotal(model.getFreeSpinsTotal() + model.getNumFreeSpinsWon());
        freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
        freeSpinView.showFreeWin(model.getFreeSpinsWon());
        // start the next free spin.
        spin();
       // console.log(" removeFreespinRetrigCelebration Spin()");
        wonView.showGoodLuckMsg();
    }
    function removeIntro() {
        gameServices.notifyGameEnd();
        clearTimeout(introTimeout);
         soundManager.StopIntroSound();
        // A set of fake reel stops are created to be displayed on the reels when the game starts.
        // For gaming authority certification purposes, the stops displayed when the game begins must be a real,
        // achievable result. In other words, the player must be able to get result by playing the game.
        //var random_stop_position = Math.floor(Math.random() * randomStartReelGrids.length);
        // The call to "forceReels" tells the display to set the reels immediately to the current reel stops in the
        // model (Set above during model instantiation).
        spinIntegrator.showStaticReels(model.getReelStops());
        paylineView.hideAllPaylineWins();
    //paylineView.showPaylineWin(20,0,0);
     //   freeSpinView.showCounter();
    //   freeSpinView.updateCounter(15,25);
     //   freeSpinView.bonusTotal(200);
      //  celebrationWindowView.showFreeSpinRetrigged(50);
 // celebrationWindowView.showFreeSpinOutro(500,5);
  //reelView.playFireAnimOnReel();
         //wonView.showWinMsg(0,400000);
      //
     //celebrationWindowView.showFreeSpinIntro(25);
     //celebrationWindowView.playFireWolfAnimOnReel();
     //celebrationWindowView.playFreeSpinSummmary();
    //celebrationWindowView.playFireWolfAnimOnReel();
        introView.hideIntro();
        // Enable the game ui
        enableUI();
       // wonView.startPromoMsg();
       wonView.setPromoMsg();
    }
    function checkForAnticipation () {
        var grid = model.getReelStops();
        var scatter = 0;
        var anticipationData = [0,0,0,0,0];
        var scatterHitSound = [0,0,0,0,0];
        for (var i = 1; i < amaya.game.GameConstants.REELS-1; i++) {
            for (var j = 0; j < amaya.game.GameConstants.ICONS; j++) {
                if (grid[i][j] === amaya.game.GameConstants.SYM_SCATTER) {
                    scatter++;
                    if ( i === 1 && j === (amaya.game.GameConstants.ICONS - 1) && scatter === 0) {
                        // If no scatter was encountered on second reel then return
                        spinIntegrator.setAnticipationSpin([0,0,0,0,0]);
                        model.setScatterHitSound([0,0,0,0,0]);
                        console.log("no anticipation data set");
                        return;
                    }
                    scatterHitSound[i] = 1;
                    if (scatter == 2) {
                        anticipationData[i+1] = 1;
                    }
                } else if (j === (amaya.game.GameConstants.ICONS - 1) && scatter < (i)) {
                    model.setScatterHitSound(scatterHitSound);
                    spinIntegrator.setAnticipationSpin(anticipationData);
                    console.log("anticipation data set "+anticipationData);
                    return;
                }
            }
        }
        model.setScatterHitSound(scatterHitSound);
        spinIntegrator.setAnticipationSpin(anticipationData);
    }
    //Skip animation when tap on screen
    function skipAnimation () {
        display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
       // console.log(" function skipAnimation () removeEventListener skipAnimation");
        if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) {
            if (model.getFreeSpinsUsed() < model.getFreeSpinsTotal()) {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    console.log("Skip Animation");
                    clearTimeout(freespinTime);
                    freeSpinView.ShowFreeWinAfterSkip();
                    spin();
                   // console.log(" Skip Animation Spin()");
                }
            }
            else {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    clearTimeout(freespinTime);
                    freeSpinView.ShowFreeWinAfterSkip();
                    playFreeSpinEnd();
                }
            }
        }
        else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE && model.spinHasPaylineWins() && model.getFreeSpinsTriggered()) {
                if (bigWin) {
                  bigWin = false;
                  wonView.clearBigwinMsg();
                } else {
                    if (model.getFreeSpinsUsed() > 0) {
                        model.setOneWinAnimationCycleComplete(true);
                        stopWinDisplayForNextSpin();
                        playFreeSpinRetrigger();
                    }
                    else {
                        spin();
                      //   console.log("Skip Animation  else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN  Spin()");
                    }
                }
        }
        else if ((model.getCreditsWon()) >= 20*(model.getBet()) && model.getGameState() == amaya.game.GameConstants.SPIN_STATE){
            if (bigWin) {
              bigWin = false;
              wonView.clearBigwinMsg();
            }
        }
    }
    /** Check if total credits won after spin is a BIG WIN or not **/
    	function checkBigWin(){
    	    if ((model.getCreditsWon()) >= 20*(model.getBet())){
    	        if(model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE){
                     soundManager.StopFreeSpinSound();
                }
               // wonView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, checkBigWin);
                display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
               // console.log(" function checkBigWin () addEventListener skipAnimation");
                wonView.addEventListener(amaya.game.events.ClickToContinueEvent.Click, playGameAfterBigWin);
                gameServices.autoSpin.cancel();
                wonView.showBigwinMsg();
                bigWin = true;
            }
    	    else if ((model.getCreditsWon()) >= 10*(model.getBet())){
                gameServices.autoSpin.cancel();
              //  gameServices.notifyGameEnd();
                //console.log("big win");
            }
       	}
    //Below function is for play Animation and win after big win
       	function playGameAfterBigWin () {
            display.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
           // console.log(" function playGameAfterBigWin () removeEventListener skipAnimation");
            wonView.removeEventListener(amaya.game.events.ClickToContinueEvent.Click, playGameAfterBigWin);
            if (model.getGameState() == amaya.game.GameConstants.SPIN_STATE) {
                if (!model.getFreeSpinsHasWins()) {
                    playBaseGameLineWins();
                }
                processGameEnd();
            }
            else if (model.getGameState() == amaya.game.GameConstants.FREE_SPIN_STATE) {
                soundManager.PlayFreeSpinSound();
                if ( !model.getFreeSpinsTriggered() ) {
                    freeSpinView.updateCounter(model.getFreeSpinsUsed(),model.getFreeSpinsTotal());
                    freeSpinView.showFreeWin(model.getFreeSpinsWon());
                }
                if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) {
                    if (bigWin) {
                        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                     //   console.log(" function playGameAfterBigWin ()  if ( model.spinHasPaylineWins() && !model.getFreeSpinsTriggered()) addEventListener skipAnimation");
                        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
                        wonView.showWin();
                    }
                }
                /*if ( model.getFreeSpinsTriggered() ) {
                    if (bigWin) {
                        display.addEventListener(amaya.game.events.ClickToContinueEvent.Click, skipAnimation);
                        winAnimationIntegrator.playLineWinAnimations(model.getWinningLines(), model.getReelStops(), winAnimationIntegrator.FREESPIN_ANIM_MODE);
                        wonView.showWin();
                    } else {
                        if (model.getFreeSpinsUsed() > 0) {
                            playFreeSpinRetrigger();
                        }
                        else {
                            spin();
                        }
                    }
                }*/
                if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) {
                    if (!bigWin) {
                        clearTimeout(freespinTime);
                        spin();
                      //   console.log(" function playGameAfterBigWin () if ( model.getFreeSpinsUsed() < model.getFreeSpinsTotal() ) spin()");
                    }
                } else {
                    if (!bigWin) {
                        playFreeSpinEnd();
                    }
                }
            }
            bigWin = false;
       	}
    return instance;
};
amaya.game.GameConstants = {
    NUM_LINES : 50,
    REELS : 5,
    ICONS : 4,
    REEL_STOP_FREE_SPIN : 6,
    REEL_STOP_STAR : 7,
    SPIN_STATE : 0,
    FREE_SPIN_STATE : 1,
    DEFAULT_CREDITS_PER_LINE : 1,
    CREDITS_PER_LINE : [1,2,3,4,5,6,7,8,9,10],
    ID_CREDITS_PER_LINE : "creditsPerLine",
    ID_CREDIT_VALUE : "creditValue",
    ID_AUTO_SPIN : "autoSpin",
    SYM_WILD : 1,
    SYM_LOGO : 2,
    SYM_REDWOLF : 3,
    SYM_BLUEWOLF : 4,
    SYM_FIREBIRD : 5,
    SYM_PAWS : 6,
    SYM_ACE : 7,
    SYM_KING : 8,
    SYM_QUEEN : 9,
    SYM_JACK : 10,
    SYM_SCATTER : 11,
    NUM_REEL_SPIN_ROWS : 7,
    NUM_SYMBOLS : 11,
    REEL_INDEX_START_INDEX : 1,
    SYMBOL_INDEX_START_INDEX : 1,
    CELEBRATION_WINDOW_TIMER : 3000,
    NUM_FREESPINS_WON : [0,5,10,20,60]
};
amaya.game.IntroView = function ( container) {
    var instance = new amaya.api.EventDispatcher();
    var gameServices = new amaya.GameServices();
    var IntroContainer = new createjs.Container();
    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");
	var IntroScreen = new createjs.Bitmap(gameServices.assets.getAsset("device/images/loader.jpg"));
    //var scattersym = new createjs.Bitmap(gameServices.assets.getAsset("device/images/Small_Scatter.png"));
    //amaya.sdk.utils.JSONUtils.fromJSON( scattersym, json.scattersym );
   // scattersym.scaleX = 0.5;
   // scattersym.scaleY = 0.5;
    //scattersym.x = 50;
   // scattersym.y = 620;
	IntroScreen.addEventListener("click",ClickToStart);
	IntroContainer.addChild(IntroScreen);
    //IntroContainer.addChild(scattersym);
    var tfIntroContinueShadow = new createjs.Text(langFile.tap_to_continue);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroContinueShadow, json.tfIntroContinueShadow );
   // tfIntroContinue.text = langFile.tap_to_continue;
   IntroContainer.addChild(tfIntroContinueShadow);
    var tfIntroContinue = new createjs.Text( langFile.tap_to_continue);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroContinue, json.tfIntroContinue );
   // tfIntroContinue.text = langFile.tap_to_continue;
    IntroContainer.addChild(tfIntroContinue);
    var tfIntroTextShadow1 = new createjs.Text(langFile.Msg1);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow1, json.tfIntroTextShadow1 );
    IntroContainer.addChild(tfIntroTextShadow1);
    var tfIntroText1 = new createjs.Text(langFile.Msg1);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText1, json.tfIntroText1 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText1);
    //text onto intro screen started from here, it should be from IntroText2 to IntroText7 with their shadows as well
   var tfIntroTextShadow2 = new createjs.Text(langFile.IntroText2);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow2, json.tfIntroTextShadow2 );
    IntroContainer.addChild(tfIntroTextShadow2);
    var tfIntroText2 = new createjs.Text(langFile.IntroText2);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText2, json.tfIntroText2 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText2);
    var tfIntroTextShadow3 = new createjs.Text(langFile.IntroText3);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow3, json.tfIntroTextShadow3 );
    IntroContainer.addChild(tfIntroTextShadow3);
    var tfIntroText3 = new createjs.Text(langFile.IntroText3);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText3, json.tfIntroText3 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText3);
     var tfIntroTextShadow4 = new createjs.Text(langFile.IntroText4);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow4, json.tfIntroTextShadow4 );
    IntroContainer.addChild(tfIntroTextShadow4);
    var tfIntroText4 = new createjs.Text(langFile.IntroText4);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText4, json.tfIntroText4 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText4);
var tfIntroTextShadow8 = new createjs.Text(langFile.IntroText8);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow8, json.tfIntroTextShadow8 );
         IntroContainer.addChild(tfIntroTextShadow8);
         var tfIntroText8 = new createjs.Text(langFile.IntroText8);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText8, json.tfIntroText8 );
         //tfIntroText.text = langFile.Msg;
          IntroContainer.addChild(tfIntroText8);
var intro_ad_image2 = new createjs.Bitmap(gameServices.assets.getAsset("device/images/powerLogo.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( intro_ad_image2, json.intro_ad_image2 );
    IntroContainer.addChild(intro_ad_image2);
     var tfIntroTextShadow5 = new createjs.Text(langFile.IntroText5);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow5, json.tfIntroTextShadow5 );
         IntroContainer.addChild(tfIntroTextShadow5);
         var tfIntroText5 = new createjs.Text(langFile.IntroText5);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText5, json.tfIntroText5 );
         //tfIntroText.text = langFile.Msg;
          IntroContainer.addChild(tfIntroText5);
         var tfIntroTextShadow6 = new createjs.Text(langFile.IntroText6);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow6, json.tfIntroTextShadow6 );
         IntroContainer.addChild(tfIntroTextShadow6);
         var tfIntroText6 = new createjs.Text(langFile.IntroText6);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText6, json.tfIntroText6 );
         //tfIntroText.text = langFile.Msg;
          IntroContainer.addChild(tfIntroText6);
          var tfIntroTextShadow7 = new createjs.Text(langFile.IntroText7);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow7, json.tfIntroTextShadow7 );
         IntroContainer.addChild(tfIntroTextShadow7);
         var tfIntroText7 = new createjs.Text(langFile.IntroText7);
         amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText7, json.tfIntroText7 );
         //tfIntroText.text = langFile.Msg;
          IntroContainer.addChild(tfIntroText7);
    //text onto intro screen ended here
    //ad images onto intro screens
    var intro_ad_image1 = new createjs.Bitmap(gameServices.assets.getAsset("device/images/xStaticWild.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( intro_ad_image1, json.intro_ad_image1 );
    IntroContainer.addChild(intro_ad_image1);
    /*var tfIntroText2 = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText2, json.tfIntroText2 );
    tfIntroText2.text = langFile.Msg2;
    IntroContainer.addChild(tfIntroText2);*/
    container.addChild(IntroContainer);
	function ClickToStart(event){
	instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
	}
    instance.hideIntro = function () {
        if ( container.contains(IntroContainer) ) {
	        container.removeChild(IntroContainer);
          }
		};
		return instance;
    };
/**
 *
 * @param {number} openingBalance
 * @param {Array.<number>} creditValueList
 * @param {number} numberOfLines
 */
amaya.game.Model = function ( openingBalance, creditValueList, numberOfLines ) {
    var instance = {};
    /**
     * @type {number}
     */
    var balance;
    /**
     * @type {Array.<number>}
     */
    var creditValues;
    /**
     * @type {number}
     */
    var lines;
    /**
     * @type {number}
     */
    var bet = 0;
    /**
     * @type {number}
     */
    var creditsPerLine = 0;
    /**
     * @type {number}
     */
    var maxCreditsPerLine = 5;
    /**
     * @type {number}
     */
    var creditsWon = 0;
    /**
     * @type {number}
     */
    var scatterWin = 0;
    /**
     * @type {number}
     */
    var creditValueIndex = 0;
    /**
     * @type {Array.<number>}
     */
    var reelStops;
    /**
     * @type {Array.<number>}
     */
    var baseReelStops;
    /**
     * @type {number}
     */
    var linesWon = 0;
    /**
     * @type {Array.<object>}
     */
    var winningLines;
    /**
     * @type {number}
     */
    var spinStartTime = 0;
    /**
     * @type {number}
     */
    var freeSpinsTotal = 0;
    /**
     * @type {number}
     */
    var scatterLength;
      /**
       * @type {number}
      */
    var freeSpinsUsed = 0;
    /**
     * @type {number}
     */
    var numfreeSpinsWon = 0;
    /**
     * @type {number}
     */
    var freeSpinsWon = 0;
    /**
     * @type {boolean}
     */
    var freeSpinsTriggered = false;
    /**
     * @type {boolean}
     */
    var flag = false;
    /**
     * @type {number}
     */
    var gameState = 0; // 0 is Regular Spin, 1 is Free Spin
    var isOneWinAnimationCycleComplete;
    /**
     * @type {boolean}
     */
    var gameInProgress = false;
    /**
     * @type {Array.<object>}
     */
    var scatterHitSound = [];
    /**
     * @type {boolean}
     */
    var freeSpinsHasWins = false;
    var streamStack =[];
    var allpos = [];
    var ScatterWays;
	var SymbolsWays;
    var winningLineId;
     /**
      * @returns {Array.<object>}
       */
      instance.getScatterHitSound = function (value) {
           return scatterHitSound[value];
       };
      /**
      * @param {Array.<object>} value
      */
      instance.setScatterHitSound = function (value) {
           scatterHitSound = value;
      };
    instance.getStreamStack =  function () {
         return streamStack;
     };
    instance.setStreamStack = function (value) {
           console.log("setStreamStack",value);
           streamStack = value;
    };
    instance.getAllPosition =  function () {
         return allpos;
     };
    instance.setAllPosition = function (value) {
           allpos = value;
    };
     instance.getScatterWays =  function () {
         return ScatterWays;
     };
    instance.setScatterAllPositions = function (value) {
           ScatterWays = value;
    };
    /**
     * @param {boolean} value
     */
    instance.setFreeSpinsHasWins = function (value) {
        freeSpinsHasWins = value;
    };
    /**
     * @returns {boolean}
     */
    instance.getFreeSpinsHasWins = function () {
        return freeSpinsHasWins;
    };
    instance.resetForSpin = function () {
        reelStops = undefined;
        winningLines = [];
        creditsWon = 0;
        freeSpinsTriggered = false;
    };
    instance.resetAfterFreeSpinBonus = function () {
        freeSpinsTotal = 0;
        freeSpinsUsed = 0;
        freeSpinsWon = 0;
        numfreeSpinsWon = 0;
    };
    /**
     * @returns {number}
     */
    instance.getLines = function () {
        return lines;
    };
    /**
     * @returns {number}
     */
    instance.getBet = function () {
        return bet;
    };
    /**
     * @returns {number}
     */
    instance.getCreditsPerLine = function () {
        return creditsPerLine;
    };
    /**
     * @param {number} value
     */
    instance.setCreditsPerLine = function (value) {
        creditsPerLine = value;
        updateBet();
    };
    /**
     * @returns {number}
     */
    instance.getMaxCreditsPerLine = function () {
        return maxCreditsPerLine;
    };
    /**
     * @returns {number}
     */
    instance.getBalance = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(balance);
    };
    /**
     * @param {number} value
     */
    instance.setBalance = function (value) {
        balance = value;
    };
    /**
     * @returns {number}
     */
    instance.getCredits = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(balance / instance.getCreditValue());
    };
    /**
     * @returns {number}
     */
    instance.getCreditValue = function () {
        return creditValues[creditValueIndex];
    };
    /**
     * @returns {number}
     * @throws Invalid credit value.
     */
    instance.setCreditValue = function ( val ) {
        var index = creditValues.indexOf(val);
        if ( index != -1 ) {
            creditValueIndex = index;
        } else {
            throw "Credit value " + val + " is not valid for this profile ("+ creditValues +")";
        }
    };
    /**
     * @returns {number}
     */
    instance.increaseCreditValue = function () {
        if (creditValueIndex < creditValues.length-1) {
            creditValueIndex++;
        }
        return instance.getCreditValue();
    };
    /**
     * @returns {number}
     */
    instance.decreaseCreditValue = function () {
        if (creditValueIndex > 0) {
            creditValueIndex--;
        }
        return instance.getCreditValue();
    };
    /**
     * @returns {number}
     */
    instance.getWon = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(linesWon * instance.getCreditValue());
    };
    /**
     * @returns {number}
     */
    instance.getCreditsWon = function () {
        return creditsWon;
    };
    /**
     * @param {number} value
     */
    instance.setCreditsWon = function (value) {
        creditsWon = value;
    };
    /**
     * @returns {number}
     */
    instance.getScatterWin = function () {
        return scatterWin;
    };
    /**
     * @param {number} value
     */
    instance.setScatterWin = function (value) {
        scatterWin = value;
    };
    instance.getScatterLength = function (value) {
                 return scatterLength;
    };
    instance.setScatterLength = function (value) {
            scatterLength = value;
    };
    /**
     * @returns {number}
     */
    instance.getNetWon = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint((linesWon - bet) * instance.getCreditValue());
    };
    /**
     * @returns {number}
     */
    instance.getNetCreditsWon = function () {
        return linesWon - bet;
    };
    /**
     * @returns {boolean}
     */
    instance.hasReelStops = function () {
        return reelStops != undefined;
    };
    /**
     * @returns {Array.<number>}
     */
    instance.getReelStops = function () {
        return reelStops;
    };
    /**
     * @returns {Array.<number>}
     */
    instance.getBaseReelStops = function () {
        return baseReelStops;
    };
    /**
     * @param {number} reel
     * @param {number} icon
     * @returns {number}
     */
    instance.getReelStop = function ( reel, icon ) {
        return reelStops[reel][icon];
    };
    /**
     * @param {number} reel
     * @param {number} icon
     * @returns {number}
     */
    instance.getBaseReelStop = function ( reel, icon ) {
        return baseReelStops[reel][icon];
    };
    /**
     * @param {Array.<number>} value
     */
    instance.setReelStops = function (value) {
        reelStops = value;
    };
    /**
     * @param {Array.<number>} value
     */
    instance.setBaseReelStops = function (value) {
        baseReelStops = value;
    };
    /**
     * @returns {Array.<object>}
     */
    instance.getWinningLines = function () {
        return winningLines;
    };
    /**
     * @param {Array.<object>} value
     */
    instance.setWinningLines = function (value) {
        winningLines = value;
    };
    /**
     * @returns {number}
     */
    instance.getSpinStartTime = function () {
        return spinStartTime;
    };
    /**
     * @param {number} value
     */
    instance.setSpinStartTime = function (value) {
        spinStartTime = value;
    };
    function updateBet () {
        bet = creditsPerLine * lines;
    }
    /**
     * @returns {number}
     */
    instance.getFreeSpinsTotal = function () {
        return freeSpinsTotal;
    };
    /**
     * @param {number} value
     */
    instance.setFreeSpinsTotal = function (value) {
        freeSpinsTotal = value;
    };
    /**
     * @returns {number}
     */
    instance.getFreeSpinsUsed = function () {
        return freeSpinsUsed;
    };
    /**
     * @param {number} value
     */
    instance.setFreeSpinsUsed = function (value) {
        freeSpinsUsed = value;
    };
    /**
     * @returns {number}
     */
    instance.getFreeSpinsWon = function () {
        return freeSpinsWon;
    };
    /**
     * @param {number} value
     */
    instance.setFreeSpinsWon = function (value) {
        freeSpinsWon = value;
    };
    /**
     * @returns {number}
     */
    instance.getNumFreeSpinsWon = function () {
        return numfreeSpinsWon;
    };
    /**
     * @param {number} value
     */
    instance.setNumFreeSpinsWon = function (value) {
        numfreeSpinsWon = value;
    };
    /**
     * @returns {boolean}
     */
    instance.getFreeSpinsTriggered = function () {
        return freeSpinsTriggered;
    };
    /**
     * @param {boolean} value
     */
    instance.setFreeSpinsTriggered = function (value) {
        freeSpinsTriggered = value;
    };
    /**
     * @returns {number}
     */
    instance.getGameState = function () {
        return gameState;
    };
    /**
     * @param {number} value
     */
    instance.setGameState = function (value) {
        gameState = value;
    };
    /**
     * @returns {boolean}
     */
    instance.spinHasPaylineWins = function () {
        return winningLines.length > 0;
    };
    /**
     * @returns {number}
     */
    instance.getLinesWon = function () {
        return linesWon;
    };
    /**
     * @param {number} value
     */
    instance.setLinesWon = function (value) {
        linesWon = value;
    };
    /**
     * @returns {boolean}
     */
    instance.getGameInProgress = function () {
        return gameInProgress;
    };
    /**
     * @param {boolean} gameInProgress
     */
    instance.setGameInProgress = function (isGameInProgress) {
        gameInProgress = isGameInProgress;
    };
    /**
     * @returns {number}
     */
    instance.getWager = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(bet * instance.getCreditValue());
    };
    /**
     * @returns {number}
     */
    instance.getDisplayBalance = function () {
        return balance - instance.getWager();
    };
    /**
     * @returns {number}
     */
    instance.getDisplayCredits = function () {
        return instance.getCredits() - bet;
    };
    /**
     * @param {boolean} value
     */
    instance.setOneWinAnimationCycleComplete = function (value) {
        isOneWinAnimationCycleComplete = value;
    }
        instance.getFlag = function () {
            return flag;
        };
    /**
     * @returns {boolean}
     */
    instance.hasOneWinAnimationCycleCompleted = function () {
        return isOneWinAnimationCycleComplete;
    }
    /**
     * @param {boolean} value
     */
    instance.setFlag = function (value) {
        flag = value;
    }
	instance.getSymbolsWays =function(){
             return SymbolsWays;
    }
    instance.setSymbolsWays =function(value){
            SymbolsWays = value;
    }
    instance.getWinningLineId= function(){
            return winningLineId;
    };
    instance.setWinningLineId= function(value){
             winningLineId = value;
    };
    balance = openingBalance;
    creditValues = creditValueList;
    creditValueIndex = 0;
    lines = numberOfLines;
    creditsPerLine = amaya.game.GameConstants.DEFAULT_CREDITS_PER_LINE;
    updateBet();
    instance.resetForSpin();
    return instance;
};
amaya.game.PaylineView = function ( container, winboxContainer, model, reelView ) {
    var instance = {};
    var gameServices = new amaya.GameServices();
   // var paylinePos = [[1,1,1,1,1],[0,0,0,0,0],[2,2,2,2,2],[0,1,2,1,0],[2,1,0,1,2],[0,1,0,1,0],[2,1,2,1,2],[1,0,1,0,1],[1,2,1,2,1],[1,1,0,1,1],[1,1,2,1,1],[0,1,1,1,0],[2,1,1,1,2],[2,2,1,0,0],[0,0,1,2,2],[1,0,0,0,1],[1,2,2,2,1],[2,1,0,0,0],[0,1,2,2,2],[2,2,2,1,0],[0,0,0,1,2],[1,1,1,0,2],[1,2,1,1,1],[0,0,0,1,0],[2,1,2,2,2]];
    var json = gameServices.assets.getAsset("device/main.json");
    var scatterContainer = new createjs.Container();
    container.addChild(scatterContainer);
    var reelBg_BaseData = json.reelBg_BaseData;
    reelBg_BaseData.images = [gameServices.assets.getAsset(json.reelBg_BaseData.image)];
    var reelbg_BaseSS = new createjs.SpriteSheet(reelBg_BaseData);
    var reelBg_FSData = json.reelBg_FSData;
    reelBg_FSData.images = [gameServices.assets.getAsset(json.reelBg_FSData.image)];
    var reelbg_FSSS = new createjs.SpriteSheet(reelBg_FSData);
    var bgContainer = new createjs.Container();
    container.addChild(bgContainer);
    amaya.framework.utils.JSON.fromJSON( container, json.reels );
    amaya.framework.utils.JSON.fromJSON( winboxContainer, json.reels );
   var linePos = [];
       function setupPayline (pos) {
           bgContainer.removeAllChildren();
           //container.removeChild(bgContainer);
           var mcLineComposite = [];
           // Base reel bg
           var mcLine = [];
           for (var i = 0; i < pos.length; i++) {
               var mcLinePos = new createjs.Sprite(model.getGameState() === amaya.game.GameConstants.SPIN_STATE?reelbg_BaseSS:reelbg_FSSS);
               mcLinePos.gotoAndStop((pos[i][0]-1) + ((pos[i][1]-1)*amaya.game.GameConstants.REELS));
               amaya.framework.utils.JSON.fromJSON( mcLinePos, json["reel"+(pos[i][0])] );
               mcLinePos.x += json.reelBg_BaseData.spacing;
              // console.log("mcLinePos.x",mcLinePos.x);
               mcLinePos.y += ((pos[i][1]-1) * json.icons.spacing) + json.reelBg_BaseData.spacing;
             //  console.log("mcLinePos.y",mcLinePos.y);
               bgContainer.addChild(mcLinePos);
              // container.addChild(bgContainer);
               mcLine.push(mcLinePos);
           }
           mcLineComposite.push(mcLine);
           linePos.push(mcLineComposite);
       }
    var lineWinContainer = new createjs.Container();
     winboxContainer.addChild(lineWinContainer);
    function hidePayline ( line ) {
        //lines[line-1].visible = false;
        for (var i = 0; i < amaya.game.GameConstants.REELS; i++) {
            linePos[line-1][0][i].visible = false;
            linePos[line-1][1][i].visible = false;
        }
    }
    /*instance.showAllPaylineWin = function () {
         var lines = model.getWinningLines();
         for ( var i=0; i<lines.length; i++ ) {
            if (lines[i].id  !== 0) {
                 setupPayline(pos);
               // showPayline(line, pos);
                showPaylineWinBox(pos);
            }
        }
    };*/
    instance.showPaylineWin = function (line,pos) {
        if (line !== 0) {
             setupPayline(pos);
           // showPayline(line, pos);
            showPaylineWinBox(pos);
        }
    };
    instance.showScatterBg = function ( positions ) {
       // console.log("show scatter bg", positions);
        var state = model.getGameState();// === amaya.game.GameConstants.SPIN_STATE;
        var tcontainer = new createjs.Container();
        for (var i = 0; i < positions.length; i++) {
            var mcLinePos = new createjs.Sprite(model.getGameState() === amaya.game.GameConstants.SPIN_STATE?reelbg_BaseSS:reelbg_FSSS);
            mcLinePos.gotoAndStop(positions[i][1] - 1);
            amaya.framework.utils.JSON.fromJSON( mcLinePos, json["reel"+(positions[i][0])] );
            mcLinePos.x += json.reelBg_BaseData.spacing;
            mcLinePos.y += ((positions[i][1] - 1) * json.icons.spacing) + json.reelBg_BaseData.spacing;
            tcontainer.addChild(mcLinePos);
          //  console.log("adding scatter bg", tcontainer);
        }
        scatterContainer.removeAllChildren();
        scatterContainer.addChild(tcontainer);
    }
    instance.showScatterMarkerBg = function ( reel, positions ) {
       // console.log("show scatter bg", positions);
        var state = model.getGameState();// === amaya.game.GameConstants.SPIN_STATE;
        var tcontainer = new createjs.Container();
        for (var i = 0; i < positions.length; i++) {
            if (positions[i] === amaya.game.GameConstants.SYM_SCATTER) {
                var mcLinePos = new createjs.Sprite(model.getGameState() === amaya.game.GameConstants.SPIN_STATE?reelbg_BaseSS:reelbg_FSSS);
                mcLinePos.gotoAndStop( reel + ( i *amaya.game.GameConstants.REELS));
                amaya.framework.utils.JSON.fromJSON( mcLinePos, json["reel"+(reel + 1)] );
                mcLinePos.x += json.reelBg_BaseData.spacing;
                mcLinePos.y += (i * json.icons.spacing) + json.reelBg_BaseData.spacing;
                tcontainer.addChild(mcLinePos);
            }
          //  console.log("adding scatter bg", tcontainer);
        }
        //scatterContainer.removeAllChildren();
        scatterContainer.addChild(tcontainer);
    }
    instance.showAllPosBg = function ( positions ) {
            console.log("showAllPosBg positions ", positions);
            var state = model.getGameState();// === amaya.game.GameConstants.SPIN_STATE;
            var tcontainer = new createjs.Container();
            for (var i = 0; i < positions.length; i++) {
                var mcLinePos = new createjs.Sprite(model.getGameState() === amaya.game.GameConstants.SPIN_STATE?reelbg_BaseSS:reelbg_FSSS);
                mcLinePos.gotoAndStop((positions[i][0]-1) + ((positions[i][1]-1)*amaya.game.GameConstants.REELS));
                amaya.framework.utils.JSON.fromJSON( mcLinePos, json["reel"+(positions[i][0])] );
                mcLinePos.x += json.reelBg_BaseData.spacing;
                mcLinePos.y += ((positions[i][1] - 1) * json.icons.spacing) + json.reelBg_BaseData.spacing;
                tcontainer.addChild(mcLinePos);
              //  console.log("adding scatter bg", tcontainer);
            }
            //scatterContainer.removeAllChildren();
            scatterContainer.addChild(tcontainer);
        }
     instance.hideAllPosBg = function(){
         scatterContainer.removeAllChildren();
     }
    /*instance.hidePaylineWin = function ( line ) {
        hidePaylineWinBox(line);
        bgContainer.removeAllChildren();
        scatterContainer.removeAllChildren();
    };*/
    /*instance.showAllPaylineWins = function () {
        var lines = model.getWinningLines();
        for ( var i=0; i<lines.length; i++ ) {
            if (lines[i].id !== 0)
                instance.showPaylineWin( lines[i].id, *//*lines[i].positions,*//* lines[i].positions.length, lines[i].won );
        }
    };*/
    instance.hideAllPaylineWins = function () {
        lineWinContainer.removeAllChildren();
        bgContainer.removeAllChildren();
        scatterContainer.removeAllChildren();
        linePos = [];
    };
    function showPaylineWinBox (pos ) {
        createWinBox ( pos );
    }
   function createWinBox ( pos ) {
        for (var i = 0; i < pos.length; i++) {
            var winframeData = json.winboxData;
            winframeData.images = [gameServices.assets.getAsset(json.winboxData.image)];
            var winframeAnimSS = new createjs.SpriteSheet(winframeData);
            var winframeAnim = new createjs.Sprite(winframeAnimSS);
            winframeAnim.gotoAndStop(0);
            winframeAnim.gotoAndPlay("winboxAnim");
          //  var winBoxImg =  new createjs.Bitmap( gameServices.assets.getAsset("device/images/Winbox/winbox.png") );
            var winbox = new createjs.Container();
            winbox.addChild(winframeAnim);
            lineWinContainer.addChild(winbox);
           // winbox.id = "Winbox_"+i;
        }
        setupWinboxCoordinates(lineWinContainer,pos);
    }
    function getReelCoordinates(reelIndex) {
        switch (reelIndex) {
            case 0: return json.reel1;
            case 1: return json.reel2;
            case 2: return json.reel3;
            case 3: return json.reel4;
            case 4: return json.reel5;
        }
    }
    function setupWinboxCoordinates (lineWinContainer, pos) {
        for (var i = 0; i < pos.length; i++) {
            lineWinContainer.getChildAt(i).x = getReelCoordinates(pos[i][0]-1).x;
            lineWinContainer.getChildAt(i).y = getReelCoordinates(pos[i][0]-1).y + (pos[i][1]-1)*json.icons.spacing;
        }
    }
    function getWinBox ( ) {
             return mcLineWin;
    }
    reelView.getPaylineView(instance);
    return instance;
}
/**
 * Created with IntelliJ IDEA.
 * User: amansoor
 * Date: 12/5/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
amaya.game.ReelView = function ( container, symAnimContainer, model, backgroundContainer, reelStreamContainer, soundManager ) {
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
        function getBlueStreamData ( ) {
                var reelBlueStreamData = mainjson.BlueStreamData;
                reelBlueStreamData.images = [gameServices.assets.getAsset(mainjson.BlueStreamData.image)];
                var  reelBlueStreamSS = new createjs.SpriteSheet(reelBlueStreamData);
                var reelBlueStream = new createjs.Sprite( reelBlueStreamSS);
                reelBlueStream.gotoAndStop(0);
                reelBlueStream.gotoAndPlay("reelBlueStreamAnim");
                return reelBlueStream;
        }
        instance.playBlueStream = function(x1){
            soundManager.PlayReelSrikeSound();
            var blueStream = getBlueStreamData();
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
    return instance;
}
amaya.game.SoundConstants = {
    STAR: "null",
    BUTTON: "bet1",
    INTRO: {id:"audio1",start:111,end:4631,loop:0},
    SPIN: {id:"spin_click",start:126,end:283,loop:0},
    BASE_WIN: {id:"WinTickup",start:168,end:4991,loop:1},
    WIN: "null",
    BIGWIN:  {id:"big_win",start:044,end:5800,loop:1},
    FEATURE: "null",
    FREE_SPIN_END: "null",
    BONUS_MARKER_2: {id:"bonus_markers",start:53,end:1684,loop:0},
    BONUS_MARKER_3: {id:"bonus_markers",start:2914,end:4571,loop:0},
    BONUS_MARKER_4: {id:"bonus_markers",start:5801,end:7431,loop:0},
    FIREWOLF: {id:"audio1",start:7322,end:11093,loop:0},
    FREE_SPIN_INTRO: {id:"audio1",start:11898,end:15753,loop:0},
    RETRIGGER: {id:"audio2",start:90,end:3269,loop:0},
    FREE_SPIN_OUTRO: {id:"audio2",start:13302,end:16904,loop:0},
    FREE_SPIN: {id:"audio2",start:20424,end:28299,loop:1},
    ANTICIPATION: {id:"anticipation_reel4",start:65,end:4332,loop:0},
    REEL_SPIN1: {id:"audio3",start:10929,end:14642,loop:1},
    REEL_SPIN2: {id:"audio3",start:15966,end:19729,loop:1},
    REEL_SPIN3: {id:"audio3",start:20993,end:24621,loop:1},
    REEL_SPIN4: {id:"audio3",start:5902,end:9543,loop:1},
    SCATTER: {id:"audio4",start:365,end:3066,loop:0},
    LOGO: {id:"audio4",start:4587,end:7887,loop:0},
    REDWOLF: {id:"audio4",start:9333,end:12504,loop:0},
    BLUEWOLF: {id:"audio4",start:14343,end:17769,loop:0},
    ACE: {id:"audio4",start:19723,end:23079,loop:0},
    KING: {id:"audio5",start:185,end:3501,loop:0},
    QUEEN: {id:"audio5",start:5057,end:8300,loop:0},
    JACK: {id:"audio5",start:9964,end:13201,loop:0},
    PAWS: {id:"audio5",start:19771,end:23124,loop:0},
    FIREBIRD: {id:"audio5",start:14875,end:18197,loop:0},
    REEL_STRIKE: {id:"audio5",start:24493,end:27569,loop:0},
    REEL_STOP: {id:"reel_stop",start:79,end:151,loop:0},
    PRE_BIGWIN_BLUE: {id:"audio6",start:5983,end:8772,loop:0},
    PRE_BIGWIN_RED: {id:"audio6",start:10252,end:13026,loop:0},
    WILD: {id:"audio6",start:14508,end:17917,loop:0}
};
amaya.game.SoundManager = function ( model, soundSystem ) {
    var instance = {};
    var gameServices = new amaya.GameServices ();
    var anticipation;
    var bonus_marker_01;
    var bonus_marker_02;
    var bonus_marker_03;
    var bonus_marker_04;
    var bonus_marker_05;
    var bigWin;
    var intro;
    var spinSound;
    var reelSpin;
    var reelStopSound;
    var winTickUp;
    var spinButton;
    var freeSpinSound;
    var fireWolf;
    var freeOutro;
    var freeIntro;
    var freeRetrigg;
    var reelStrikeSound;
    var PreBigWinBlueSound;
    var PreBigWinRedSound;
    var wildSound, wildSound2, fwlogo, redwolf, bluewolf, firebird, paws, Ace, King, Queen, Jack, Ten, Nine, scatter, wildSoundMix;
    instance.PlayAnticipation = function () {
        if(anticipation){
            anticipation.stop();
            createjs.Ticker.removeEventListener("tick",anticipationComplete);
        }
        anticipation = soundSystem.play(amaya.game.SoundConstants.ANTICIPATION.id,0,amaya.game.SoundConstants.ANTICIPATION.start);
        createjs.Ticker.addEventListener("tick",anticipationComplete);
    };
    var anticipationComplete = function(e){
        var currPos = anticipation.getPosition();
        if (currPos >= amaya.game.SoundConstants.ANTICIPATION.end){
            if(amaya.game.SoundConstants.ANTICIPATION.loop){
                anticipation.setPosition(amaya.game.SoundConstants.ANTICIPATION.start);
            }else{
                anticipation.stop();
                createjs.Ticker.removeEventListener("tick",anticipationComplete);
            }
        }
    };
    instance.StopAnticipation = function () {
         if(anticipation){
            anticipation.stop();
            createjs.Ticker.removeEventListener("tick",anticipationComplete);
        }
    };
    instance.PlayBonus_marker_02 = function () {
        if(bonus_marker_02){
              bonus_marker_02.stop();
              createjs.Ticker.removeEventListener("tick",bonus_marker_02_Complete);
        }
        bonus_marker_02 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_2.id,0,amaya.game.SoundConstants.BONUS_MARKER_2.start);
        createjs.Ticker.addEventListener("tick",bonus_marker_02_Complete);
    };
    var bonus_marker_02_Complete = function(e){
        var currPos = bonus_marker_02.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_2.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_2.loop){
                bonus_marker_02.setPosition(amaya.game.SoundConstants.BONUS_MARKER_2.start);
            }else{
                bonus_marker_02.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_02_Complete);
            }
        }
    };
    instance.PlayBonus_marker_03 = function () {
        if(bonus_marker_03){
            bonus_marker_03.stop();
            createjs.Ticker.removeEventListener("tick",bonus_marker_03_Complete);
        }
        bonus_marker_03 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_3.id,0,amaya.game.SoundConstants.BONUS_MARKER_3.start);
        createjs.Ticker.addEventListener("tick",bonus_marker_03_Complete);
    };
    var bonus_marker_03_Complete = function(e){
        var currPos = bonus_marker_03.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_3.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_3.loop){
                bonus_marker_03.setPosition(amaya.game.SoundConstants.BONUS_MARKER_3.start);
            }else{
                bonus_marker_03.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_03_Complete);
            }
        }
    };
    instance.PlayBonus_marker_04 = function () {
           if(bonus_marker_04){
                bonus_marker_04.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_04_Complete);
           }
            bonus_marker_04 = soundSystem.play(amaya.game.SoundConstants.BONUS_MARKER_4.id,0,amaya.game.SoundConstants.BONUS_MARKER_4.start);
            createjs.Ticker.addEventListener("tick",bonus_marker_04_Complete);
    };
    var bonus_marker_04_Complete = function(e){
        var currPos = bonus_marker_04.getPosition();
        if (currPos >= amaya.game.SoundConstants.BONUS_MARKER_4.end){
            if(amaya.game.SoundConstants.BONUS_MARKER_4.loop){
                bonus_marker_04.setPosition(amaya.game.SoundConstants.BONUS_MARKER_4.start);
            }else{
                bonus_marker_04.stop();
                createjs.Ticker.removeEventListener("tick",bonus_marker_04_Complete);
            }
        }
    };
    instance.PlayBigWin = function () {
        if(bigWin){
            bigWin.stop();
            createjs.Ticker.removeEventListener("tick",BigWinComplete);
        }
        bigWin = soundSystem.play(amaya.game.SoundConstants.BIGWIN.id,0,amaya.game.SoundConstants.BIGWIN.start);
        createjs.Ticker.addEventListener("tick",BigWinComplete);
    };
    var BigWinComplete = function(e){
        var currPos = bigWin.getPosition();
        if (currPos >= amaya.game.SoundConstants.BIGWIN.end){
            if(amaya.game.SoundConstants.BIGWIN.loop){
                bigWin.setPosition(amaya.game.SoundConstants.BIGWIN.start);
            }else{
                bigWin.stop();
                createjs.Ticker.removeEventListener("tick",BigWinComplete);
            }
        }
    };
    instance.StopBigWin = function () {
        if (bigWin) {
            bigWin.stop();
            createjs.Ticker.removeEventListener("tick",BigWinComplete);
        }
    };
    instance.PlayIntro = function () {
        if(intro) {
            intro.stop();
            createjs.Ticker.removeEventListener("tick",IntroComplete);
        }
        intro = soundSystem.play(amaya.game.SoundConstants.INTRO.id,0,amaya.game.SoundConstants.INTRO.start);
        createjs.Ticker.addEventListener("tick",IntroComplete);
    };
    var IntroComplete = function(e){
        var currPos = intro.getPosition();
        if (currPos >= amaya.game.SoundConstants.INTRO.end){
            if(amaya.game.SoundConstants.INTRO.loop){
                intro.setPosition(amaya.game.SoundConstants.INTRO.start);
            }else{
                intro.stop();
                createjs.Ticker.removeEventListener("tick",IntroComplete);
            }
        }
    };
     instance.StopIntroSound = function () {
            if (intro) {
                intro.stop();
                createjs.Ticker.removeEventListener("tick",IntroComplete);
            }
     };
    var spinSoundConstant;
    var x =1;
    instance.PlaySpinSound = function () {
            if(spinSound){
                 spinSound.stop();
                 createjs.Ticker.removeEventListener("tick",spinSoundComplete);
                }
           //  x = Math.floor((Math.random() * 4) +1);
            reelSpin = 'REEL_SPIN' +x;
            if(x==4){
                 x=1;
             }
             else{
                 x++;
             }
            spinSound = soundSystem.play(amaya.game.SoundConstants[reelSpin].id,0,amaya.game.SoundConstants[reelSpin].start);
            spinSoundConstant = amaya.game.SoundConstants[reelSpin];
            createjs.Ticker.addEventListener("tick",spinSoundComplete);
    };
    var spinSoundComplete = function(e){
        var currPos = spinSound.getPosition();
        if (currPos >= spinSoundConstant.end){
            if(spinSoundConstant.loop){
                spinSound.setPosition(spinSoundConstant.start);
            }else{
                spinSound.stop();
                createjs.Ticker.removeEventListener("tick",spinSoundComplete);
            }
        }
    };
    instance.StopSpinSound = function () {
            if (spinSound) {
                spinSound.stop();
                createjs.Ticker.removeEventListener("tick",spinSoundComplete);
            }
    };
    instance.PlayReelStopSound = function () {
        if (reelStopSound) {
            reelStopSound.stop();
            createjs.Ticker.removeEventListener("tick",reelStopSoundComplete);
        }
        reelStopSound = soundSystem.play(amaya.game.SoundConstants.REEL_STOP.id,0,amaya.game.SoundConstants.REEL_STOP.start);
        createjs.Ticker.addEventListener("tick",reelStopSoundComplete);
    };
    var reelStopSoundComplete = function(e){
        var currPos = reelStopSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.REEL_STOP.end){
            if(amaya.game.SoundConstants.REEL_STOP.loop){
                reelStopSound.setPosition(amaya.game.SoundConstants.REEL_STOP.start);
            }else{
                reelStopSound.stop();
                createjs.Ticker.removeEventListener("tick",reelStopSoundComplete);
            }
        }
    };
    instance.PlayReelSrikeSound = function () {
        if (reelStrikeSound) {
            reelStrikeSound.stop();
            createjs.Ticker.removeEventListener("tick",reelStrikeSoundComplete);
        }
        reelStrikeSound = soundSystem.play(amaya.game.SoundConstants.REEL_STRIKE.id,0,amaya.game.SoundConstants.REEL_STRIKE.start);
        createjs.Ticker.addEventListener("tick",reelStrikeSoundComplete);
    };
    var reelStrikeSoundComplete = function(e){
        var currPos = reelStrikeSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.REEL_STRIKE.end){
            if(amaya.game.SoundConstants.REEL_STRIKE.loop){
                reelStrikeSound.setPosition(amaya.game.SoundConstants.REEL_STRIKE.start);
            }else{
                reelStrikeSound.stop();
                createjs.Ticker.removeEventListener("tick",reelStrikeSoundComplete);
            }
        }
    };
    instance.PreBigWinBlueSound = function () {
        if (PreBigWinBlueSound) {
            PreBigWinBlueSound.stop();
            createjs.Ticker.removeEventListener("tick",PreBigWinBlueSoundComplete);
        }
        PreBigWinBlueSound = soundSystem.play(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.id,0,amaya.game.SoundConstants.PRE_BIGWIN_BLUE.start);
        createjs.Ticker.addEventListener("tick",PreBigWinBlueSoundComplete);
    };
    var PreBigWinBlueSoundComplete = function(e){
        var currPos = PreBigWinBlueSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.PRE_BIGWIN_BLUE.end){
            if(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.loop){
                PreBigWinBlueSound.setPosition(amaya.game.SoundConstants.PRE_BIGWIN_BLUE.start);
            }else{
                PreBigWinBlueSound.stop();
                createjs.Ticker.removeEventListener("tick",PreBigWinBlueSoundComplete);
            }
        }
    };
    instance.PreBigWinRedSound = function () {
        if (PreBigWinRedSound) {
            PreBigWinRedSound.stop();
            createjs.Ticker.removeEventListener("tick",PreBigWinRedSoundComplete);
        }
        PreBigWinRedSound = soundSystem.play(amaya.game.SoundConstants.PRE_BIGWIN_RED.id,0,amaya.game.SoundConstants.PRE_BIGWIN_RED.start);
        createjs.Ticker.addEventListener("tick",PreBigWinRedSoundComplete);
    };
    var PreBigWinRedSoundComplete = function(e){
        var currPos = PreBigWinRedSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.PRE_BIGWIN_RED.end){
            if(amaya.game.SoundConstants.PRE_BIGWIN_RED.loop){
                PreBigWinRedSound.setPosition(amaya.game.SoundConstants.PRE_BIGWIN_RED.start);
            }else{
                PreBigWinRedSound.stop();
                createjs.Ticker.removeEventListener("tick",PreBigWinRedSoundComplete);
            }
        }
    };
    instance.PlayWinTickUp = function () {
        if(winTickUp){
            winTickUp.stop();
            createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
        }
        winTickUp = soundSystem.play(amaya.game.SoundConstants.BASE_WIN.id,0,amaya.game.SoundConstants.BASE_WIN.start);
        createjs.Ticker.addEventListener("tick",WinTickUpComplete);
    };
    var WinTickUpComplete = function(e){
        var currPos = winTickUp.getPosition();
        if (currPos >= amaya.game.SoundConstants.BASE_WIN.end){
            if(amaya.game.SoundConstants.BASE_WIN.loop){
                winTickUp.setPosition(amaya.game.SoundConstants.BASE_WIN.start);
            }else{
                winTickUp.stop();
                createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
            }
        }
    };
    instance.StopWinTickUp = function () {
        if (winTickUp) {
            winTickUp.stop();
            createjs.Ticker.removeEventListener("tick",WinTickUpComplete);
        }
    };
    instance.PlaySpinButton = function () {
        if (spinButton) {
            spinButton.stop();
            createjs.Ticker.removeEventListener("tick",SpinButtonComplete);
        }
        spinButton = soundSystem.play(amaya.game.SoundConstants.SPIN.id,0,amaya.game.SoundConstants.SPIN.start);
        createjs.Ticker.addEventListener("tick",SpinButtonComplete);
    };
    var SpinButtonComplete = function(e){
        var currPos = spinButton.getPosition();
        if (currPos >= amaya.game.SoundConstants.SPIN.end){
            if(amaya.game.SoundConstants.SPIN.loop){
                spinButton.setPosition(amaya.game.SoundConstants.SPIN.start);
            }else{
                spinButton.stop();
                createjs.Ticker.removeEventListener("tick",SpinButtonComplete);
            }
        }
    };
    instance.PlayFreeSpinSound = function () {
        if (freeSpinSound) {
            freeSpinSound.stop();
            createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
        }
        freeSpinSound = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN.id,0,amaya.game.SoundConstants.FREE_SPIN.start);
        createjs.Ticker.addEventListener("tick",freeSpinSoundComplete);
    };
    var freeSpinSoundComplete = function(e){
        var currPos = freeSpinSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN.end){
            if(amaya.game.SoundConstants.FREE_SPIN.loop){
                freeSpinSound.setPosition(amaya.game.SoundConstants.FREE_SPIN.start);
            }else{
                freeSpinSound.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
            }
        }
    };
    instance.pauseFreeSpinSound = function () {
            if (freeSpinSound) {
                freeSpinSound.pause();
            }
    };
    instance.resumeFreeSpinSound = function () {
              freeSpinSound.resume();
    };
    instance.StopFreeSpinSound = function () {
            if (freeSpinSound) {
                freeSpinSound.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinSoundComplete);
            }
    };
     instance.PlayFireWolfSound = function () {
            if(fireWolf){
                 fireWolf.stop();
                 createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
            fireWolf = soundSystem.play(amaya.game.SoundConstants.FIREWOLF.id,0,amaya.game.SoundConstants.FIREWOLF.start);
            createjs.Ticker.addEventListener("tick",fireWolfComplete);
     };
    var fireWolfComplete = function(e){
        var currPos = fireWolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.FIREWOLF.end){
            if(amaya.game.SoundConstants.FIREWOLF.loop){
                fireWolf.setPosition(amaya.game.SoundConstants.FIREWOLF.start);
            }else{
                fireWolf.stop();
                createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
        }
    };
    instance.StopFireWolfSound = function () {
        if (fireWolf) {
            fireWolf.stop();
            createjs.Ticker.removeEventListener("tick",fireWolfComplete);
        }
    };
    instance.PlayFreeSpinIntroSound = function () {
            if(freeIntro){
                 freeIntro.stop();
                 createjs.Ticker.removeEventListener("tick",fireWolfComplete);
            }
            freeIntro = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_INTRO.id,0,amaya.game.SoundConstants.FREE_SPIN_INTRO.start);
            createjs.Ticker.addEventListener("tick",freeSpinIntroComplete);
     };
    var freeSpinIntroComplete = function(e){
        var currPos = freeIntro.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN_INTRO.end){
            if(amaya.game.SoundConstants.FREE_SPIN_INTRO.loop){
                freeIntro.setPosition(amaya.game.SoundConstants.FREE_SPIN_INTRO.start);
            }else{
                freeIntro.stop();
                createjs.Ticker.removeEventListener("tick",freeSpinIntroComplete);
            }
        }
    };
    instance.StopFreeSpinIntroSound = function () {
        if (freeIntro) {
            freeIntro.stop();
            createjs.Ticker.removeEventListener("tick",freeSpinIntroComplete);
        }
    };
    instance.PlayFreeOutroSound = function () {
        if (freeOutro) {
            freeOutro.stop();
            createjs.Ticker.removeEventListener("tick",FreeOutroComplete);
        }
            freeOutro = soundSystem.play(amaya.game.SoundConstants.FREE_SPIN_OUTRO.id,0,amaya.game.SoundConstants.FREE_SPIN_OUTRO.start);
            createjs.Ticker.addEventListener("tick",FreeOutroComplete);
    };
    var FreeOutroComplete = function(e){
        var currPos = freeOutro.getPosition();
        if (currPos >= amaya.game.SoundConstants.FREE_SPIN_OUTRO.end){
            if(amaya.game.SoundConstants.FREE_SPIN_OUTRO.loop){
                freeOutro.setPosition(amaya.game.SoundConstants.FREE_SPIN_OUTRO.start);
            }else{
                freeOutro.stop();
                createjs.Ticker.removeEventListener("tick",FreeOutroComplete);
            }
        }
    };
     instance.PlayFreeRetriggSound = function () {
        if (freeRetrigg) {
            freeRetrigg.stop();
            createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
        }
            freeRetrigg = soundSystem.play(amaya.game.SoundConstants.RETRIGGER.id,0,amaya.game.SoundConstants.RETRIGGER.start);
            createjs.Ticker.addEventListener("tick",FreeRetriggComplete);
    };
    var FreeRetriggComplete = function(e){
        var currPos = freeRetrigg.getPosition();
        if (currPos >= amaya.game.SoundConstants.RETRIGGER.end){
            if(amaya.game.SoundConstants.RETRIGGER.loop){
                freeRetrigg.setPosition(amaya.game.SoundConstants.RETRIGGER.start);
            }else{
                freeRetrigg.stop();
                createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
            }
        }
    };
     instance.StopFreeRetriggSound = function () {
        if (freeRetrigg) {
            freeRetrigg.stop();
            createjs.Ticker.removeEventListener("tick",FreeRetriggComplete);
        }
     };
    var wildSound = function () {
        if(wildSound){
           wildSound.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
        }
        wildSound = soundSystem.play(amaya.game.SoundConstants.WILD.id,0,amaya.game.SoundConstants.WILD.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete1);
    };
    var WinAnimComplete1 = function(e){
        var currPos = wildSound.getPosition();
        if (currPos >= amaya.game.SoundConstants.WILD.end){
            if(amaya.game.SoundConstants.WILD.loop){
                wildSound.setPosition(amaya.game.SoundConstants.WILD.start);
            }else{
                wildSound.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete1);
            }
        }
    };
    var logoSound = function () {
        if(fwlogo){
          fwlogo.stop();
          createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
        }
        fwlogo = soundSystem.play(amaya.game.SoundConstants.LOGO.id,0,amaya.game.SoundConstants.LOGO.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete2);
    };
    var WinAnimComplete2 = function(e){
        var currPos = fwlogo.getPosition();
        if (currPos >= amaya.game.SoundConstants.LOGO.end){
            if(amaya.game.SoundConstants.LOGO.loop){
                fwlogo.setPosition(amaya.game.SoundConstants.LOGO.start);
            }else{
                fwlogo.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete2);
            }
        }
    };
    var redWolfSound = function () {
        if(redwolf){
           redwolf.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
        }
        redwolf = soundSystem.play(amaya.game.SoundConstants.REDWOLF.id,0,amaya.game.SoundConstants.REDWOLF.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete3);
    };
    var WinAnimComplete3 = function(e){
        var currPos = redwolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.REDWOLF.end){
            if(amaya.game.SoundConstants.REDWOLF.loop){
                redwolf.setPosition(amaya.game.SoundConstants.REDWOLF.start);
            }else{
                redwolf.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete3);
            }
        }
    };
    var blueWolfSound = function () {
        if(bluewolf){
            bluewolf.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
        }
        bluewolf = soundSystem.play(amaya.game.SoundConstants.BLUEWOLF.id,0,amaya.game.SoundConstants.BLUEWOLF.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete4);
    };
    var WinAnimComplete4 = function(e){
        var currPos = bluewolf.getPosition();
        if (currPos >= amaya.game.SoundConstants.BLUEWOLF.end){
            if(amaya.game.SoundConstants.BLUEWOLF.loop){
                bluewolf.setPosition(amaya.game.SoundConstants.BLUEWOLF.start);
            }else{
                bluewolf.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete4);
            }
        }
    };
    var fireBirdSound = function () {
        if(firebird){
           firebird.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
        }
        firebird = soundSystem.play(amaya.game.SoundConstants.FIREBIRD.id,0,amaya.game.SoundConstants.FIREBIRD.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete5);
        //alert("sound position: "+firebird.getPosition());
    };
    var WinAnimComplete5 = function(e){
        var currPos = firebird.getPosition();
        if (currPos >= amaya.game.SoundConstants.FIREBIRD.end){
            if(amaya.game.SoundConstants.FIREBIRD.loop){
                firebird.setPosition(amaya.game.SoundConstants.FIREBIRD.start);
            }else{
                firebird.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete5);
            }
        }
    };
    var pawsSound = function () {
        if(paws){
           paws.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
        }
        paws = soundSystem.play(amaya.game.SoundConstants.PAWS.id,0,amaya.game.SoundConstants.PAWS.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete6);
        //alert("sound position: "+paws.getPosition());
    };
    var WinAnimComplete6 = function(e){
        var currPos = paws.getPosition();
        if (currPos >= amaya.game.SoundConstants.PAWS.end){
            if(amaya.game.SoundConstants.PAWS.loop){
                paws.setPosition(amaya.game.SoundConstants.PAWS.start);
            }else{
                paws.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete6);
            }
        }
    };
    var aceSound = function () {
        if(Ace){
           Ace.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
        }
        Ace = soundSystem.play(amaya.game.SoundConstants.ACE.id,0,amaya.game.SoundConstants.ACE.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete7);
    };
    var WinAnimComplete7 = function(e){
        var currPos = Ace.getPosition();
        if (currPos >= amaya.game.SoundConstants.ACE.end){
            if(amaya.game.SoundConstants.ACE.loop){
                Ace.setPosition(amaya.game.SoundConstants.ACE.start);
            }else{
                Ace.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
            }
        }
    };
    var kingSound = function () {
        if(King){
           King.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete7);
        }
        King = soundSystem.play(amaya.game.SoundConstants.KING.id,0,amaya.game.SoundConstants.KING.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete8);
    };
    var WinAnimComplete8 = function(e){
        var currPos = King.getPosition();
        if (currPos >= amaya.game.SoundConstants.KING.end){
            if(amaya.game.SoundConstants.KING.loop){
                King.setPosition(amaya.game.SoundConstants.KING.start);
            }else{
                King.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete8);
            }
        }
    };
    var queenSound = function () {
        if(Queen){
           Queen.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete9);
        }
        Queen = soundSystem.play(amaya.game.SoundConstants.QUEEN.id,0,amaya.game.SoundConstants.QUEEN.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete9);
    };
    var WinAnimComplete9 = function(e){
        var currPos = Queen.getPosition();
        if (currPos >= amaya.game.SoundConstants.QUEEN.end){
            if(amaya.game.SoundConstants.QUEEN.loop){
                Queen.setPosition(amaya.game.SoundConstants.QUEEN.start);
            }else{
                Queen.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete9);
            }
        }
    };
    var jackSound = function () {
        if(Jack){
            Jack.stop();
            createjs.Ticker.removeEventListener("tick",WinAnimComplete10);
        }
        Jack = soundSystem.play(amaya.game.SoundConstants.JACK.id,0,amaya.game.SoundConstants.JACK.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete10);
    };
    var WinAnimComplete10 = function(e){
        var currPos = Jack.getPosition();
        if (currPos >= amaya.game.SoundConstants.JACK.end){
            if(amaya.game.SoundConstants.JACK.loop){
                Jack.setPosition(amaya.game.SoundConstants.JACK.start);
            }else{
                Jack.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete10);
            }
        }
    };
    var bonusSound = function () {
        if(scatter){
           scatter.stop();
           createjs.Ticker.removeEventListener("tick",WinAnimComplete11);
        }
        scatter = soundSystem.play(amaya.game.SoundConstants.SCATTER.id,0,amaya.game.SoundConstants.SCATTER.start);
        createjs.Ticker.addEventListener("tick",WinAnimComplete11);
    };
    var WinAnimComplete11 = function(e){
        var currPos = scatter.getPosition();
        if (currPos >= amaya.game.SoundConstants.SCATTER.end){
            if(amaya.game.SoundConstants.SCATTER.loop){
                scatter.setPosition(amaya.game.SoundConstants.SCATTER.start);
            }else{
                scatter.stop();
                createjs.Ticker.removeEventListener("tick",WinAnimComplete11);
            }
        }
    };
    var symSound = [wildSound,logoSound,redWolfSound,blueWolfSound,fireBirdSound,pawsSound,aceSound,kingSound,queenSound,jackSound,bonusSound];
    instance.PlayWinAnim = function (sumId) {
        //alert("Call PlayWinAnim");
        symSound[sumId-1]();
    };
    return instance;
};
amaya.game.WonView = function ( container, model, soundManager, backgroundView ) {
    var instance = new amaya.api.EventDispatcher();
    var gameServices = new amaya.GameServices ();
    var preBigWinContainer = new createjs.Container();
    container.addChild(preBigWinContainer);
    var bigWinContainer = new createjs.Container();
    var bigWinCount = 0;
    var bigWinNumber =0;
    var bigWinTimer =0;
    var bigWinInterval;
    var bigWinDivider =0;
    var bigWinAnimTimer;
    var multiplierBigWin;
    var bigWinDifference=0;
    var diffByFour=0;
    var setBigWinTimeOut=0;
    var tickUpTimeOut=0;
    var rollingtime = 0;
    var gameServices = new amaya.GameServices ();
    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/MonarchSun_lang.json");
    var winMeter= new createjs.Bitmap(gameServices.assets.getAsset("device/images/Win_meter.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( winMeter, json.winMeter );
    container.addChild(winMeter);
    var winTextShadow = new createjs.Text(langFile.WON);
    amaya.sdk.utils.JSONUtils.fromJSON( winTextShadow, json.winTextShadow );
    container.addChild(winTextShadow);
	var winText = new createjs.Text(langFile.WON);
    amaya.sdk.utils.JSONUtils.fromJSON( winText, json.winText );
	container.addChild(winText);
   var messageShadow1 = new createjs.Text(langFile.Msg1);
   amaya.sdk.utils.JSONUtils.fromJSON( messageShadow1, json.messageShadow1 );
   container.addChild(messageShadow1);
   messageShadow1.visible=false;
   	var message1 = new createjs.Text(langFile.Msg1);
   amaya.sdk.utils.JSONUtils.fromJSON( message1, json.message1 );
   container.addChild(message1);
   message1.visible=false;
  /* var messageShadow2 = new createjs.Text(langFile.Msg2);
   amaya.sdk.utils.JSONUtils.fromJSON( messageShadow2, json.messageShadow2 );
   container.addChild(messageShadow2);
   messageShadow2.visible=false;
   var message2 = new createjs.Text(langFile.Msg2);
   amaya.sdk.utils.JSONUtils.fromJSON( message2, json.message2 );
   container.addChild(message2);
   message2.visible=false;
   var messageShadow3 = new createjs.Text(langFile.Msg3);
   amaya.sdk.utils.JSONUtils.fromJSON( messageShadow3, json.messageShadow3 );
   container.addChild(messageShadow3);
   messageShadow3.visible=false;
   var message3 = new createjs.Text(langFile.Msg3);
   amaya.sdk.utils.JSONUtils.fromJSON( message3, json.message3 );
   container.addChild(message3);
   message3.visible=false;*/
   var messageShadow4 = new createjs.Text(langFile.Msg4);
   amaya.sdk.utils.JSONUtils.fromJSON( messageShadow4, json.messageShadow4 );
   container.addChild(messageShadow4);
   messageShadow4.visible=false;
   var message4 = new createjs.Text(langFile.Msg4);
   amaya.sdk.utils.JSONUtils.fromJSON( message4, json.message4 );
   container.addChild(message4);
   message4.visible=false;
	var FreeMessageShadow = new createjs.Text(langFile.ALL_WINS);
    amaya.sdk.utils.JSONUtils.fromJSON( FreeMessageShadow, json.FreeMessageShadow );
    container.addChild(FreeMessageShadow);
    FreeMessageShadow.visible=false;
    var FreeMessage = new createjs.Text(langFile.ALL_WINS);
    amaya.sdk.utils.JSONUtils.fromJSON( FreeMessage, json.FreeMessage );
    container.addChild(FreeMessage);
    FreeMessage.visible=false;
	var msgBar = new createjs.Bitmap(gameServices.assets.getAsset("device/images/Msg_Bar.png"));
    amaya.sdk.utils.JSONUtils.fromJSON( msgBar, json.msgBar );
    container.addChild(msgBar);
    var iconWinMsg = new createjs.Text("");
    amaya.sdk.utils.JSONUtils.fromJSON( iconWinMsg, json.iconWinMsg );
    container.addChild(iconWinMsg);
    iconWinMsg.visible = false;
    var MessageIcon = new createjs.Container();
    container.addChild(MessageIcon);
    var tfWonShadow = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfWonShadow, json.tfWonShadow );
    container.addChild(tfWonShadow);
    var tfWon = new createjs.Text();
    amaya.sdk.utils.JSONUtils.fromJSON( tfWon, json.tfWon );
    container.addChild(tfWon);
   /* var Logo= new createjs.Bitmap(gameServices.assets.getAsset("device/images/small_symbols/Logo_Symbol.png"));
   // amaya.sdk.utils.JSONUtils.fromJSON( winMeter, json.winMeter );
    container.addChild(Logo);
    Logo.visible=false;*/
	/*Promo Messages display*/
	var msgFlag=0;
	var promoMsgTime=0;
    var freePromoMsgTime=0;
    instance.setPromoMsg = function(){
		//logo.visible=false;
//       if( gameServices.frameworkInfo.aspectRatio()=="1.33"){
                switch(msgFlag){
                    case 0:
                    message4.visible=false;
                    messageShadow4.visible=false;
                    backgroundView.showLogo();
                    msgFlag++;
                    break;
                    case 1:
                    backgroundView.hideLogo();
                	message1.visible=true;
                	messageShadow1.visible=true;
                	//message2.visible=true;
                   // messageShadow2.visible=true;
                   // message3.visible=true;
                  //  messageShadow3.visible=true;
                    msgFlag++;
                	break;
                	case 2:
                	message1.visible=false;
                    messageShadow1.visible=false;
                   // message2.visible=false;
                   // messageShadow2.visible=false;
                   // message3.visible=false;
                   // messageShadow3.visible=false;
                    message4.visible=true;
                    messageShadow4.visible=true;
                    msgFlag=0;
                	break;
                 }
		  promoMsgTime = setTimeout( function() {instance.setPromoMsg();}, 8000);
    }
	instance.clearPromoMsg = function(){
		message1.visible=false;
        messageShadow1.visible=false;
       // message2.visible=false;
       // messageShadow2.visible=false;
       // message3.visible=false;
       // messageShadow3.visible=false;
        message4.visible=false;
        messageShadow4.visible=false;
		clearTimeout(promoMsgTime);
		msgFlag=0;
		backgroundView.showLogo();
	}
     instance.setFreePromoMsg = function(){
          switch(msgFlag){
             case 0:
             message1.visible=false;
             messageShadow1.visible=false;
            // message2.visible=false;
            // messageShadow2.visible=false;
           //  message3.visible=false;
           //  messageShadow3.visible=false;
             backgroundView.showLogo();
             msgFlag++;
             break;
              case 1:
              backgroundView.hideLogo();
              message1.visible=true;
              messageShadow1.visible=true;
             // message2.visible=true;
            //  messageShadow2.visible=true;
            //  message3.visible=true;
            //  messageShadow3.visible=true;
              msgFlag=0;
              break;
          }
          freePromoMsgTime = setTimeout( function() {instance.setFreePromoMsg();}, 8000);
     }
    instance.clearFreePromoMsg = function(){
      message1.visible=false;
      messageShadow1.visible=false;
     /* message2.visible=false;
      messageShadow2.visible=false;
      message3.visible=false;
      messageShadow3.visible=false;*/
      backgroundView.showLogo();
      clearTimeout(freePromoMsgTime);
      msgFlag=0;
      }
    /**
     * Updates the won field to show the amount won by the player.
     * Also plays a sound effect to indicate a win.
     */
    var count = 0;
	var number =0;
	var timer =0;
	var interval=0;
	var divider =0;
    instance.showWin = function () {
        console.log("showWin");
        rollingtime = 1500;
		number = model.getCreditsWon();
		if( model.getCreditsWon()){
            soundManager.PlayWinTickUp();
			interval = setInterval(function(){
                if(count<=number){
                    divider = countCheck();
                    count = Math.floor( count + (number/divider));
                    if (count >= number) {
                         soundManager.StopWinTickUp();
                         clearInterval(interval);
                         tfWon.text = number;
                    }
                    else{
                          tfWon.text = count;
                    }
                }
			},30);
            timer = setTimeout(function(){
                console.log("timer"+count);
                soundManager.StopWinTickUp();
                tfWon.text = number;
                clearInterval(interval);
            }, rollingtime);
            if (model.getGameState() == amaya.game.GameConstants.SPIN_STATE) {
                //gameServices.setWonDisplay( model.getWon(), model.getNetWon() );
                //gameServices.setCreditsWonDisplay( model.getLinesWon(), model.getNetCreditsWon() );
            }
     		//instance.playSound();
		}
	};
    instance.clearWin = function () {
		soundManager.StopWinTickUp();
        rollingtime = 0
        tfWon.text = number;
		number=0;
		count= 0;
		clearInterval(interval);
		clearTimeout(timer);
		setTimeout ( function () {
            tfWon.text = 0;
        //    tfWonShadow.text = 0;
            if (model.getGameState() == amaya.game.GameConstants.SPIN_STATE) {
                gameServices.setWonDisplay( 0, 0 );
                gameServices.setCreditsWonDisplay( 0, 0 );
            }
		}, 500);
    };
	function countCheck(){
	    if(model.getCreditsWon() > 50){
	     //  count = count+4;
		   return 50;
		}
		else {
        	return model.getCreditsWon();
        }
	}
	function countBigWin(){
	    var tickUpAdder = Math.floor(model.getCreditsWon()/116800);
        	return tickUpAdder;
	}
    instance.showScatterWinOnFSScreenMeter = function () {
            tfWon.text =  model.getCreditsWon();
            tfWonShadow.text = model.getCreditsWon();
    };
    instance.showBaseWinOnTrigger = function () {
        gameServices.setWonDisplay( model.getWon(), model.getNetWon() );
        gameServices.setCreditsWonDisplay( model.getLinesWon(), model.getNetCreditsWon() );
    };
     /*symbol images for win message*/
   instance.getMsgIcon = function (j) {
       var msgIcon = [];
       var msgIconsData = json.msgIconsData;
       msgIconsData.images = [];
       for ( var i = 0; i < json.msgIconsData.image.length; i++ ) {
           msgIconsData.images[i] = gameServices.assets.getAsset(json.msgIconsData.image[i]);
       }
       msgIcon = msgIconsData;
       //console.log("msgIcon",msgIcon);
       return msgIcon.image[j];
   }
       /*function getmsgIconPos1(){
           msgIcon.x = json.msgIconsPos1.x;
           msgIcon.y = json.msgIconsPos1.y;
       }
       function getmsgIconPos2(){
           msgIcon.x = json.msgIconsPos2.x;
           msgIcon.y = json.msgIconsPos2.y;
       }*/
      var id,win;
      var ways;
      var creditsPerway;
     // var symbolName = ["",Logo];
      var IconMsgData ,iconMsg;
      /*function IconPos(waylength){
              if(waylength==1){
                iconMsg.x = json.msgIconsPos1.x;
                iconMsg.y = json.msgIconsPos1.y;
              }
              else {
                iconMsg.x = json.msgIconsPos2.x;
                iconMsg.y = json.msgIconsPos2.y;
              }
        }*/
      instance.showWinMsg = function( id,win,icon ){
        var lineId = id;
        var index = model.getWinningLineId().indexOf(id);
        IconMsgData =  instance.getMsgIcon((icon-1));
        iconMsg = new createjs.Bitmap(gameServices.assets.getAsset(IconMsgData));
        //console.log("IconMsgData",iconMsg);
        if (lineId === 0) {
                 iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"            "+langFile.wins_msg +" "+ model.getNumFreeSpinsWon() +" "+langFile.FG;
            if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                 iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"          "+langFile.wins_msg +" "+ model.getNumFreeSpinsWon() +" "+langFile.FG;
            }
                  iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                  iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                  MessageIcon.removeAllChildren();
                  MessageIcon.addChild(iconMsg);
        }
        else {
             if(model.getSymbolsWays().ways.length > 1){
                  if(model.getSymbolsWays().ways[index] === 1){
                      iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"            "+langFile.wins_msg+" "+win+" "+langFile.credits;
                    if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                      iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"          "+langFile.wins_msg+" "+win+" "+langFile.credits;
                    }
                    //IconPos(1);
                      iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                      iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                      MessageIcon.removeAllChildren();
                      MessageIcon.addChild(iconMsg);
                  }else{
                     iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"            "+langFile.pays+" "+win/model.getSymbolsWays().ways[index]+" x "+model.getSymbolsWays().ways[index]+" "+langFile.ways+" = "+win+" "+langFile.credits;
                    if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                     iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"          "+langFile.pays+" "+win/model.getSymbolsWays().ways[index]+" x "+model.getSymbolsWays().ways[index]+" "+langFile.ways+" = "+win+" "+langFile.credits;
                    }
                      //IconPos(2);
                      iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                      iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                      MessageIcon.removeAllChildren();
                      MessageIcon.addChild(iconMsg);
                  }
             }else{
                if(model.getSymbolsWays().ways[index] === 1){
                     iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"            "+langFile.wins_msg+" "+win+" "+langFile.credits;
                    if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                     iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"          "+langFile.wins_msg+" "+win+" "+langFile.credits;
                    }
                       //IconPos(1);
                      iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                      iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                       MessageIcon.removeAllChildren();
                       MessageIcon.addChild(iconMsg);
                }else{
                      iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"            "+langFile.pays+" "+win/model.getSymbolsWays().ways[index]+" x "+model.getSymbolsWays().ways[index]+" "+langFile.ways+" = "+win+" "+langFile.credits;
                    if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                      iconWinMsg.text = model.getSymbolsWays().symbolNumbers[index]+"          "+langFile.pays+" "+win/model.getSymbolsWays().ways[index]+" x "+model.getSymbolsWays().ways[index]+" "+langFile.ways+" = "+win+" "+langFile.credits;
                    }
                    //IconPos(2);
                      iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                      iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                      MessageIcon.removeAllChildren();
                      MessageIcon.addChild(iconMsg);
                }
             }
        }
      iconWinMsg.visible = true;
    };
    instance.showAllCreditsWon = function(win){
             iconWinMsg.text =  langFile.totalWin +" "+win+" "+langFile.credits;
             iconWinMsg.visible = true;
    }
    instance.showWinMsgInAutoPlay = function( length, win, icon ){
        IconMsgData =  instance.getMsgIcon((icon-1));
        iconMsg = new createjs.Bitmap(gameServices.assets.getAsset(IconMsgData));
         var IdIndex = model.getWinningLineId().indexOf(model.getWinningLines()[0].id);
       if (length === 1) {
           if(model.getSymbolsWays().ways[IdIndex] === 1){
                iconWinMsg.text = model.getSymbolsWays().symbolNumbers[IdIndex]+"            "+langFile.wins_msg+" "+win+" "+langFile.credits;
               if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                iconWinMsg.text = model.getSymbolsWays().symbolNumbers[IdIndex]+"          "+langFile.wins_msg+" "+win+" "+langFile.credits;
               }
                  //IconPos(1);
                 iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                 iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                  MessageIcon.removeAllChildren();
                  MessageIcon.addChild(iconMsg);
           }else{
                 iconWinMsg.text = model.getSymbolsWays().symbolNumbers[IdIndex]+"            "+langFile.pays+" "+win/model.getSymbolsWays().ways[IdIndex]+" x "+model.getSymbolsWays().ways[IdIndex]+" "+langFile.ways+" = "+win+" "+langFile.credits;
               if( gameServices.frameworkInfo.aspectRatio()=="1.33" ){
                 iconWinMsg.text = model.getSymbolsWays().symbolNumbers[IdIndex]+"          "+langFile.pays+" "+win/model.getSymbolsWays().ways[IdIndex]+" x "+model.getSymbolsWays().ways[IdIndex]+" "+langFile.ways+" = "+win+" "+langFile.credits;
               }
               //IconPos(2);
                 iconMsg.x = (json.iconWinMsg.x - iconWinMsg.getBounds().width/2) + (iconWinMsg.getBounds().height);
                 iconMsg.y = json.iconWinMsg.y - ((iconMsg.getBounds().height - iconWinMsg.getBounds().height)/2);
                 MessageIcon.removeAllChildren();
                 MessageIcon.addChild(iconMsg);
           }
       }
       else {
            iconWinMsg.text = langFile.totalWin +" "+win+" "+langFile.credits;
        }
            iconWinMsg.visible = true;
    };
    instance.showGoodLuckMsg = function(){
           iconWinMsg.text = langFile.GoodLuck;
           iconWinMsg.visible = true;
    }
    instance.clearWinMsg = function( ){
      iconWinMsg.visible = false;
      MessageIcon.removeAllChildren();
    }  ;
    instance.clearWin();
    /*instance.showPreBigWin = function(){
        var x = Math.floor((Math.random() * 2) +1);
        console.log("showPreBigWin x",x);
        if(x==1){
            soundManager.PreBigWinRedSound();
            var RedBigWinData = json.redWinData;
            RedBigWinData.images = [gameServices.assets.getAsset(json.redWinData.image)];
            var redWinAnimSS = new createjs.SpriteSheet(RedBigWinData);
            var redWinAnim = new createjs.Sprite(redWinAnimSS);
            redWinAnim.x = RedBigWinData.pos.x;
            redWinAnim.y = RedBigWinData.pos.y;
            redWinAnim.scaleX = RedBigWinData.scale.x;
            redWinAnim.scaleY = RedBigWinData.scale.y;
            redWinAnim.gotoAndStop(0);
            redWinAnim.gotoAndPlay("redAnim");
            preBigWinContainer.addChild(redWinAnim);
            redWinAnim.addEventListener("animationend",function () {
                preBigWinContainer.removeChild(redWinAnim);
                instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
            });
        }
        else{
            soundManager.PreBigWinBlueSound();
            var blueBigWinData = json.blueWinData;
            blueBigWinData.images = [gameServices.assets.getAsset(json.blueWinData.image)];
            var blueWinAnimSS = new createjs.SpriteSheet(blueBigWinData);
            var blueWinAnim = new createjs.Sprite(blueWinAnimSS);
            blueWinAnim.x = blueBigWinData.pos.x;
            blueWinAnim.y = blueBigWinData.pos.y;
            blueWinAnim.scaleX = blueBigWinData.scale.x;
            blueWinAnim.scaleY = blueBigWinData.scale.y;
            blueWinAnim.gotoAndStop(0);
            blueWinAnim.gotoAndPlay("blueAnim");
            preBigWinContainer.addChild(blueWinAnim);
            blueWinAnim.addEventListener("animationend",function () {
                preBigWinContainer.removeChild(blueWinAnim);
                instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
            });
        }
    }*/
        instance.showBigwinMsg = function(){
           // instance.showPreBigWin();
            //calculating multiplier of win
            multiplierBigWin = (model.getCreditsWon())/(model.getBet());
            //Calculating how much time multiplier greater then 20x
            bigWinDifference = multiplierBigWin-20;
            //Dividing by 4 to calculate extra time after 8 second
            diffByFour = Math.floor(bigWinDifference/4);
            //Set big win animation time
            setBigWinTimeOut = ((diffByFour*1000) + 8500);
        //set tikup time
        //tickUpTimeOut = ((setBigWinTimeOut - 500)/(model.getCreditsWon()));
        console.log("tickUpTimeOut"+tickUpTimeOut);
        //bigWinDivider = model.getCreditsWon() / (createjs.Ticker.getMeasuredFPS()*(setBigWinTimeOut/1000));
        if (setBigWinTimeOut > 60000) {
            setBigWinTimeOut = 60000;
            //tickUpTimeOut = ((setBigWinTimeOut - 500)/116800);
            //bigWinDivider = model.getCreditsWon() / (createjs.Ticker.getMeasuredFPS()*(setBigWinTimeOut/1000));
        }
        soundManager.PlayBigWin();
        var rect = new createjs.Shape();
        rect.graphics.beginFill("rgba(0,0,0,0.5)").drawRect(0, 0, 1366, 768);
        bigWinContainer.addChild(rect);
            var bigWinBannerShadow=new createjs.Text(langFile.BIG_WIN);
            amaya.sdk.utils.JSONUtils.fromJSON( bigWinBannerShadow, json.bigWinBannerShadow );
            bigWinContainer.addChild(bigWinBannerShadow);
            var bigWinBanner=new createjs.Text(langFile.BIG_WIN);
            amaya.sdk.utils.JSONUtils.fromJSON( bigWinBanner, json.bigWinBanner );
            bigWinContainer.addChild(bigWinBanner);
            var animData  = json.bigWinAnimData;
            var animData1 = json.bigWinAnimData1;
            var animData2 = json.bigWinAnimData2;
            var animData3 = json.bigWinAnimData3;
            animData.images  = [gameServices.assets.getAsset(json.bigWinAnimData.image)];
            animData1.images = [gameServices.assets.getAsset(json.bigWinAnimData1.image)];
            animData2.images = [gameServices.assets.getAsset(json.bigWinAnimData2.image)];
            animData3.images = [gameServices.assets.getAsset(json.bigWinAnimData3.image)];
            var bigWinMeterBox= new createjs.Bitmap(gameServices.assets.getAsset("device/images/Win_meter.png"));
            amaya.sdk.utils.JSONUtils.fromJSON( bigWinMeterBox, json.bigWinMeterBox );
            bigWinContainer.addChild(bigWinMeterBox);
            //big win meter
            var bigWinMeter=new createjs.Text();
            amaya.sdk.utils.JSONUtils.fromJSON( bigWinMeter, json.bigWinMeter );
            bigWinContainer.addChild(bigWinMeter);
                    bigWinNumber = model.getCreditsWon();
                         //time interval for tick's
        bigWinInterval = createjs.Tween.get(bigWinMeter)
            .to({bigWinCount:bigWinNumber},setBigWinTimeOut)
            .call( function (evt) {
                evt.removeAllEventListeners();
                bigWinMeter.text = Number(bigWinNumber).toFixed(0);
                bigWinAnimTimer = setTimeout( function () {
                                       createjs.Ticker.addEventListener("tick", tick);
                                        var direction = 0.025;
                                        function tick(event) {
                                           console.log("setBigWinTimeOut==   "+ setBigWinTimeOut);
                                           bigWinContainer.alpha -= direction;
                                           if (bigWinContainer.alpha <=  0) {
                                              createjs.Ticker.removeEventListener("tick", tick);
                                              bigWinTimer = setTimeout( function () {
                                                                instance.clearBigwinMsg();
                                                                bigWinMeter.text = 0;
                                                                bigWinContainer.alpha = 1;
                                                                bigWinContainer.removeChild(bigWinMeter);
                                                                bigWinContainer.removeChild(bigWinMeterBox);
                                                                }, 500);
                                                             }
                                        }
                                     }, 1000);
            });
        bigWinInterval.on("change", function(evt){
            bigWinDivider = model.getCreditsWon() / (createjs.Ticker.getMeasuredFPS()*(setBigWinTimeOut/1000));
            if(bigWinCount < bigWinNumber){
                bigWinCount = bigWinCount + bigWinDivider;
            }else{
                bigWinCount = bigWinNumber;
            }
            bigWinMeter.text = Number(bigWinCount).toFixed(0);
        });
                var spriteSheet = new createjs.SpriteSheet(animData);
                var bigWinSprite = new createjs.Sprite(spriteSheet);
                bigWinSprite.x = animData.pos.x;
                bigWinSprite.y = animData.pos.y;
                bigWinSprite.scaleX = animData.scale.x;
                bigWinSprite.scaleY = animData.scale.y;
                bigWinSprite.gotoAndStop(0);
                bigWinSprite.gotoAndPlay("lightBigWin");
                bigWinContainer.addChild(bigWinSprite);
                var spriteSheet1 = new createjs.SpriteSheet(animData1);
                var bigWinSprite1 = new createjs.Sprite(spriteSheet1);
                bigWinSprite1.x = animData1.pos.x;
                bigWinSprite1.y = animData1.pos.y;
                bigWinSprite1.scaleX = animData1.scale.x;
                bigWinSprite1.scaleY = animData1.scale.y;
                bigWinSprite1.gotoAndStop(0);
                bigWinSprite.addEventListener("animationend",function () {
                    bigWinContainer.removeChild(bigWinSprite);
                    bigWinSprite1.gotoAndPlay("lightBigWin1");
                    bigWinContainer.addChild(bigWinSprite1);
                });
                var spriteSheet2 = new createjs.SpriteSheet(animData2);
                var bigWinSprite2 = new createjs.Sprite(spriteSheet2);
                bigWinSprite2.x = animData2.pos.x;
                bigWinSprite2.regX = bigWinSprite2.getBounds().width;
                bigWinSprite2.y = animData2.pos.y;
                bigWinSprite2.scaleX = -animData2.scale.x;
                bigWinSprite2.scaleY = animData2.scale.y;
                bigWinSprite2.gotoAndStop(0);
                bigWinSprite2.gotoAndPlay("lightBigWin2");
                //console.log("In seconde part");
                bigWinContainer.addChild(bigWinSprite2);
                var spriteSheet3 = new createjs.SpriteSheet(animData3);
                var bigWinSprite3 = new createjs.Sprite(spriteSheet3);
                bigWinSprite3.x = animData3.pos.x;
                bigWinSprite3.regX = bigWinSprite3.getBounds().width;
                bigWinSprite3.y = animData3.pos.y;
                bigWinSprite3.scaleX = -animData3.scale.x;
                bigWinSprite3.scaleY = animData3.scale.y;
                bigWinSprite2.addEventListener("animationend",function () {
                    bigWinContainer.removeChild(bigWinSprite2);
                    bigWinSprite3.gotoAndPlay("lightBigWin3");
                    bigWinContainer.addChild(bigWinSprite3);
                });
        //time for  fade away the big win animation
        bigWinContainer.alpha = 1;
        container.addChild(bigWinContainer);
	};
    //Clear big win animation
	instance.clearBigwinMsg = function(){
	     soundManager.StopBigWin();
	     clearTimeout(bigWinAnimTimer);
	     clearTimeout(bigWinTimer);
	     //clearInterval(bigWinInterval);
         bigWinInterval.setPosition (setBigWinTimeOut, 0);
	     bigWinInterval.off("change");
	     bigWinInterval.removeAllEventListeners();
	     bigWinContainer.alpha = 1;
	     bigWinContainer.removeAllChildren();
	     bigWinCount = 0;
         bigWinNumber = 0;
         bigWinTimer = 0;
         bigWinInterval = 0;
         bigWinDivider = 0;
         bigWinAnimTimer = 0;
         multiplierBigWin = 0;
         bigWinDifference = 0;
         diffByFour = 0;
         setBigWinTimeOut = 0;
         tickUpTimeOut = 0;
        container.removeChild(bigWinContainer);
        instance.dispatchEvent( new amaya.game.events.ClickToContinueEvent());
	};
    return instance;
};
amaya.game.events.ClickToContinueEvent = function () {
    return new amaya.api.events.Event( amaya.game.events.ClickToContinueEvent.Click,
            {} );
};
amaya.game.events.ClickToContinueEvent.Click = "click";
amaya.game.messaging.FreeSpinRequest = function ( total, used ) {
    var instance = new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.FREE_SPIN_REQUEST );
    instance.append('<freeSpins total="'+ total +'" used="'+ used +'"/>');
    return instance;
};
/**
 *
 * @param {xml} responseData
 */
amaya.game.messaging.FreeSpinResponse = function ( responseData ) {
    var instance = {};
    /**
     * @type {Array.<Array>}
     */
    var reels;
    /**
     * @type {number}
     */
    var creditsWon;
    /**
     * @type {Array.<object>}
     */
    var lines;
    /**
     * @type {number}
     */
    var scatterWin = 0;
    /**
     * @type {Array.<object>}
     */
    var positions;
    /**
     * @type {number}
     */
    var linesWon;
    /**
     * @type {number}
     */
    var starsTotal;
    /**
     * @type {number}
     */
    var freeSpinsTotal;
    /**
     * @type {number}
     */
    var freeSpinsUsed;
    /**
     * @type {number}
     */
    var freeSpinsWon;
    var scatterLength;
    reels = [];
    var streamPos = [];
    var stops;
    var aCount=1;
    var pos=0 ;
    var tempArray = [];
    var numOfWays = [];
    var numOfSym=[];
    var wayPos=0;
    var winningLineId = [];
    $(responseData).find("msgdata reels reel").each( function ( index, element ) {
        stops = $(element).text().split(",");
        if (stops.length === 3) {
            stops[3] = 10;
        }
        stops[0] = Number(stops[0]);
        stops[1] = Number(stops[1]);
        stops[2] = Number(stops[2]);
        stops[3] = Number(stops[3]);
        reels.push(stops);
        console.log("stops",stops);
        if(aCount == 2|| aCount ==3 || aCount ==4){
            streamStack();
        }
        aCount++;
    } );
    function streamStack(){
        var redCount = 0;
        var blueCount =0;
        var wildCount=0;
        var count = 1;
            for(i=0;i<stops.length;i++){
                if(stops[i]==3) {  redCount++;}
                if(stops[i]==4) { blueCount++; }
                if(stops[i]==1) { wildCount++; }
            }
            if(redCount == stops.length){
               streamPos[pos]=3;
            }
            else if(blueCount == stops.length){
                streamPos[pos]=4;
            }
            else if(wildCount == stops.length){
                streamPos[pos]=1;
            }
            pos++;
            if (pos==3){ pos = 0;}
    }
    creditsWon = parseInt($(responseData).find("msgdata lines").attr("won"));
    linesWon = parseInt($(responseData).find("msgdata creditResults").attr("won"));
    lines = [];
    allPositions =[];
    $(responseData).find("msgdata lines line").each( function ( index, element ) {
        var line = $(element);
        var a = String(line.attr("positions")).split(";");
        positions = [];
        for (var i = 0; i < a.length; i++) {
            if (a[i] !== "") {
                positions[i] = a[i].split(",");
            }
        }
        allPositions = allPositions.concat(positions);
		if (lines.length) {
            if ((Number(line.attr("id")) != lines[lines.length-1].id) && (Number(line.attr("id")) != lines[0].id)) {
                var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
                lines.push(lineData);
                winningLineId.push(Number(line.attr("id")));
                if (lineData.id === 0) {
                    scatterWin = lineData.won;
                    scatterLength = a.length;
                }
            }
		}
		else {
            var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
            lines.push(lineData);
            winningLineId.push(Number(line.attr("id")));
            if (lineData.id === 0) {
                scatterWin = lineData.won;
                scatterLength = a.length;
            }
		}
		if(positions.length){
            var i,
                j,
                k=0,
                l=1,
                n=0,
                m=0;
                tempArray =[];
                tempArray.push(l);
                for(j=0;j< positions.length-1;j++){
                    if(positions[j][k]==positions[j+1][k]){
                        l++;
                        tempArray[n] = l;
                    }else{
                        l=1;
                        tempArray.push(l);
                        n++;
                    }
                }
            numOfWays[wayPos] = tempArray[m];
            for(m=1;m<tempArray.length;m++){
                numOfWays[wayPos] = numOfWays[wayPos] * tempArray[m];
            }
            numOfSym[wayPos] = tempArray.length;
            wayPos++;
            /*console.log("positions",positions);
            console.log("tempArray",tempArray);
            console.log("NumOfSym",numOfSym);
            console.log("numOfWays",numOfWays);*/
        }
    } );
    freeSpinsTotal = parseInt($(responseData).find("msgdata freeSpins").attr("total"));
    freeSpinsUsed = parseInt($(responseData).find("msgdata freeSpins").attr("used"));
    freeSpinsWon = parseInt($(responseData).find("msgdata freeSpins").attr("won"));
    instance.getReelStops = function () {
        return reels.slice();
    };
    instance.getCreditsWon = function () {
        return creditsWon;
    };
    instance.getLinesWon = function () {
        return linesWon;
    };
    instance.getWinningLines = function () {
        return lines;
    };
    instance.getFreeSpinsTotal = function () {
        return freeSpinsTotal;
    };
    instance.getFreeSpinsUsed = function () {
        return freeSpinsUsed;
    };
    instance.getFreeSpinsWon = function () {
        return freeSpinsWon;
    };
    instance.getScatterLength = function () {
            return scatterLength;
    };
    instance.getScatterWin = function () {
        return scatterWin;
    };
    instance.getAllPositions = function(){
            console.log("getAllPositions ",allPositions);
            return {
                win:0,
                length:allPositions.length,
                positions:allPositions
            }
    };
    instance.getStreamingStack = function(){
            return streamPos;
    };
    instance.getSymbolsAndWays = function(){
            return {
                ways:numOfWays,
                symbolNumbers:numOfSym
            }
    };
    instance.getWinningLineId= function(){
            return winningLineId;
    };
    return instance;
};
amaya.game.messaging.InitRequest = function () {
    return new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.INIT_REQUEST );
};
/**
 *
 * @param {xml} responseData
 */
amaya.game.messaging.InitResponse = function ( responseData ) {
    var instance = {};
    /**
     * @type {number}
     */
    var mainReels = [];
    /**
     * @type {number}
     */
    var freeReels = [];
    /**
     * @type {Array.<Array>}
     */
    var reels;
      //console.log("init response");
  //a  console.log(responseData);
    $(responseData).find("msgdata mainReelsConfig reel").each( function ( index, element ) {
        var reel = $(element).text().split(",");
        for (var i = 0; i < reel.length; i++) {
            reel[i] = Number(reel[i]);
        }
        mainReels.push(reel);
    } );
    console.log(mainReels);
    $(responseData).find("msgdata freeSpinReelsConfig reel").each( function ( index, element ) {
        var reel = $(element).text().split(",");
        for (var i = 0; i < reel.length; i++) {
            reel[i] = Number(reel[i]);
        }
        freeReels.push(reel);
    } );
    console.log(freeReels);
    reels = [];
    $(responseData).find("msgdata initStops").each( function ( index, element ) {
        var stops = $(element).text().split(",");
        for (var i = 0; i < stops.length; i++) {
            stops[i] = Number(stops[i]);
        }
        for (var i = 0; i < 5; i++) {
            var reel = [];
            for (var j = 0; j < 4; j++) {
                if (mainReels[i].length > (stops[i]+j)) {
                    reel.push(mainReels[i][stops[i]+j]);
                }
                else {
                    reel.push(mainReels[i][(stops[i]+j) - mainReels[i].length]);
                }
            }
            reels.push(reel);
        }
    } );
    instance.getReelStops = function () {
        console.log("reels",reels);
        return reels.slice();
    };
     /**
     * @return {number}
     */
    instance.getReels = function () {
        return mainReels;
    };
    /**
     * @return {number}
     */
    instance.getFreeReels = function () {
        return freeReels;
    };
    return instance;
};
amaya.game.messaging.MessagingConstants = {
    INIT_REQUEST: "InitReq",
    SPIN_REQUEST: "SpinReq",
    FREE_SPIN_REQUEST: "FreeReq"
};
/**
 *
 * @param {xml} responseData
 */
amaya.game.messaging.ResumeGameResponse = function ( responseData ) {
    var instance = {};
    /**
     * @type {Array.<Array>}
     */
    var reels;
    /**
     * @type {Array.<Array>}
     */
    var baseReels;
    /**
     * @type {number}
     */
    var creditsWon;
    /**
     * @type {Array.<object>}
     */
    var lines;
    /**
     * @type {Array.<object>}
     */
    var positions;
    /**
     * @type {number}
     */
    var linesWon;
    /**
     * @type {number}
     */
    var starsTotal;
    /**
     * @type {number}
     */
    var freeSpinsTotal;
    /**
     * @type {number}
     */
    var freeSpinsUsed;
    /**
     * @type {number}
     */
    var scatterWin = 0;
    /**
     * @type {number}
     */
    var numScatterSymbols;
    /**
     * @type {number}
     */
    var creditValue;
    /**
     * @type {number}
     */
    var creditsPerLine;
     var scatterLength;
    var isFreeSpinTriggered;
    /**
         * @type {number}
         */
        var mainReels = [];
        /**
         * @type {number}
         */
        var freeReels = [];
        //console.log("init response");
      //a  console.log(responseData);
        $(responseData).find("msgdata mainReelsConfig reel").each( function ( index, element ) {
            var reel = $(element).text().split(",");
            for (var i = 0; i < reel.length; i++) {
                reel[i] = Number(reel[i]);
            }
            mainReels.push(reel);
        } );
        console.log(mainReels);
        $(responseData).find("msgdata freeSpinReelsConfig reel").each( function ( index, element ) {
            var reel = $(element).text().split(",");
            for (var i = 0; i < reel.length; i++) {
                reel[i] = Number(reel[i]);
            }
            freeReels.push(reel);
        } );
        console.log(freeReels);
        /**
         * @return {number}
         */
        instance.getReels = function () {
            return mainReels;
        };
        /**
         * @return {number}
         */
        instance.getFreeReels = function () {
            return freeReels;
        };
    reels = [];
    var streamPos = [];
    var stops;
    var aCount=1;
    var pos=0 ;
    var tempArray = [];
    var numOfWays = [];
    var numOfSym = [];
    var wayPos=0;
    var tempArray_scatter = [];
    var numOfWays_scatter;
    var numOfSym_scatter = [];
    var wayPos_scatter=0;
    var winningLineId = [];
    $(responseData).find("msgdata reels reel").each( function ( index, element ) {
        stops = $(element).text().split(",");
        if (stops.length === 3) {
            stops[3] = 10;
        }
        stops[0] = Number(stops[0]);
        stops[1] = Number(stops[1]);
        stops[2] = Number(stops[2]);
        stops[3] = Number(stops[3]);
        reels.push(stops);
        if(aCount == 2|| aCount ==3 || aCount ==4){
            streamStack();
        }
        aCount++;
    } );
    baseReels = [];
   /* $(responseData).find("msgdata basespinreels reel").each( function ( index, element ) {
        var stops = $(element).text().split(",");
        if (stops.length === 3) {
            stops[3] = 1;
        }
        stops[0] = Number(stops[0]);
        stops[1] = Number(stops[1]);
        stops[2] = Number(stops[2]);
        stops[3] = Number(stops[3]);
        baseReels.push(stops);
    } );*/
     $(responseData).find("msgdata baseGameStops").each( function ( index, element ) {
            var stops = $(element).text().split(",");
            for (var i = 0; i < stops.length; i++) {
                stops[i] = Number(stops[i]);
            }
            for (var i = 0; i < 5; i++) {
                var reel = [];
                for (var j = 0; j < 4; j++) {
                    if (mainReels[i].length > (stops[i]+j)) {
                        reel.push(mainReels[i][stops[i]+j]);
                    }
                    else {
                        reel.push(mainReels[i][(stops[i]+j) - mainReels[i].length]);
                    }
                }
                baseReels.push(reel);
            }
        } );
   function streamStack(){
        var redCount = 0;
        var blueCount =0;
        var wildCount=0;
        var count = 1;
            for(i=0;i<stops.length;i++){
                if(stops[i]==3) {  redCount++;}
                if(stops[i]==4) { blueCount++; }
                if(stops[i]==1) { wildCount++; }
            }
            if(redCount == stops.length){
               streamPos[pos]=3;
            }
            else if(blueCount == stops.length){
                streamPos[pos]=4;
            }
            else if(wildCount == stops.length){
                streamPos[pos]=1;
            }
            pos++;
            if (pos==3){ pos = 0;}
    }
    creditsWon = parseInt($(responseData).find("msgdata lines").attr("won"));
    linesWon = parseInt($(responseData).find("msgdata creditResults").attr("won"));
    lines = [];
    isFreeSpinTriggered = false;
    var allPositions =[];
    var  scatterAllPos = [];
    $(responseData).find("msgdata lines line").each( function ( index, element ) {
        var line = $(element);
        var a = String(line.attr("positions")).split(";");
        positions = [];
        for (var i = 0; i < a.length; i++) {
            if (a[i] !== "") {
                positions[i] = a[i].split(",");
            }
        }
        allPositions = allPositions.concat(positions);
		if (lines.length) {
             if ((Number(line.attr("id")) != lines[lines.length-1].id) && (Number(line.attr("id")) != lines[0].id)) {
                var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
                lines.push(lineData);
                winningLineId.push(Number(line.attr("id")));
                if (lineData.id === 0) {
                    isFreeSpinTriggered = true;
                    numScatterSymbols = lineData.positions.length;
                    scatterAllPos =lineData.positions;
                    getScatterWays();
                    scatterWin = lineData.won;
                    scatterLength = a.length;
                }
            }
		}
		else {
            var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
            lines.push(lineData);
            winningLineId.push(Number(line.attr("id")));
            if (lineData.id === 0) {
                isFreeSpinTriggered = true;
                numScatterSymbols = lineData.positions.length;
                scatterAllPos = lineData.positions;
                getScatterWays();
                scatterWin = lineData.won;
                scatterLength = a.length;
            }
		}
		if(positions.length){
            var i, j,k=0,l=1,n=0,m=0;
                tempArray =[];
                tempArray.push(l);
                for(j=0;j< positions.length-1;j++){
                    if(positions[j][k]==positions[j+1][k]){
                        l++;
                        tempArray[n] = l;
                    }else{
                        l=1;
                        tempArray.push(l);
                        n++;
                    }
                }
            numOfWays[wayPos] = tempArray[m];
            for(m=1;m<tempArray.length;m++){
                numOfWays[wayPos] = numOfWays[wayPos] * tempArray[m];
            }
            numOfSym[wayPos] = tempArray.length;
            wayPos++;
            /*console.log("positions",positions);
            console.log("tempArray",tempArray);
            console.log("NumOfSym",numOfSym);
            console.log("numOfWays",numOfWays);*/
        }
        function getScatterWays(){
           var a,b=0,c=1,d=0,e=0;
           tempArray_scatter =[];
           tempArray_scatter.push(c);
           for(a=0;a< scatterAllPos.length-1;a++){
               if(scatterAllPos[a][b]==scatterAllPos[a+1][b]){
                   c++;
                   tempArray_scatter[d] = c;
               }else{
                   c=1;
                   tempArray_scatter.push(c);
                   d++;
               }
           }
           numOfWays_scatter = tempArray_scatter[e];
           for(e=1;e<tempArray_scatter.length;e++){
               numOfWays_scatter = numOfWays_scatter * tempArray_scatter[e];
           }
           numOfSym_scatter = tempArray_scatter.length;
        }
    } );
    freeSpinsTotal = parseInt($(responseData).find("msgdata freeSpins").attr("total"));
    freeSpinsUsed = parseInt($(responseData).find("msgdata freeSpins").attr("used"));
    freeSpinsWon = parseInt($(responseData).find("msgdata freeSpins").attr("won"));
    creditValue = Number($(responseData).find("msgdata initialWager").attr("coinValue"));
    creditsPerLine = Number($(responseData).find("msgdata initialWager").attr("creditWager"));
    instance.getReelStops = function () {
        return reels.slice();
    };
    instance.getBaseReelStops = function () {
        return baseReels.slice();
    };
    instance.getCreditsWon = function () {
        return creditsWon;
    };
    instance.getLinesWon = function () {
        return linesWon;
    };
    instance.getWinningLines = function () {
        return lines;
    };
    instance.getFreeSpinsTotal = function () {
        return freeSpinsTotal;
    };
    instance.getFreeSpinsUsed = function () {
        return freeSpinsUsed;
    };
    instance.getCreditValue = function () {
        return creditValue;
    };
instance.getStreamingStack = function(){
            return streamPos;
    };
    instance.getCreditsPerLine = function () {
        return creditsPerLine;
    };
    instance.hasFreeSpinsTriggered = function () {
        return isFreeSpinTriggered;
    }
    instance.getNumScatterSymbols = function () {
        return numScatterSymbols;
    }
    instance.getFreeSpinsWon = function () {
        return freeSpinsWon;
    };
    instance.getScatterLength = function () {
            return scatterLength;
    };
    instance.getScatterWin = function () {
        return scatterWin;
    };
    instance.getSymbolsAndWays = function(){
            return {
                ways:numOfWays,
                symbolNumbers:numOfSym
            }
    };
    instance.getWinningLineId= function(){
            return winningLineId;
    };
    instance.getAllPositions = function(){
            //console.log("getStreamingStack ",streamPos);
            return {
                win:0,
                length:allPositions.length,
                positions:allPositions
            }
    };
     instance.getScatterWays = function(){
            return {
           // console.log("getScatterWays ",numOfWays_scatter);
               ways:numOfWays_scatter,
               symbolNumbers:numOfSym_scatter,
               freespins:8*numOfWays_scatter
            }
        };
    return instance;
};
amaya.game.messaging.SpinRequest = function ( creditValue, creditsPerLine ) {
    var instance = new amaya.sdk.singleplayer.messaging.V2Req( amaya.game.messaging.MessagingConstants.SPIN_REQUEST );
    instance.append('<coinValue>' + creditValue + '</coinValue>');
    instance.append('<creditWager>' + creditsPerLine + '</creditWager>');
    return instance;
};
/**
 *
 * @param {xml} responseData
 */
amaya.game.messaging.SpinResponse = function ( responseData ) {
    var instance = {};
    /**
     * @type {Array.<Array>}
     */
    var reels;
    /**
     * @type {number}
     */
    var creditsWon;
    /**
     * @type {number}
     */
    var scatterWin = 0;
    /**
     * @type {Array.<object>}
     */
    var lines;
    /**
     * @type {Array.<object>}
     */
    var positions;
    /**
     * @type {number}
     */
    var linesWon;
    /**
     * @type {number}
     */
    var starsTotal;
    /**
     * @type {number}
     */
    var freeSpinsTotal;
    /**
     * @type {number}
     */
  //  var freeSpinsWon;
    var freeSpinsUsed;
    var scatterLength;
    var reels = [];
    var rePatternPos = [];
    var streamPos = [];
    var replacePos;
    var stops;
    var aCount=1;
    var pos=0 ;
    var tempArray = [];
    var numOfWays = [];
    var numOfSym = [];
    var wayPos=0;
    var winningLineId = [];
    $(responseData).find("msgdata reels reel").each( function ( index, element ) {
        stops = $(element).text().split(",");
        if (stops.length === 3) {
            stops[3] = 10;
        }
        stops[0] = Number(stops[0]);
        stops[1] = Number(stops[1]);
        stops[2] = Number(stops[2]);
        stops[3] = Number(stops[3]);
        reels.push(stops);
        console.log("stops",stops);
        if(aCount == 2|| aCount ==3 || aCount ==4){
            streamStack();
        }
        aCount++;
    } );
   /* rePatternCheck();
    function rePatternCheck(){
        if($(responseData).find("msgdata reels rePattern").text()){
                replacePos = $(responseData).find("msgdata reels rePattern").text().split(",");
                 console.log("replacePos ",replacePos);
                 for(var i=0; i < replacePos.length; i++){
                     rePatternPos.push(parseInt(replacePos[i]));
                    console.log("rePatternPos ",rePatternPos);
                 }
                 var reelId=1;
                 var rePatternId=0;
                for(reelId=1;reelId<4;reelId++){
                    for(i=0;i<stops[reelId].length;i++){
                        if(stops[reelId][i]==12){
                            stops[reelId][i] = rePatternPos[rePatternId];
                            console.log("stops[reelId][i]",stops[reelId][i]);
                            console.log("rePatternPos[rePatternId]",rePatternPos[rePatternId]);
                            streamPos[i]= rePatternPos[rePatternId];
                            console.log("streamPos ",streamPos[i]);
                        }
                    }
                    rePatternId++;
                }
        }
    }*/
    function streamStack(){
        var redCount = 0;
        var blueCount =0;
        var wildCount=0;
        var count = 1;
            for(i=0;i<stops.length;i++){
                if(stops[i]==3) {  redCount++;}
                if(stops[i]==4) { blueCount++; }
                if(stops[i]==1) { wildCount++; }
            }
            if(redCount == stops.length){
               streamPos[pos]=3;
            }
            else if(blueCount == stops.length){
                streamPos[pos]=4;
            }
            else if(wildCount == stops.length){
                streamPos[pos]=1;
            }
            pos++;
            if (pos==3){ pos = 0;}
    }
    creditsWon = parseInt($(responseData).find("msgdata lines").attr("won"));
    linesWon = parseInt($(responseData).find("msgdata creditResults").attr("won"));
    lines = [];
    allPositions =[];
    $(responseData).find("msgdata lines line").each( function ( index, element ) {
        var line = $(element);
        var a = String(line.attr("positions")).split(";");
        positions = [];
        for (var i = 0; i < a.length; i++) {
            if (a[i] !== "") {
                positions[i] = a[i].split(",");
            }
        }
        allPositions = allPositions.concat(positions);
       // console.log("allPositions", allPositions);
		if (lines.length) {
            if ((Number(line.attr("id")) != lines[lines.length-1].id) && (Number(line.attr("id")) != lines[0].id)) {
                var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
                lines.push(lineData);
                winningLineId.push(Number(line.attr("id")));
           //     console.log("lines entered", lines);
                if (lineData.id === 0) {
                    scatterWin = lineData.won;                
                    scatterLength = a.length;
                }
            }
         //   console.log("lines skipped", lines);
		}
		else {
            var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
            lines.push(lineData);
            winningLineId.push(Number(line.attr("id")));
            if (lineData.id === 0) {
                scatterWin = lineData.won;
                scatterLength = a.length;
            }
		}
		if(positions.length){
            var i,
                j,
                k=0,
                l=1,
                n=0,
                m=0;
                tempArray =[];
                tempArray.push(l);
                for(j=0;j< positions.length-1;j++){
                    if(positions[j][k]==positions[j+1][k]){
                        l++;
                        tempArray[n] = l;
                    }else{
                        l=1;
                        tempArray.push(l);
                        n++;
                    }
                }
            numOfWays[wayPos] = tempArray[m];
            for(m=1;m<tempArray.length;m++){
                numOfWays[wayPos] = numOfWays[wayPos] * tempArray[m];
            }
            numOfSym[wayPos] = tempArray.length;
            wayPos++;
            /*console.log("positions",positions);
            console.log("tempArray",tempArray);
            console.log("NumOfSym",numOfSym);
            console.log("numOfWays",numOfWays);*/
        }
    } );
    freeSpinsTotal = parseInt($(responseData).find("msgdata freeSpins").attr("total"));
    freeSpinsUsed = parseInt($(responseData).find("msgdata freeSpins").attr("used"));
    freeSpinsWon = parseInt($(responseData).find("msgdata freeSpins").attr("won"));
    instance.getReelStops = function () {
        //console.log("reels",reels);
        return reels.slice();
    };
    instance.getCreditsWon = function () {
           return creditsWon;
    };
    instance.getScatterWin = function () {
        return scatterWin;
    };
    instance.getLinesWon = function () {
        return linesWon;
    };
    instance.getWinningLines = function () {
        console.log("getWinningLines",lines);
        return lines;
    };
    instance.getFreeSpinsTotal = function () {
        return freeSpinsTotal;
    };
    instance.getFreeSpinsUsed = function () {
        return freeSpinsUsed;
    };
    instance.getScatterLength = function () {
            return scatterLength;
    };
    instance.getStreamingStack = function(){
            return streamPos;
    };
    instance.getAllPositions = function(){
            //console.log("getStreamingStack ",streamPos);
            return {
                win:0,
                length:allPositions.length,
                positions:allPositions
            }
    };
    instance.getSymbolsAndWays = function(){
            return {
                ways:numOfWays,
                symbolNumbers:numOfSym
            }
    };
    instance.getWinningLineId= function(){
            return winningLineId;
    };
    instance.getFreeSpinsWon = function () {
            return freeSpinsWon;
    };
    return instance;
};