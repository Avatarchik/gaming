goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.FreeSpinView");

amaya.game.FreeSpinView = function ( container, model ) {

    var instance = {};

    var gameServices = new amaya.GameServices ();

    var json = gameServices.assets.getAsset("device/main.json");
    var langFile = gameServices.assets.getAsset("lang/WhiteBuffalo_lang.json");
	var temp = 0;
	var win =0;
	var num =0;
	var freeinterval;
	var freeTimer;
	var FGdivider =0;
	var FGdiv=0;
	var totalWon;


    var rightMask = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Mask/Right_CornerFeathers.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( rightMask, json.rightMask );
    container.addChild(rightMask);

    var leftMask = new createjs.Bitmap( gameServices.assets.getAsset("device/images/Mask/Left_CornerFeathers.png") );
    amaya.sdk.utils.JSONUtils.fromJSON( leftMask, json.leftMask );
    container.addChild(leftMask);


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
