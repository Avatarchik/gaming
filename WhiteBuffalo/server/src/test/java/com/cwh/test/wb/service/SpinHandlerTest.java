/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.wb.service;

import   com.cwh.wb.model.GameState;
import   com.cwh.wb.model.PersistentState;
import com.cwh.wb.model.Profile;
import   com.cwh.wb.model.RandomPools;
import com.cwh.wb.service.SpinHandler;
import nu.xom.Builder;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class SpinHandlerTest {
    @Test
    public void testSpin_loss() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                    "    <pool id=\"mainSpinReel1_1_at_0_129\">70</pool>\n" +
                    "    <pool id=\"mainSpinReel2_1_at_0_199\">80</pool>\n" +
                    "    <pool id=\"mainSpinReel3_1_at_0_199\">77</pool>\n" +
                    "    <pool id=\"mainSpinReel4_1_at_0_199\">73</pool>\n" +
                    "    <pool id=\"mainSpinReel5_1_at_0_149\">74</pool>\n" +
                    "    <pool id=\"mainSpinReelRE_1_at_0_499\">0</pool>\n" +
                    "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "3");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
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
                            "    <pool id=\"mainSpinReel1_1_at_0_129\">63</pool>\n" +
                            "    <pool id=\"mainSpinReel2_1_at_0_199\">61</pool>\n" +
                            "    <pool id=\"mainSpinReel3_1_at_0_199\">83</pool>\n" +
                            "    <pool id=\"mainSpinReel4_1_at_0_199\">69</pool>\n" +
                            "    <pool id=\"mainSpinReel5_1_at_0_149\">96</pool>\n" +
                            "    <pool id=\"mainSpinReelRE_1_at_0_499\">0</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "3");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(320, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testSpin_re() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel1_1_at_0_129\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel2_1_at_0_199\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel3_1_at_0_199\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel4_1_at_0_199\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel5_1_at_0_149\">0</pool>\n" +
                            "    <pool id=\"mainSpinReelRE_1_at_0_499\">22</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "3");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(1280, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testSpin_jackpot() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel1_1_at_0_129\">43</pool>\n" +
                            "    <pool id=\"mainSpinReel2_1_at_0_199\">78</pool>\n" +
                            "    <pool id=\"mainSpinReel3_1_at_0_199\">81</pool>\n" +
                            "    <pool id=\"mainSpinReel4_1_at_0_199\">155</pool>\n" +
                            "    <pool id=\"mainSpinReel5_1_at_0_149\">82</pool>\n" +
                            "    <pool id=\"mainSpinReelRE_1_at_0_499\">0</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(5);
            gameState.setMaxBet(true);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(625050, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
    
    //Test that the jackpot is not given when it is not max bet
    @Test
    public void testSpin_nojackpot() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel1_1_at_0_129\">43</pool>\n" +
                            "    <pool id=\"mainSpinReel2_1_at_0_199\">78</pool>\n" +
                            "    <pool id=\"mainSpinReel3_1_at_0_199\">81</pool>\n" +
                            "    <pool id=\"mainSpinReel4_1_at_0_199\">155</pool>\n" +
                            "    <pool id=\"mainSpinReel5_1_at_0_149\">82</pool>\n" +
                            "    <pool id=\"mainSpinReelRE_1_at_0_499\">0</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(410, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testSpin_getFreeSpins() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel1_1_at_0_129\">43</pool>\n" +
                            "    <pool id=\"mainSpinReel2_1_at_0_199\">103</pool>\n" +
                            "    <pool id=\"mainSpinReel3_1_at_0_199\">101</pool>\n" +
                            "    <pool id=\"mainSpinReel4_1_at_0_199\">100</pool>\n" +
                            "    <pool id=\"mainSpinReel5_1_at_0_149\">82</pool>\n" +
                            "    <pool id=\"mainSpinReelRE_1_at_0_499\">0</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);
            
            System.out.println(gameState.getReels().getReelsDisplay().toString());

            assertEquals(32, gameState.getFreeAwardsTotal().intValue());
            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(5, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }
}