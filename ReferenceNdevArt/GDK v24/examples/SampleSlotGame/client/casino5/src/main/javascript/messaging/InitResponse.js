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
    var starsTotal = parseInt($(responseData).find("msgdata stars").attr("total"));

    /**
     * @return {number}
     */
    instance.getStarsTotal = function () {
        return starsTotal;
    };

    return instance;
};