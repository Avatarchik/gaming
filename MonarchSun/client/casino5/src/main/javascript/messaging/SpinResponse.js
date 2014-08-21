goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.SpinResponse");

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