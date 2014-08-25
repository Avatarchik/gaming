/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.service;

import com.cwh.sample.slotgame.model.PaylineResult;
import com.cwh.sample.slotgame.model.Reel;
import com.cwh.sample.slotgame.model.SpinResults;

import java.util.List;

/**
 * As this is PURELY a sample slot game, to avoid an more configuration files, the paylines, payouts and winning conditions
 * are hard coded. This is NOT a suggested.
 *
 * This class will provide the means to evaluate if a spin (a new set of reels) has met any of the winning conditions.
 */
public class SpinEvaluator {
    public static enum  SPIN_TYPE   {SPIN, FREE}
    public static final Integer     SPIN_WIN_SYMBOL    = 1;
    public static final Integer     SPIN_WIN_THRESHOLD = 5;     // Number of Spin Icons required to award
    public static final Integer     SPIN_WIN_PAYOUT    = 100;   // Number of credits to award on win
    public static final Integer     FREE_WIN_SYMBOL    = 6;
    public static final Integer     FREE_WIN_THRESHOLD = 3;     // Number of Free Icons required award
    public static final Integer     FREE_WIN_PAYOUT    = 10;    // Number of Free Spins awarded
    public static final Integer     BONUS_WIN_SYMBOL   = 7;
    public static final Integer[][] PAYLINES = new Integer[][]{ { 1, 1, 1, 1, 1},
                                                                { 0, 0, 0, 0, 0},
                                                                { 2, 2, 2, 2, 2},
                                                                { 0, 1, 2, 1, 0},
                                                                { 2, 1, 0, 1, 2} };

    /**
     * This method, given a set of generated reels, will evaluate if the winning conditions have been met.
     *
     * @param reels The generated Reels.
     * @return SpinResults. Contains the winning paylines and amount won.
     */
    public SpinResults evaluateSpin(List<Reel> reels) {
        SpinResults spinResult = new SpinResults();

        for(int i = 0; i < SpinEvaluator.PAYLINES.length; i++) {
            Integer[] payLine = SpinEvaluator.PAYLINES[i];
            PaylineResult pResult = this.evaluatePayline(reels, payLine);
            if(pResult != null) {
                pResult.setPaylineId(i);
                spinResult.addPaylineResult(pResult);
            }
        }

        return spinResult;
    }

    /**
     * This method will count the reels to see how many Sevens occurred in the reel.
     *
     * @param reels The generated Reels.
     * @return Integer. A count of the number of Sevens that occurred in the reel.
     */
    public Integer evaluateBonusFeature(List<Reel> reels) {
        Integer numBonusSymbols = 0;
        for(Reel aReel : reels) {
            for(int i = 0; i < 3; i++) {
                if(aReel.getReelCellValue(i).equals(BONUS_WIN_SYMBOL)) {
                    numBonusSymbols++;
                }
            }
        }
        return numBonusSymbols;
    }

    /**
     * This method will count the number of Free icons on the reels and determine
     * the amount of Free spins to award.
     *
     * @param reels The generated Reels.
     * @return Integer. A the number of free spins awarded.
     */
    public Integer evaluateFreeFeature(List<Reel> reels) {
        Integer numFreeFeatureTriggered = 0;
        // Run thru the reels to see how many of the Free Symbols found
        Integer numFreeSymbols = 0;
        for(Reel aReel : reels) {
            for(int i = 0; i < 3; i++) {
                if(aReel.getReelCellValue(i).equals(FREE_WIN_SYMBOL)) {
                    numFreeSymbols++;
                }
            }
        }
        // See if threshold has been met for triggering the Free Feature
        if(numFreeSymbols >= FREE_WIN_THRESHOLD) {
            numFreeFeatureTriggered = FREE_WIN_PAYOUT;
        }

        return numFreeFeatureTriggered;
    }

    private PaylineResult evaluatePayline(List<Reel> reels, Integer[] payline) {
        PaylineResult pResult = null;

        StringBuilder winReelPos   = new StringBuilder();
        Integer       winIconCount = 0;
        // Run thru each reel and determine if the winning symbol is found.
        for(int i = 0; i < reels.size(); i++) {
            Reel aReel = reels.get(i);
            if(aReel.getReelCellValue(payline[i]).equals(SPIN_WIN_SYMBOL)) {
                winReelPos.append(winReelPos.length() > 0 ? ";" : "");
                winReelPos.append(aReel.getReelNum()).append(",").append(payline[i]);
                winIconCount++;
            }
        }
        // Check to see if we found the min number of icons needed to win
        if(winIconCount >= SPIN_WIN_THRESHOLD) {
            pResult = new PaylineResult();
            pResult.setPositions(winReelPos.toString());
            pResult.setCreditsWon(SPIN_WIN_PAYOUT);
            pResult.setIconWon(SPIN_WIN_SYMBOL.toString());
        }

        return pResult;
    }    
}