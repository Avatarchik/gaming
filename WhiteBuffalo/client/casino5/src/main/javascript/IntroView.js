goog.require("amaya");
goog.require("amaya.game");
goog.provide("amaya.game.IntroView");

amaya.game.IntroView = function ( container) {

    var instance = new amaya.api.EventDispatcher();

    var gameServices = new amaya.GameServices();
	
    var IntroContainer = new createjs.Container();

    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/WhiteBuffalo_lang.json");

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

/*    var tfIntroTextShadow2 = new createjs.Text(langFile.Msg2);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow2, json.tfIntroTextShadow2 );
    IntroContainer.addChild(tfIntroTextShadow2);

    var tfIntroText2 = new createjs.Text(langFile.Msg2);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText2, json.tfIntroText2 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText2);

    var tfIntroTextShadow3 = new createjs.Text(langFile.Msg3);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroTextShadow3, json.tfIntroTextShadow3 );
    IntroContainer.addChild(tfIntroTextShadow3);

    var tfIntroText3 = new createjs.Text(langFile.Msg3);
    amaya.sdk.utils.JSONUtils.fromJSON( tfIntroText3, json.tfIntroText3 );
    //tfIntroText.text = langFile.Msg;
     IntroContainer.addChild(tfIntroText3);*/
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