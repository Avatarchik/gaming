/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.service;

import com.cwh.sample.slotgame.model.GameState;
import com.cwh.sample.slotgame.model.PersistentState;
import com.cwh.sample.slotgame.model.RandomPools;
import nu.xom.Builder;
import org.junit.Test;

import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class SpinHandlerTest {
    @Test
    public void testSpin_loss() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                    "    <pool id=\"POOL_0_14\">5,6,6,7,0</pool>\n" +
                    "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());


            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin();

            assertEquals(0, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSpin_win() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                    "    <pool id=\"POOL_0_14\">0,0,0,0,0</pool>\n" +
                    "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin();

            assertEquals(5, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(500, gameState.getCreditWonTotal().intValue());

            assertEquals(0, persistentState.getNumBonusSymbols().intValue());
        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}