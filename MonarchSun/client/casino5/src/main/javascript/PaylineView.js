goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.PaylineView");

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
            /* changes for double winbox
            console.log('reelArray' ,pos[i]);
            console.log('reelID' ,pos[i][0]);
            if(pos[i][0] == 3){
                winframeData.images = [gameServices.assets.getAsset(json.winboxDataBig.image)];
            } else {
                winframeData.images = [gameServices.assets.getAsset(json.winboxData.image)];
            }
            */
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
