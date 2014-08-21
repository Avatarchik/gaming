goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.ResumeGameResponse");

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
            else if(blueCount == stops.length-1){
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