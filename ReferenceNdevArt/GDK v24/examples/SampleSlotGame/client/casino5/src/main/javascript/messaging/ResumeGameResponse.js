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
    var creditValue;

    /**
     * @type {number}
     */
    var creditsPerLine;

    reels = [];
    $(responseData).find("msgdata reels reel").each( function ( index, element ) {
        var stops = $(element).text().split(",");
        stops[0] = Number(stops[0]);
        stops[1] = Number(stops[1]);
        stops[2] = Number(stops[2]);
        reels.push(stops);
    } );


    creditsWon = parseInt($(responseData).find("msgdata creditResults").attr("won"));

    linesWon = parseInt($(responseData).find("msgdata lines").attr("won"));

    lines = [];
    $(responseData).find("msgdata lines line").each( function ( index, element ) {
        var line = $(element);
        var a = String(line.attr("positions")).split(";");
        var positions = [ a[0].split(","), a[1].split(","), a[2].split(",") ];
        var lineData = { id:Number(line.attr("id")), won:Number(line.attr("won")), icon:Number(line.attr("icon")), positions:positions };
        lines.push(lineData);
    } );

    starsTotal = parseInt($(responseData).find("msgdata stars").attr("total"));

    freeSpinsTotal = parseInt($(responseData).find("msgdata freeSpins").attr("total"));
    freeSpinsUsed = parseInt($(responseData).find("msgdata freeSpins").attr("used"));

    creditValue = Number($(responseData).find("msgdata initialWager").attr("coinValue"));

    creditsPerLine = Number($(responseData).find("msgdata initialWager").attr("creditsPerLine"));

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

    instance.getStarsTotal = function () {
        return starsTotal;
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

    instance.getCreditsPerLine = function () {
        return creditsPerLine;
    };

    return instance;
};