goog.require("amaya");
goog.require("amaya.game");

goog.provide("amaya.game.GameConstants");

amaya.game.GameConstants = {
    NUM_LINES : 50,
    REELS : 5,
    ICONS : 4,
    REEL_STOP_FREE_SPIN : 6,
    REEL_STOP_STAR : 7,
    SPIN_STATE : 0,
    FREE_SPIN_STATE : 1,
    DEFAULT_CREDITS_PER_LINE : 1,
    CREDITS_PER_LINE : [1,2,3,4,5,6,7,8,9,10],
    ID_CREDITS_PER_LINE : "creditsPerLine",
    ID_CREDIT_VALUE : "creditValue",
    ID_AUTO_SPIN : "autoSpin",

    SYM_WILD : 1,
    SYM_LOGO : 2,
    SYM_REDWOLF : 3,
    SYM_BLUEWOLF : 4,
    SYM_FIREBIRD : 5,
    SYM_PAWS : 6,
    SYM_ACE : 7,
    SYM_KING : 8,
    SYM_QUEEN : 9,
    SYM_JACK : 10,
    SYM_SCATTER : 11,

    NUM_REEL_SPIN_ROWS : 7,
    NUM_SYMBOLS : 11,

    REEL_INDEX_START_INDEX : 1,
    SYMBOL_INDEX_START_INDEX : 1,

    CELEBRATION_WINDOW_TIMER : 3000,

    NUM_FREESPINS_WON : [0,5,10,20,60]
};
