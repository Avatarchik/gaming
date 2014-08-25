goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.GameConstants");

amaya.game.GameConstants = {
    NUM_LINES : 5,
    REELS : 5,
    ICONS : 3,
    REEL_STOP_FREE_SPIN : 6,
    REEL_STOP_STAR : 7,
    SPIN_STATE : 0,
    FREE_SPIN_STATE : 1,
    DEFAULT_CREDITS_PER_LINE : 1,
    CREDITS_PER_LINE : [1,2,3,4,5],
    ID_CREDITS_PER_LINE : "creditsPerLine",
    ID_CREDIT_VALUE : "creditValue",
    ID_AUTO_SPIN : "autoSpin"
};
