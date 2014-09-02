/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.test.monarchsun.service;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import com.cwh.monarchsun.MonarchSunConfig.MonarchSunREReelsConfig;
import   com.cwh.monarchsun.model.GameState;
import   com.cwh.monarchsun.model.PersistentState;
import com.cwh.monarchsun.model.Profile;
import   com.cwh.monarchsun.model.RandomPools;
import com.cwh.monarchsun.service.MonarchSunREEvaluator;
import com.cwh.monarchsun.service.SpinHandler;
import nu.xom.Builder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;

public class SpinHandlerTest {
    @Test
    public void main_spin_lose_tc1() {
        try {
            /*final String sRandomPools =
                    "<randomPools>\n" +
                    "    <pool id=\"mainSpinReel921_1_at_0_99\">79</pool>\n" +
                    "    <pool id=\"mainSpinReel922_1_at_0_98\">76</pool>\n" +
                    "    <pool id=\"mainSpinReel923_1_at_0_99\">81</pool>\n" +
                    "    <pool id=\"mainSpinReel924_1_at_0_98\">76</pool>\n" +
                    "    <pool id=\"mainSpinReel925_1_at_0_99\">79</pool>\n" +
                    "    <pool id=\"mainSpinReel92RE_1_at_0_98\">0</pool>\n" +
                    "</randomPools>";*/

            /*final String sRandomPools =
                    "<randomPools>\n" +
                    "    <pool id=\"mainSpinReel941_1_at_0_99\">86</pool>\n" +
                    "    <pool id=\"mainSpinReel942_1_at_0_97\">85</pool>\n" +
                    "    <pool id=\"mainSpinReel943_1_at_0_99\">82</pool>\n" +
                    "    <pool id=\"mainSpinReel944_1_at_0_97\">86</pool>\n" +
                    "    <pool id=\"mainSpinReel945_1_at_0_99\">87</pool>\n" +
                    "    <pool id=\"mainSpinReel94RE_1_at_0_98\">0</pool>\n" +
                    "</randomPools>";*/

            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel951_1_at_0_99\">74</pool>\n" +
                            "    <pool id=\"mainSpinReel952_1_at_0_97\">80</pool>\n" +
                            "    <pool id=\"mainSpinReel953_1_at_0_99\">81</pool>\n" +
                            "    <pool id=\"mainSpinReel954_1_at_0_97\">80</pool>\n" +
                            "    <pool id=\"mainSpinReel955_1_at_0_99\">81</pool>\n" +
                            "    <pool id=\"mainSpinReel95RE_1_at_0_98\">0</pool>\n" +
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
            
            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(0, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_tc2() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">55</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">65</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">68</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">75</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(150, gameState.getCreditWonTotal().intValue());
            assertEquals(150, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_1wild_tc3() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">55</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">65</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">75</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            //assertEquals(300, gameState.getCreditWonTotal().intValue());
            assertEquals(300, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_both_directions_tc4() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">55</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">65</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">75</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            //assertEquals(100, sHandler.getGameState().getSpinResults().getPaylineResults().get(0).won());
            //assertEquals(144, sHandler.getGameState().getSpinResults().getPaylineResults().get(1).won());
            //assertEquals(300, gameState.getCreditWonTotal().intValue());
            assertEquals(244, gameState.getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_10bonus_tc5() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">16</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">3</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(10, gameState.getFreeAwardsTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_15bonus_tc6() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">16</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(15, gameState.getFreeAwardsTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_20bonus_tc7() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">16</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(20, gameState.getFreeAwardsTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_win_20bonus_with_paylines_tc8() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">55</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">3</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(2, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(64, gameState.getCreditWonTotal().intValue());
            assertEquals(10, gameState.getFreeAwardsTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_win_regular_tc9() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">34</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">81</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">80</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(60, sHandler.getGameState().getCreditWonTotal().intValue());
            //assertEquals(10, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_win_1wild_tc10() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">85</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">80</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(20, sHandler.getGameState().getCreditWonTotal().intValue());
            //assertEquals(10, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_win_2wild_tc11() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">5</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">96</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(2, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(32, sHandler.getGameState().getCreditWonTotal().intValue());
            //assertEquals(10, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_win_3wild_tc12() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">5</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">78</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(5, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(276, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(1, sHandler.getGameState().getStickyWilds().size());
            //assertEquals(10, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_server_error_tc22() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">-1</pool>\n" +
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

            //This test should fail
            fail();

        } catch(Throwable t) {
            //t.printStackTrace();
            //fail();
        }
    }

    @Test
    public void main_spin_symbol_1st_pattern_replacement_lower_tc23() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">0</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));
            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_1st_pattern_replacement_upper_tc23() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">6</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_2nd_pattern_replacement_lower_tc24() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">7</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_2nd_pattern_replacement_upper_tc24() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">11</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_3rd_pattern_replacement_lower_tc25() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">12</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_3rd_pattern_replacement_upper_tc25() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">21</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_4th_pattern_replacement_lower_tc26() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">22</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_4th_pattern_replacement_upper_tc26() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">31</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_5th_pattern_replacement_lower_tc27() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">32</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_5th_pattern_replacement_upper_tc27() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">41</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_6th_pattern_replacement_lower_tc28() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">42</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_6th_pattern_replacement_upper_tc28() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">45</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_7th_pattern_replacement_lower_tc29() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">46</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(24, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_7th_pattern_replacement_upper_tc29() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">49</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(24, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_8th_pattern_replacement_lower_tc30() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">50</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_8th_pattern_replacement_upper_tc30() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">59</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_9th_pattern_replacement_lower_tc31() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">60</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_9th_pattern_replacement_upper_tc31() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">69</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_10th_pattern_replacement_lower_tc32() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">70</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_10th_pattern_replacement_upper_tc32() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">74</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_11th_pattern_replacement_lower_tc33() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">75</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_11th_pattern_replacement_upper_tc33() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">79</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_12th_pattern_replacement_lower_tc34() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">80</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("9"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_12th_pattern_replacement_upper_tc34() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">84</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("9"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_13th_pattern_replacement_lower_tc35() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">85</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(288, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_13th_pattern_replacement_upper_tc35() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">89</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(288, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_14th_pattern_replacement_lower_tc36() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">90</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("9"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(288, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_14th_pattern_replacement_upper_tc36() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">94</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("9"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(288, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_15th_pattern_replacement_lower_tc37() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">95</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_15th_pattern_replacement_upper_tc37() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">99</pool>\n" +
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

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_symbol_replacement_invalid_pattern_tc38() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">100</pool>\n" +
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

            fail();

        } catch(Throwable t) {
            //t.printStackTrace();
            //fail();
        }
    }

    @Test
    public void main_spin_symbol_replacement_invalid_pattern_tc39() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">-1</pool>\n" +
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

            fail();

        } catch(Throwable t) {
            //t.printStackTrace();
            //fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_1st_pattern_lower_tc40() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">0</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_1st_pattern_upper_tc40() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">9</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_2nd_pattern_lower_tc41() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">10</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_2nd_pattern_upper_tc41() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">14</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_3rd_pattern_lower_tc42() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">15</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("8"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(512, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_3rd_pattern_upper_tc42() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">19</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("8"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(512, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_4th_pattern_lower_tc43() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(512, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_4th_pattern_upper_tc43() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">24</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("1"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(512, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_5th_pattern_lower_tc44() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">25</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_5th_pattern_upper_tc44() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">74</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_6th_pattern_lower_tc45() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">75</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_6th_pattern_upper_tc45() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">124</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("10"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("10"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_7th_pattern_lower_tc46() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">125</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_7th_pattern_upper_tc46() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">174</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("8"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_8th_pattern_lower_tc47() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">175</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_8th_pattern_upper_tc47() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">209</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("7"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_9th_pattern_lower_tc48() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">210</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_9th_pattern_upper_tc48() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">259</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("7"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_10th_pattern_lower_tc49() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">260</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_10th_pattern_upper_tc49() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">309</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(0)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(1)).equals("9"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(2)).equals("3"));
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getReplacementPattern().get(3)).equals("3"));

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_invalid_lower_tc50() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">-1</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            fail();

        } catch(Throwable t) {
            //t.printStackTrace();
            //fail();
        }
    }

    @Test
    public void free_spin_symbol_replacement_invalid_upper_tc50() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">310</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.FREE);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            fail();

        } catch(Throwable t) {
            //t.printStackTrace();
            //fail();
        }
    }

    @Test
    public void main_spin_win_3rd_reel_4th_pos_tc51() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">11</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">84</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">46</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertTrue(MonarchSunConfig.SYMBOL_MAP.get(sHandler.getGameState().getSpinResults().getPaylineResults().get(0).symbol()).equals("3"));
            assertEquals(720, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    public void main_spin_no_win_3rd_reel_4th_pos_tc52() {
        try {
            final String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">11</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">78</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">1</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">46</pool>\n" +
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

            //System.out.println(gameState.getReelDisplay().toString());

            assertEquals(0, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, sHandler.getGameState().getCreditWonTotal().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

    @Test
    //@Ignore
    public void main_free_spin_combination_tc53() {
        try {
            //Win 10 free spins
            String sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">16</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">36</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">17</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">47</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
                            "</randomPools>";

            final GameState       gameState       = new GameState();
            final PersistentState persistentState = new PersistentState();
            RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            Map<String, String> parameterMap = new HashMap<String, String>();
            parameterMap.put("level", "1");
            Profile profile = new Profile(parameterMap);
            gameState.setCreditWager(1);
            gameState.setPlayState(GameState.PLAY_STATE.NEW);

            SpinHandler sHandler = new SpinHandler();
            sHandler.setGameState(gameState);
            sHandler.setPersistentState(persistentState);
            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(0, gameState.getCreditWonTotal().intValue());
            assertEquals(10, gameState.getFreeAwardsTotal().intValue());

            /**************************************************************************/
            //FREE SPINS
            /**************************************************************************/

            sHandler.getGameState().setPlayState(GameState.PLAY_STATE.FREE);

            //FS #1
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">85</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">80</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(20, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #2
            //Same play - Add 20
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">24</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">85</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">80</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">20</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            assertEquals(1, gameState.getSpinResults().getPaylineResults().size());
            assertEquals(40, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #3
            //new play
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">35</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">34</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">81</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">80</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">75</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(100, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #4
            //No Win (*)
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">28</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">26</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(100, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #5
            //Adding sticky wild
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">28</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">5</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(740, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #6
            //No win by combination but win with sticky wild
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">28</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">26</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            //Win 320
            assertEquals(1060, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #7
            //Overwritten existing sticky wild on 1st position
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">28</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">5</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(1700, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #8
            //Adding new sticky wild
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">28</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">3</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(2340, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #9
            //No win
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(2340, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

            //FS #10
            //Win ONLY with Sticky wilds
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"freeSpinReel1_1_at_0_99\">37</pool>\n" +
                            "    <pool id=\"freeSpinReel2_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel3_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel4_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReel5_1_at_0_99\">7</pool>\n" +
                            "    <pool id=\"freeSpinReelRE_1_at_0_309\">100</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(1, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(2436, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());


            //Win only with wilds in the middle reel. It should NOT win anything.
            sRandomPools =
                    "<randomPools>\n" +
                            "    <pool id=\"mainSpinReel921_1_at_0_99\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel922_1_at_0_98\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel923_1_at_0_99\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel924_1_at_0_98\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel925_1_at_0_99\">0</pool>\n" +
                            "    <pool id=\"mainSpinReel92RE_1_at_0_98\">1</pool>\n" +
                            "</randomPools>";
            randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

            sHandler.setRandomPools(randomPools);
            sHandler.processSpin(profile);

            //System.out.println(sHandler.getGameState().getReels().getReelsDisplay().toString());

            assertEquals(0, sHandler.getGameState().getSpinResults().getPaylineResults().size());
            assertEquals(2436, sHandler.getGameState().getCreditWonTotal().intValue());
            assertEquals(0, persistentState.getNumBonusSymbols().intValue());

        } catch(Throwable t) {
            t.printStackTrace();
            fail();
        }
    }

}