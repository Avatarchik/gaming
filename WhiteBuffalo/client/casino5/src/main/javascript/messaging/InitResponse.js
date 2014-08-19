goog.require("amaya");
goog.require("amaya.game");
goog.require("amaya.game.messaging");

goog.provide("amaya.game.messaging.InitResponse");

/**
 *
 * @param {xml} responseData
 */
amaya.game.messaging.InitResponse = function ( responseData ) {

    var instance = {};

    /**
     * @type {number}
     */
    var mainReels = [];
    /**
     * @type {number}
     */
    var freeReels = [];

    /**
     * @type {Array.<Array>}
     */
    var reels;

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

    reels = [];
    $(responseData).find("msgdata initStops").each( function ( index, element ) {
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
            reels.push(reel);
        }
    } );

    instance.getReelStops = function () {
        console.log("reels",reels);
        return reels.slice();
    };

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

    return instance;
};