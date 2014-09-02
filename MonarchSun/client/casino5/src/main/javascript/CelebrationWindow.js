/**
 * Created with IntelliJ IDEA.
 * User: amansoor
 * Date: 12/11/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
goog.require("amaya");
goog.require("amaya.game");
goog.provide("amaya.game.CelebrationWindow");

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

        //CelebrationWindow.addChild(FreeIntroBg);
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

           //CelebrationWindow.addChild(FreeOutroBg);

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