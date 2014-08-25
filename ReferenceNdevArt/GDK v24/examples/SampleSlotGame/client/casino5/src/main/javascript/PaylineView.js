goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.PaylineView");

amaya.game.PaylineView = function ( container, model ) {

    var instance = {};

    var gameServices = new amaya.GameServices();

    var json = gameServices.assets.getAsset("device/reels.json");
    var mainjson = gameServices.assets.getAsset("device/main.json");

    amaya.framework.utils.JSON.fromJSON( container, mainjson.reels );
    
    var paylineData = json.paylineData;
    paylineData.images = [gameServices.assets.getAsset(json.paylineData.image)];
    var paylineSS = new createjs.SpriteSheet(paylineData);

    var mcLine1 = new createjs.Sprite(paylineSS);
    amaya.framework.utils.JSON.fromJSON( mcLine1, json.mcLine1 );
    mcLine1.gotoAndStop(0);
    container.addChild(mcLine1);

    var mcLine2 = new createjs.Sprite(paylineSS);
    amaya.framework.utils.JSON.fromJSON( mcLine2, json.mcLine2 );
    mcLine2.gotoAndStop(1);
    container.addChild(mcLine2);

    var mcLine3 = new createjs.Sprite(paylineSS);
    amaya.framework.utils.JSON.fromJSON( mcLine3, json.mcLine3 );
    mcLine3.gotoAndStop(2);
    container.addChild(mcLine3);

    var mcLine4 = new createjs.Sprite(paylineSS);
    amaya.framework.utils.JSON.fromJSON( mcLine4, json.mcLine4 );
    mcLine4.gotoAndStop(3);
    container.addChild(mcLine4);

    var mcLine5 = new createjs.Sprite(paylineSS);
    amaya.framework.utils.JSON.fromJSON( mcLine5, json.mcLine5 );
    mcLine5.gotoAndStop(4);
    container.addChild(mcLine5);

    var lines = [mcLine1,mcLine2,mcLine3,mcLine4,mcLine5];
    

    var paylineButtonData = json.paylineButtonData;
    paylineButtonData.images = [gameServices.assets.getAsset(json.paylineButtonData.image)];
    var paylineButtonSS = new createjs.SpriteSheet(paylineButtonData);

    var btnLine1 = new createjs.Sprite(paylineButtonSS);
    amaya.framework.utils.JSON.fromJSON( btnLine1, json.btnLine1 );
    btnLine1.gotoAndStop(0);
    container.addChild(btnLine1);

    var btnLine2 = new createjs.Sprite(paylineButtonSS);
    amaya.framework.utils.JSON.fromJSON( btnLine2, json.btnLine2 );
    btnLine2.gotoAndStop(1);
    container.addChild(btnLine2);

    var btnLine3 = new createjs.Sprite(paylineButtonSS);
    amaya.framework.utils.JSON.fromJSON( btnLine3, json.btnLine3 );
    btnLine3.gotoAndStop(2);
    container.addChild(btnLine3);

    var btnLine4 = new createjs.Sprite(paylineButtonSS);
    amaya.framework.utils.JSON.fromJSON( btnLine4, json.btnLine4 );
    btnLine4.gotoAndStop(3);
    container.addChild(btnLine4);

    var btnLine5 = new createjs.Sprite(paylineButtonSS);
    amaya.framework.utils.JSON.fromJSON( btnLine5, json.btnLine5 );
    btnLine5.gotoAndStop(4);
    container.addChild(btnLine5);



    var winBoxBackgroundData = json.winBoxBackgroundData;
    winBoxBackgroundData.images = [gameServices.assets.getAsset(json.winBoxBackgroundData.image)];
    var winBoxBackgroundSS = new createjs.SpriteSheet(winBoxBackgroundData);

    var mcLine1Win = new createjs.Container();
    createWinBox(mcLine1Win,1);
    container.addChild(mcLine1Win);

    var mcLine2Win = new createjs.Container();
    createWinBox(mcLine2Win,2);
    container.addChild(mcLine2Win);

    var mcLine3Win = new createjs.Container();
    createWinBox(mcLine3Win,3);
    container.addChild(mcLine3Win);

    var mcLine4Win = new createjs.Container();
    createWinBox(mcLine4Win,4);
    container.addChild(mcLine4Win);

    var mcLine5Win = new createjs.Container();
    createWinBox(mcLine5Win,5);
    container.addChild(mcLine5Win);

    hideAllPaylines();
    hideAllPaylineWinBoxes();

    instance.enableUI = function () {
        btnLine1.addEventListener("mouseover",onLine1Over);
        btnLine1.addEventListener("mouseout",onLine1Out);
        btnLine2.addEventListener("mouseover",onLine2Over);
        btnLine2.addEventListener("mouseout",onLine2Out);
        btnLine3.addEventListener("mouseover",onLine3Over);
        btnLine3.addEventListener("mouseout",onLine3Out);
        btnLine4.addEventListener("mouseover",onLine4Over);
        btnLine4.addEventListener("mouseout",onLine4Out);
        btnLine5.addEventListener("mouseover",onLine5Over);
        btnLine5.addEventListener("mouseout",onLine5Out);  
    };
    
    instance.disableUI = function () {
        btnLine1.removeEventListener("mouseover",onLine1Over);
        btnLine1.removeEventListener("mouseout",onLine1Out);
        btnLine2.removeEventListener("mouseover",onLine2Over);
        btnLine2.removeEventListener("mouseout",onLine2Out);
        btnLine3.removeEventListener("mouseover",onLine3Over);
        btnLine3.removeEventListener("mouseout",onLine3Out);
        btnLine4.removeEventListener("mouseover",onLine4Over);
        btnLine4.removeEventListener("mouseout",onLine4Out);
        btnLine5.removeEventListener("mouseover",onLine5Over);
        btnLine5.removeEventListener("mouseout",onLine5Out);
    };
    
    instance.showPaylineWin = function ( line, won ) {
        showPayline(line);
        showPaylineWinBox(line, won);
    };
    
    instance.hidePaylineWin = function ( line ) {
        hidePayline(line);
        hidePaylineWinBox(line);
    };
    
    instance.showAllPaylineWins = function () {
        var lines = model.getWinningLines();
        for ( var i=0; i<lines.length; i++ ) {
            instance.showPaylineWin( lines[i].id, lines[i].won );
        }
    };
    
    instance.hideAllPaylineWins = function () {
        hideAllPaylines();
        hideAllPaylineWinBoxes();
    };

    
    
    function showPayline ( line ) {
        lines[line-1].visible = true;
    }
    
    function hidePayline ( line ) {
        lines[line-1].visible = false;
    }
    
    function hideAllPaylines () {
        for ( var i=1; i<=amaya.game.GameConstants.NUM_LINES; i++ ) {
            hidePayline(i);
        }
    }
    
    function showPaylineWinBox ( id, won ) {
        var winBox = getWinBox(id);
        winBox.visible = true;
        winBox.tf.text = won;
    }
    
    function hidePaylineWinBox ( id ) {
        getWinBox(id).visible = false;
    }
    
    function hideAllPaylineWinBoxes () {
        for ( var i=1; i<=amaya.game.GameConstants.NUM_LINES; i++ ) {
            hidePaylineWinBox(i);
        }
    }
    
    function onLine1Over ( event ) {
        instance.hideAllPaylineWins();
        showPayline(1);
    }
    
    function onLine1Out ( event ) {
        hidePayline(1);
    }
    
    function onLine2Over ( event ) {
        instance.hideAllPaylineWins();
        showPayline(2);
    }
    
    function onLine2Out ( event ) {
        hidePayline(2);
    }
    
    function onLine3Over ( event ) {
        instance.hideAllPaylineWins();
        showPayline(3);
    }
    
    function onLine3Out ( event ) {
        hidePayline(3);
    }
    
    function onLine4Over ( event ) {
        instance.hideAllPaylineWins();
        showPayline(4);
    }
    
    function onLine4Out ( event ) {
        hidePayline(4);
    }
    
    function onLine5Over ( event ) {
        instance.hideAllPaylineWins();
        showPayline(5);
    }
    
    function onLine5Out ( event ) {
        hidePayline(5);
    }

    function createWinBox ( lineWinContainer, id ) {
        amaya.framework.utils.JSON.fromJSON( lineWinContainer, json["mcLine"+id+"Win"] );

        var background = new createjs.Sprite(winBoxBackgroundSS);
        background.gotoAndStop(id-1);
        lineWinContainer.addChild(background);

        var textField = new createjs.Text();
        amaya.framework.utils.JSON.fromJSON( textField, json.mcLineWinText );
        lineWinContainer.addChild(textField);

        lineWinContainer.tf = textField;
    }

    function getWinBox ( id ) {
        switch ( id ) {
            case 1: return mcLine1Win;
            case 2: return mcLine2Win;
            case 3: return mcLine3Win;
            case 4: return mcLine4Win;
            case 5: return mcLine5Win;
        }
    }
        
    return instance;

};
