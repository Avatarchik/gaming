goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.WonView");

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
