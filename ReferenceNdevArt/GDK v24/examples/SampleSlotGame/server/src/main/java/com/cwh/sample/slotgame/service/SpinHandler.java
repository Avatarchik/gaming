/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.service;

import com.cwh.sample.slotgame.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will handle the spinning request. Generating the reels and evaluting the results.
 */
public class SpinHandler {
    private SpinEvaluator   spinEvaluator   = new SpinEvaluator();
    private GameState       gameState       = null;
    private RandomPools     randomPools     = null;
    private PersistentState persistentState = null;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPersistentState(PersistentState persistentState) {
        this.persistentState = persistentState;
    }

    public void setRandomPools(RandomPools randomPools) {
        this.randomPools = randomPools;
    }

    /**
     * Generate the reels and evalutate. Once the reels have been evaluated, update the states.
     */
    public void processSpin() {
        try {
            List<Reel>  reelResults = this.generateReelResults();
            SpinResults spinResult  = this.spinEvaluator.evaluateSpin(reelResults);
            Integer     bonusCount  = this.spinEvaluator.evaluateBonusFeature(reelResults);
            Integer     freeAward   = this.spinEvaluator.evaluateFreeFeature(reelResults);
            // Update Game State
            this.gameState.setReels(reelResults);
            this.gameState.setSpinResults(spinResult);
            this.gameState.setCreditWonTotal(this.gameState.getCreditWonTotal() + spinResult.getCreditsWonTotal());
            this.gameState.setFreeAwardsTotal(this.gameState.getFreeAwardsTotal() + freeAward);
            if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE)) {
                this.gameState.setCreditFreeWonTotal(this.gameState.getCreditFreeWonTotal() + spinResult.getCreditsWonTotal());
            }
            // Check FreeAward and State
            this.gameState.setFreeAwardsUsed(this.checkFreeFeatureUsed());
            this.gameState.setPlayState(this.determineStateTransition());
            // Update Persistent State
            this.persistentState.setNumBonusSymbols(this.persistentState.getNumBonusSymbols() + bonusCount);
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process a Spin: " + t, t);
        }
    }

    private List<Reel> generateReelResults() {
        // Determine the Reels
        List<Reel> reelResults = new ArrayList<Reel>(5);
        for(int i = 0; i < 5; i++) {
            Reel newReel = new Reel();
            newReel.setReelNum(i);
            newReel.setReelPos(this.randomPools.nextSeq("POOL_0_14"));
            reelResults.add(newReel);
        }
        return reelResults;
    }

    private GameState.PLAY_STATE determineStateTransition() {
        GameState.PLAY_STATE stateTransistion = GameState.PLAY_STATE.FINISHED;
        // If there are still pending free games, its a FREE state.
        if(this.gameState.getFreeAwardsTotal() - this.gameState.getFreeAwardsUsed() > 0) {
            stateTransistion = GameState.PLAY_STATE.FREE;
        }
        return stateTransistion;
    }

    private Integer checkFreeFeatureUsed() {
        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE)) {
            this.gameState.setFreeAwardsUsed(this.gameState.getFreeAwardsUsed() + 1);
        }
        return this.gameState.getFreeAwardsUsed();
    }
}