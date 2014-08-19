/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.service;

import com.cwh.slotstoolbox2.SlotsRandom;
import com.cwh.slotstoolbox2.reels.Reel;
import com.cwh.slotstoolbox2.reels.Reels;
import com.cwh.slotstoolbox2.reels.ReelsConfig;
import com.cwh.slotstoolbox2.scatter.ScatterConfig;
import   com.cwh.wb.WhiteBuffaloConfig.*;
import com.cwh.wb.model.*;

import nu.xom.Element;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
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
    protected static ReelsConfig mainReelsConfig = new WhiteBuffaloReelsConfig();
    protected static REReelsConfig mainREReelsConfig = new WhiteBuffaloREReelsConfig();
    protected static WhiteBuffaloPayTableConfig mainPayConfig = new WhiteBuffaloPayTableConfig();
    
    // Free Game
    protected static ReelsConfig freeSpinReelsConfig = new WhiteBuffaloFreeSpinsReelsConfig();
    protected static REReelsConfig freeSpinREReelsConfig = new WhiteBuffaloREFreeSpinsReelsConfig();
    protected static WhiteBuffaloPayTableConfig freeSpinPayConfig = new WhiteBuffaloPayTableConfig();
    
    private GameState       gameState       = null;
    private RandomPools     randomPools     = null;
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPersistentState(PersistentState persistentState) {
    }

    public void setRandomPools(RandomPools randomPools) {
        this.randomPools = randomPools;
    }

    public void processSpin(Profile profile)
    {
        Map<Integer, SlotsRandom> random = createWhiteBuffaloRandomPoolMap();

        ReelsConfig reelsConfig = null;
        REReelsConfig reReelsConfig = null;
        WhiteBuffaloPayTableConfig paylinesConfig = null;

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
        Reels reels = new Reels(reelsConfig, WhiteBuffaloRTP.rtp(profile.getRTPLevel()), random);
        //Bets bets = Bets.newBetOnAllLines(gameState.getCreditPerLine(), 1, WhiteBuffaloConfig.MAX_COIN_PER_LINE, WhiteBuffaloConfig.NUMBER_OF_LINES);
        int bet = gameState.getCreditWager()*WhiteBuffaloConfig.COST_TO_COVER; //Change Game State
        List<List<String>> symbolDisplay = reels.getReelsDisplay();
        
        //Replace all RE symbols in the symbolDisplay
        List<String> rePattern = reReelsConfig.getPattern(WhiteBuffaloRTP.rtp(profile.getRTPLevel()), createWhiteBuffaloRERandomPool().nextSeq());
        symbolDisplay = WhiteBuffaloREEvaluator.EvaluateRE(rePattern, symbolDisplay);
        
        List<PowerXStreamPaylineResult> paylineResults = WhiteBuffaloEvaluator.EvaluateL2R(paylinesConfig, symbolDisplay, bet, gameState.isMaxBet(), logger);
        paylineResults.addAll(WhiteBuffaloEvaluator.EvaluateR2L(paylinesConfig, symbolDisplay, bet, logger));
        
        //Evaluate scatter wins
        PowerXStreamPaylineResult scatterWin = WhiteBuffaloBonusEvaluator.ScatterEvaluate(symbolDisplay, WhiteBuffaloConfig.FREE_SPINS_AWARDED, WhiteBuffaloConfig.SYMBOL_BONUS, logger);
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
    protected Map<Integer, SlotsRandom> createWhiteBuffaloRandomPoolMap()
    {
        Map<Integer, SlotsRandom> map = new HashMap<Integer, SlotsRandom>();

        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))
        {
            map.put(1,  randomPools.pool("freeSpinReel1_1_at_0_99"));
            map.put(2,  randomPools.pool("freeSpinReel2_1_at_0_199"));
            map.put(3,  randomPools.pool("freeSpinReel3_1_at_0_199"));
            map.put(4,  randomPools.pool("freeSpinReel4_1_at_0_199"));
            map.put(5,  randomPools.pool("freeSpinReel5_1_at_0_99"));
        }
        else
        {
            map.put(1,  randomPools.pool("mainSpinReel1_1_at_0_129"));
            map.put(2,  randomPools.pool("mainSpinReel2_1_at_0_199"));
            map.put(3,  randomPools.pool("mainSpinReel3_1_at_0_199"));
            map.put(4,  randomPools.pool("mainSpinReel4_1_at_0_199"));
            map.put(5,  randomPools.pool("mainSpinReel5_1_at_0_149"));
        }
        return map;
    }
    
    protected SlotsRandom createWhiteBuffaloRERandomPool() {
    	if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FREE))
        {
    		return randomPools.pool("freeSpinReelRE_1_at_0_399");
        } else {
        	return randomPools.pool("mainSpinReelRE_1_at_0_499");
        }
    }
}