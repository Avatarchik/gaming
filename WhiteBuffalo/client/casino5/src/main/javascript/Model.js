goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.Model");

/**
 *
 * @param {number} openingBalance
 * @param {Array.<number>} creditValueList
 * @param {number} numberOfLines
 */
amaya.game.Model = function ( openingBalance, creditValueList, numberOfLines ) {

    var instance = {};

    /**
     * @type {number}
     */
    var balance;

    /**
     * @type {Array.<number>}
     */
    var creditValues;

    /**
     * @type {number}
     */
    var lines;

    /**
     * @type {number}
     */
    var bet = 0;

    /**
     * @type {number}
     */
    var creditsPerLine = 0;

    /**
     * @type {number}
     */
    var maxCreditsPerLine = 5;

    /**
     * @type {number}
     */
    var creditsWon = 0;

    /**
     * @type {number}
     */
    var scatterWin = 0;

    /**
     * @type {number}
     */
    var creditValueIndex = 0;

    /**
     * @type {Array.<number>}
     */
    var reelStops;

    /**
     * @type {Array.<number>}
     */
    var baseReelStops;

    /**
     * @type {number}
     */
    var linesWon = 0;

    /**
     * @type {Array.<object>}
     */
    var winningLines;

    /**
     * @type {number}
     */
    var spinStartTime = 0;

    /**
     * @type {number}
     */
    var freeSpinsTotal = 0;

    /**
     * @type {number}
     */
    var scatterLength;

      /**
       * @type {number}
      */
    var freeSpinsUsed = 0;

    /**
     * @type {number}
     */
    var numfreeSpinsWon = 0;

    /**
     * @type {number}
     */
    var freeSpinsWon = 0;

    /**
     * @type {boolean}
     */
    var freeSpinsTriggered = false;

    /**
     * @type {boolean}
     */
    var flag = false;

    /**
     * @type {number}
     */
    var gameState = 0; // 0 is Regular Spin, 1 is Free Spin

    var isOneWinAnimationCycleComplete;

    /**
     * @type {boolean}
     */
    var gameInProgress = false;

    /**
     * @type {Array.<object>}
     */
    var scatterHitSound = [];

    /**
     * @type {boolean}
     */
    var freeSpinsHasWins = false;

    var streamStack =[];

    var allpos = [];
    var ScatterWays;
	var SymbolsWays;
    var winningLineId;
     /**
      * @returns {Array.<object>}
       */
      instance.getScatterHitSound = function (value) {
           return scatterHitSound[value];
       };

      /**
      * @param {Array.<object>} value
      */
      instance.setScatterHitSound = function (value) {
           scatterHitSound = value;
      };

    instance.getStreamStack =  function () {
         return streamStack;
     };

    instance.setStreamStack = function (value) {
           console.log("setStreamStack",value);
           streamStack = value;

    };

    instance.getAllPosition =  function () {
         return allpos;
     };

    instance.setAllPosition = function (value) {
           allpos = value;
    };

     instance.getScatterWays =  function () {
         return ScatterWays;
     };

    instance.setScatterAllPositions = function (value) {
           ScatterWays = value;
    };
    /**
     * @param {boolean} value
     */
    instance.setFreeSpinsHasWins = function (value) {
        freeSpinsHasWins = value;
    };

    /**
     * @returns {boolean}
     */
    instance.getFreeSpinsHasWins = function () {
        return freeSpinsHasWins;
    };

    instance.resetForSpin = function () {
        reelStops = undefined;
        winningLines = [];
        creditsWon = 0;
        freeSpinsTriggered = false;
    };
    
    instance.resetAfterFreeSpinBonus = function () {
        freeSpinsTotal = 0;
        freeSpinsUsed = 0;
        freeSpinsWon = 0;
        numfreeSpinsWon = 0;
    };

    /**
     * @returns {number}
     */
    instance.getLines = function () {
        return lines;
    };


    /**
     * @returns {number}
     */
    instance.getBet = function () {
        return bet;
    };

    /**
     * @returns {number}
     */
    instance.getCreditsPerLine = function () {
        return creditsPerLine;
    };

    /**
     * @param {number} value
     */
    instance.setCreditsPerLine = function (value) {
        creditsPerLine = value;
        updateBet();
    };

    /**
     * @returns {number}
     */
    instance.getMaxCreditsPerLine = function () {
        return maxCreditsPerLine;
    };

    /**
     * @returns {number}
     */
    instance.getBalance = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(balance);
    };

    /**
     * @param {number} value
     */
    instance.setBalance = function (value) {
        balance = value;
    };

    /**
     * @returns {number}
     */
    instance.getCredits = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(balance / instance.getCreditValue());
    };

    /**
     * @returns {number}
     */
    instance.getCreditValue = function () {
        return creditValues[creditValueIndex];
    };

    /**
     * @returns {number}
     * @throws Invalid credit value.
     */
    instance.setCreditValue = function ( val ) {
        var index = creditValues.indexOf(val);
        if ( index != -1 ) {
            creditValueIndex = index;
        } else {
            throw "Credit value " + val + " is not valid for this profile ("+ creditValues +")";
        }
    };

    /**
     * @returns {number}
     */
    instance.increaseCreditValue = function () {
        if (creditValueIndex < creditValues.length-1) {
            creditValueIndex++;
        }
        return instance.getCreditValue();
    };

    /**
     * @returns {number}
     */
    instance.decreaseCreditValue = function () {
        if (creditValueIndex > 0) {
            creditValueIndex--;
        }
        return instance.getCreditValue();
    };

    /**
     * @returns {number}
     */
    instance.getWon = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(linesWon * instance.getCreditValue());
    };

    /**
     * @returns {number}
     */
    instance.getCreditsWon = function () {
        return creditsWon;

    };

    /**
     * @param {number} value
     */
    instance.setCreditsWon = function (value) {
        creditsWon = value;
    };

    /**
     * @returns {number}
     */
    instance.getScatterWin = function () {
        return scatterWin;
    };

    /**
     * @param {number} value
     */
    instance.setScatterWin = function (value) {
        scatterWin = value;
    };


    instance.getScatterLength = function (value) {
                 return scatterLength;
    };

    instance.setScatterLength = function (value) {
            scatterLength = value;
    };

    /**
     * @returns {number}
     */
    instance.getNetWon = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint((linesWon - bet) * instance.getCreditValue());
    };

    /**
     * @returns {number}
     */
    instance.getNetCreditsWon = function () {
        return linesWon - bet;
    };

    /**
     * @returns {boolean}
     */
    instance.hasReelStops = function () {
        return reelStops != undefined;
    };

    /**
     * @returns {Array.<number>}
     */
    instance.getReelStops = function () {
        return reelStops;
    };

    /**
     * @returns {Array.<number>}
     */
    instance.getBaseReelStops = function () {
        return baseReelStops;
    };

    /**
     * @param {number} reel
     * @param {number} icon
     * @returns {number}
     */
    instance.getReelStop = function ( reel, icon ) {
        return reelStops[reel][icon];
    };

    /**
     * @param {number} reel
     * @param {number} icon
     * @returns {number}
     */
    instance.getBaseReelStop = function ( reel, icon ) {
        return baseReelStops[reel][icon];
    };


    /**
     * @param {Array.<number>} value
     */
    instance.setReelStops = function (value) {
        reelStops = value;
    };

    /**
     * @param {Array.<number>} value
     */
    instance.setBaseReelStops = function (value) {
        baseReelStops = value;
    };

    /**
     * @returns {Array.<object>}
     */
    instance.getWinningLines = function () {
        return winningLines;
    };

    /**
     * @param {Array.<object>} value
     */
    instance.setWinningLines = function (value) {
        winningLines = value;
    };

    /**
     * @returns {number}
     */
    instance.getSpinStartTime = function () {
        return spinStartTime;
    };

    /**
     * @param {number} value
     */
    instance.setSpinStartTime = function (value) {
        spinStartTime = value;
    };
    
    function updateBet () {
        bet = creditsPerLine * lines;
    }

    /**
     * @returns {number}
     */
    instance.getFreeSpinsTotal = function () {
        return freeSpinsTotal;
    };

    /**
     * @param {number} value
     */
    instance.setFreeSpinsTotal = function (value) {
        freeSpinsTotal = value;
    };

    /**
     * @returns {number}
     */
    instance.getFreeSpinsUsed = function () {
        return freeSpinsUsed;
    };

    /**
     * @param {number} value
     */
    instance.setFreeSpinsUsed = function (value) {
        freeSpinsUsed = value;
    };

    /**
     * @returns {number}
     */
    instance.getFreeSpinsWon = function () {
        return freeSpinsWon;
    };

    /**
     * @param {number} value
     */
    instance.setFreeSpinsWon = function (value) {
        freeSpinsWon = value;
    };

    /**
     * @returns {number}
     */
    instance.getNumFreeSpinsWon = function () {
        return numfreeSpinsWon;
    };

    /**
     * @param {number} value
     */
    instance.setNumFreeSpinsWon = function (value) {
        numfreeSpinsWon = value;
    };

    /**
     * @returns {boolean}
     */
    instance.getFreeSpinsTriggered = function () {
        return freeSpinsTriggered;
    };

    /**
     * @param {boolean} value
     */
    instance.setFreeSpinsTriggered = function (value) {
        freeSpinsTriggered = value;
    };

    /**
     * @returns {number}
     */
    instance.getGameState = function () {
        return gameState;
    };

    /**
     * @param {number} value
     */
    instance.setGameState = function (value) {
        gameState = value;
    };

    /**
     * @returns {boolean}
     */
    instance.spinHasPaylineWins = function () {
        return winningLines.length > 0;
    };


    /**
     * @returns {number}
     */
    instance.getLinesWon = function () {
        return linesWon;
    };

    /**
     * @param {number} value
     */
    instance.setLinesWon = function (value) {
        linesWon = value;
    };

    /**
     * @returns {boolean}
     */
    instance.getGameInProgress = function () {
        return gameInProgress;
    };

    /**
     * @param {boolean} gameInProgress
     */
    instance.setGameInProgress = function (isGameInProgress) {
        gameInProgress = isGameInProgress;
    };

    /**
     * @returns {number}
     */
    instance.getWager = function () {
        return amaya.sdk.utils.MathUtils.correctFloatingPoint(bet * instance.getCreditValue());
    };

    /**
     * @returns {number}
     */
    instance.getDisplayBalance = function () {
        return balance - instance.getWager();
    };

    /**
     * @returns {number}
     */
    instance.getDisplayCredits = function () {
        return instance.getCredits() - bet;
    };

    /**
     * @param {boolean} value
     */
    instance.setOneWinAnimationCycleComplete = function (value) {
        isOneWinAnimationCycleComplete = value;
    }

        instance.getFlag = function () {
            return flag;
        };


    /**
     * @returns {boolean}
     */
    instance.hasOneWinAnimationCycleCompleted = function () {
        return isOneWinAnimationCycleComplete;
    }

    /**
     * @param {boolean} value
     */
    instance.setFlag = function (value) {
        flag = value;
    }
	
	instance.getSymbolsWays =function(){
             return SymbolsWays;
    }

    instance.setSymbolsWays =function(value){
            SymbolsWays = value;
    }

    instance.getWinningLineId= function(){
            return winningLineId;
    };

    instance.setWinningLineId= function(value){
             winningLineId = value;
    };

    balance = openingBalance;
    creditValues = creditValueList;
    creditValueIndex = 0;
    lines = numberOfLines;
    creditsPerLine = amaya.game.GameConstants.DEFAULT_CREDITS_PER_LINE;
    updateBet();
    instance.resetForSpin();

    return instance;
};
