/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.service;

import com.cwh.sample.slotgame.model.PaylineResult;
import com.cwh.sample.slotgame.model.Reel;
import mockit.Deencapsulation;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.*;

public class SpinEvaluatorTest {
    @Test
    public void testSpin_allLinesWin() {
        /**
         * Reels:
         *  1  1  1  1  1
         *  1  1  1  1  1
         *  1  1  1  1  1
         */
        try {
            List<Reel> reels = new LinkedList<Reel>();
            reels.add(new Reel().setReelNum(0).setReelPos(0));
            reels.add(new Reel().setReelNum(1).setReelPos(0));
            reels.add(new Reel().setReelNum(2).setReelPos(0));
            reels.add(new Reel().setReelNum(3).setReelPos(0));
            reels.add(new Reel().setReelNum(4).setReelPos(0));

            SpinEvaluator sEvaluator = new SpinEvaluator();
            for(Integer[] payLine : SpinEvaluator.PAYLINES) {
                PaylineResult pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, payLine);
                assertEquals(100, pResult.getCreditsWon().intValue());
            }
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSpin_threeLinesWin() {
        /**
         * Reels:
         *  1  7  1  7  1
         *  1  1  7  1  1
         *  1  1  1  1  1
         */
        try {
            List<Reel> reels = new LinkedList<Reel>();
            reels.add(new Reel().setReelNum(0).setReelPos(0));
            reels.add(new Reel().setReelNum(1).setReelPos(14));
            reels.add(new Reel().setReelNum(2).setReelPos(6));
            reels.add(new Reel().setReelNum(3).setReelPos(14));
            reels.add(new Reel().setReelNum(4).setReelPos(0));

            SpinEvaluator sEvaluator = new SpinEvaluator();
            PaylineResult pResult    = null;

            pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, SpinEvaluator.PAYLINES[2]);
            assertNotNull(pResult);
            assertEquals(100, pResult.getCreditsWon().intValue());
            pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, SpinEvaluator.PAYLINES[3]);
            assertNotNull(pResult);
            assertEquals(100, pResult.getCreditsWon().intValue());
            pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, SpinEvaluator.PAYLINES[4]);
            assertNotNull(pResult);
            assertEquals(100, pResult.getCreditsWon().intValue());

            pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, SpinEvaluator.PAYLINES[0]);
            assertNull(pResult);
            pResult = Deencapsulation.invoke(sEvaluator, "evaluatePayline", reels, SpinEvaluator.PAYLINES[1]);
            assertNull(pResult);


        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSpin_countSevens() {

        try {
            List<Reel>    reels      = new LinkedList<Reel>();
            SpinEvaluator sEvaluator = new SpinEvaluator();
            /**
             * Reels:
             *  1  6  1  6  1
             *  7  2  4  1  7
             *  2  3  5  2  4
             */
            reels.add(new Reel().setReelNum(0).setReelPos(11));
            reels.add(new Reel().setReelNum(1).setReelPos(11));
            reels.add(new Reel().setReelNum(2).setReelPos(11));
            reels.add(new Reel().setReelNum(3).setReelPos(11));
            reels.add(new Reel().setReelNum(4).setReelPos(11));
            assertEquals(2, sEvaluator.evaluateBonusFeature(reels).intValue());

            /**
             * Reels:
             *  5  6  1  6  5
             *  6  2  4  1  6
             *  1  3  5  2  1
             */
            reels.clear();
            reels.add(new Reel().setReelNum(0).setReelPos(9));
            reels.add(new Reel().setReelNum(1).setReelPos(11));
            reels.add(new Reel().setReelNum(2).setReelPos(11));
            reels.add(new Reel().setReelNum(3).setReelPos(11));
            reels.add(new Reel().setReelNum(4).setReelPos(9));

            assertEquals(0, sEvaluator.evaluateBonusFeature(reels).intValue());
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    } 

    @Test
    public void testSpin_FreeSpins() {
        try {
            List<Reel>    reels      = new LinkedList<Reel>();
            SpinEvaluator sEvaluator = new SpinEvaluator();

            reels.add(new Reel().setReelNum(0).setReelPos(0));
            reels.add(new Reel().setReelNum(1).setReelPos(10));
            reels.add(new Reel().setReelNum(2).setReelPos(4));
            reels.add(new Reel().setReelNum(3).setReelPos(9));
            reels.add(new Reel().setReelNum(4).setReelPos(8));
            assertEquals(10, sEvaluator.evaluateFreeFeature(reels).intValue());

            reels.clear();
            reels.add(new Reel().setReelNum(0).setReelPos(0));
            reels.add(new Reel().setReelNum(1).setReelPos(9));
            reels.add(new Reel().setReelNum(2).setReelPos(0));
            reels.add(new Reel().setReelNum(3).setReelPos(9));
            reels.add(new Reel().setReelNum(4).setReelPos(0));
            assertEquals(0, sEvaluator.evaluateFreeFeature(reels).intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}