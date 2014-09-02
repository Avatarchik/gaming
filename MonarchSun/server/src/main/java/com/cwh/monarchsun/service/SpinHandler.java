/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.service;

import com.cwh.slotstoolbox2.SlotsRandom;
import com.cwh.slotstoolbox2.reels.Reels;
import com.cwh.slotstoolbox2.reels.ReelsConfig;
import   com.cwh.monarchsun.MonarchSunConfig.*;
import com.cwh.monarchsun.model.*;

import org.apache.log4j.Logger;
import org.apache.xalan.xsltc.compiler.sym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will handle the spinning request. Generating the reels and evaluating the results.
 */
public class SpinHandler {

    protected final Logger logger;
    public SpinHandler()
    {
        this.logger = Logger.getLogger(this.getClass());
    }

    // Base Game
    protected static ReelsConfig mainReelsConfig = new MonarchSunReelsConfig();
    protected static REReelsConfig mainREReelsConfig = new MonarchSunREReelsConfig();
    protected static MonarchSunPayTableConfig mainPayConfig = new MonarchSunPayTableConfig();
    
    // Free Game
    protected static ReelsConfig freeSpinReelsConfig = new MonarchSunFreeSpinsReelsConfig();
    protected static REReelsConfig freeSpinREReelsConfig = new MonarchSunREFreeSpinsReelsConfig();
    protected static MonarchSunPayTableConfig freeSpinPayConfig = new MonarchSunPayTableConfig();
    
    private GameState       gameState       = null;
    private RandomPools     randomPools     = null;
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setPersistentState(PersistentState persistentState) {
    }

    public void setRandomPools(RandomPools randomPools) {
        this.randomPools = randomPools;
    }

    public void processSpin(Profile profile)
    {
        Map<Integer, SlotsRandom> random = createMonarchSunRandomPoolMap(profile.getRTPLevel());

        ReelsConfig reelsConfig = null;
        REReelsConfig reReelsConfig = null;
        MonarchSunPayTableConfig paylinesConfig = null;

        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))
        {
            reelsConfig = freeSpinReelsConfig;
            reReelsConfig = freeSpinREReelsConfig;
            paylinesConfig = freeSpinPayConfig;
        }
        else
        {
            reelsConfig = mainReelsConfig;
            reReelsConfig = mainREReelsConfig;
            paylinesConfig = mainPayConfig;
        }

        // spins reels
        Reels reels = new Reels(reelsConfig, MonarchSunRTP.rtp(profile.getRTPLevel()), random);
        //Bets bets = Bets.newBetOnAllLines(gameState.getCreditPerLine(), 1, MonarchSunConfig.MAX_COIN_PER_LINE, MonarchSunConfig.NUMBER_OF_LINES);
        int bet = gameState.getCreditWager()* MonarchSunConfig.COST_TO_COVER; //Change Game State
        List<List<String>> symbolDisplay = reels.getReelsDisplay();
        
        //Replace all RE symbols in the symbolDisplay
        List<String> rePattern = reReelsConfig.getPattern(MonarchSunRTP.rtp(profile.getRTPLevel()), createMonarchSunRERandomPool(profile.getRTPLevel()).nextSeq());
        symbolDisplay = MonarchSunREEvaluator.EvaluateRE(rePattern, symbolDisplay);

        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))//replace sticky wilds if there's some.
        {
            symbolDisplay = MonarchSunEvaluator.AddNReplaceStickyWilds(symbolDisplay,gameState);
        }
        else
        {
            //If is NEW game StickyWilds should be null
            gameState.setStickyWilds(new ArrayList<Integer>());
        }
        this.gameState.setReelDisplay(symbolDisplay);
        
        List<PowerXStreamPaylineResult> paylineResults = MonarchSunEvaluator.EvaluateL2R(paylinesConfig, symbolDisplay, bet, gameState.isMaxBet(), logger);
        paylineResults.addAll(MonarchSunEvaluator.EvaluateR2L(paylinesConfig, symbolDisplay, bet, logger));

        //Evaluate scatter wins
        PowerXStreamPaylineResult scatterWin = MonarchSunBonusEvaluator.ScatterEvaluate(symbolDisplay, MonarchSunConfig.FREE_SPINS_AWARDED, MonarchSunConfig.SYMBOL_BN, logger);
        //Add scatter win, if any, to results
        if (scatterWin != null)
        {
            paylineResults.add(scatterWin);
        }

        // Update Game State
        this.gameState.setReelDisplay(symbolDisplay);
        this.gameState.setStopPositions(reels.getStopPositions());
        this.gameState.setReels(reels);
        this.gameState.setReplacementPattern(rePattern);
        SpinResults spinResults = new SpinResults();
        spinResults.addPaylineResult(paylineResults);
        this.gameState.setSpinResults(spinResults);
        this.gameState.setCreditWonTotal(this.gameState.getCreditWonTotal() + spinResults.getCreditsWonTotal());
        
        //Add free spins if there is a scatter win
        if (scatterWin != null)
        {
            this.gameState.setFreeAwardsTotal(this.gameState.getFreeAwardsTotal() + scatterWin.freeSpinsWon());
        }

        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE)) {
            this.gameState.setCreditFreeWonTotal(this.gameState.getCreditFreeWonTotal() + spinResults.getCreditsWonTotal());
        }
        
        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.NEW)) {
        	//On each base game play overwrite base game stops
        	this.gameState.setBaseGameStops(reels.getStopPositions());
        }

        // Check FreeAward and State
        this.gameState.setFreeAwardsUsed(this.checkFreeFeatureUsed());
        this.gameState.setPlayState(this.determineStateTransition());
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

    /**
     * Set up for variable length reels
     *
     * @return
     */
    protected Map<Integer, SlotsRandom> createMonarchSunRandomPoolMap(int rtp)
    {
        Map<Integer, SlotsRandom> map = new HashMap<Integer, SlotsRandom>();

        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))
        {
            map.put(1,  randomPools.pool("freeSpinReel1_1_at_0_99"));
            map.put(2,  randomPools.pool("freeSpinReel2_1_at_0_99"));
            map.put(3,  randomPools.pool("freeSpinReel3_1_at_0_99"));
            map.put(4,  randomPools.pool("freeSpinReel4_1_at_0_99"));
            map.put(5,  randomPools.pool("freeSpinReel5_1_at_0_99"));
        }
        else
        {
            switch(rtp)
            {
                /*USE 92% CONFIG*/
                case 1:
                    map.put(1,  randomPools.pool("mainSpinReel921_1_at_0_99"));
                    map.put(2,  randomPools.pool("mainSpinReel922_1_at_0_98"));
                    map.put(3,  randomPools.pool("mainSpinReel923_1_at_0_99"));
                    map.put(4,  randomPools.pool("mainSpinReel924_1_at_0_98"));
                    map.put(5,  randomPools.pool("mainSpinReel925_1_at_0_99"));
                    break;
                /*USE 94% CONFIG*/
                case 2:
                    map.put(1,  randomPools.pool("mainSpinReel941_1_at_0_99"));
                    map.put(2,  randomPools.pool("mainSpinReel942_1_at_0_97"));
                    map.put(3,  randomPools.pool("mainSpinReel943_1_at_0_99"));
                    map.put(4,  randomPools.pool("mainSpinReel944_1_at_0_97"));
                    map.put(5,  randomPools.pool("mainSpinReel945_1_at_0_99"));
                    break;
                /*USE 95% CONFIG*/
                case 3:
                    map.put(1,  randomPools.pool("mainSpinReel951_1_at_0_99"));
                    map.put(2,  randomPools.pool("mainSpinReel952_1_at_0_97"));
                    map.put(3,  randomPools.pool("mainSpinReel953_1_at_0_99"));
                    map.put(4,  randomPools.pool("mainSpinReel954_1_at_0_97"));
                    map.put(5,  randomPools.pool("mainSpinReel955_1_at_0_99"));
                    break;
            }

        }
        return map;
    }
    
    protected SlotsRandom createMonarchSunRERandomPool(int rtp) {
    	if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))
        {
    		return randomPools.pool("freeSpinReelRE_1_at_0_309");
        } else {
            if(rtp==1)
            {
                return randomPools.pool("mainSpinReel92RE_1_at_0_98");
            }
            if(rtp==2)
            {
                return randomPools.pool("mainSpinReel94RE_1_at_0_98");
            }
            if(rtp==3)
            {
                return randomPools.pool("mainSpinReel95RE_1_at_0_98");
            }
            else//default is 92%
            {
                return randomPools.pool("mainSpinReel92RE_1_at_0_98");
            }
        }
    }
}