goog.provide("amaya");
goog.provide("amaya.game");
goog.provide("amaya.game.events");
goog.provide("amaya.game.messaging");

// Note: The amaya and amaya.game objects are already created on the page before the game
// script is ever loaded. Those objects are declared here again to satisfy the Google Closure Compiler
// so that it can sort the dependencies properly. Without them, it optimizes the scripts during compilation
// and injects it's own declaration of the amaya object which overwrites the current one on the page,
// effectively killing the entire framework.

amaya = amaya || {};
amaya.game = amaya.game || {};
amaya.game.events = amaya.game.events || {};
amaya.game.messaging = amaya.game.messaging || {};
