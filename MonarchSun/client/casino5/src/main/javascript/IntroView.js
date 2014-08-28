goog.require("amaya");
goog.require("amaya.game");
goog.provide("amaya.game.IntroView");

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

    //var tfIntroTextShadow1 = new createjs.Text(langFile.Msg1);
    //amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow1, json.tfIntroTextShadow1 );
    //IntroContainer.addChild(tfIntroTextShadow1);

    //var tfIntroText1 = new createjs.Text(langFile.Msg1);
    //amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText1, json.tfIntroText1 );
    //tfIntroText.text = langFile.Msg;
    // IntroContainer.addChild(tfIntroText1);

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