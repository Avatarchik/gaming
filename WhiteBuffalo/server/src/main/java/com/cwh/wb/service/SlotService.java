/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.service;

import   com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;
import   com.cwh.wb.model.*;
import   com.cwh.wb.utils.GameRounding;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * This class just provides the Slot game with the logical components of the game.
 * There is nothing standard or recommended in the structure of the service/engine.
 * This was developed to quickly to process a spin.
 *
 */
public class SlotService {
    private Player          player          = null;
    private GameState       gameState       = null;
    private PersistentState persistentState = null;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPersistentState(PersistentState persistentState) {
        this.persistentState = persistentState;
    }

    /**
     * The GameState requires to be reset to a start state whenever a SpinGameRequest is sent
     * @param gameRequest The SpinGameRequest. Any other request is ignored.
     */
    public void resetGameService(GameRequest gameRequest) {
        // This only means 1 thing. Its a SpinGameRequest
        if(gameRequest instanceof SpinGameRequest) {
            final SpinGameRequest spinGameRequest = (SpinGameRequest)gameRequest;
            this.gameState.setCreditValue(spinGameRequest.getCoinValue());
            this.gameState.setCreditWager(spinGameRequest.getCreditWager());

            this.gameState.setCreditWonTotal(0);
            this.gameState.setPlayState(GameState.PLAY_STATE.NEW);
        }
    }

    /**
     * This method will generate rGF Instructions for a given gameRequest.
     * Each gameRequest will have its own set of instructions that will be required to be executed.
     *
     * @param  gameRequest The gameRequest that will be utilized to generate the instructions.
     * @return List<Transation>. A list of transactions that will be added into the response.
     */
    public List<Transaction> generateInstructions(GameRequest gameRequest) {
        List<Transaction> transactions = new LinkedList<Transaction>();
        // Basic IF statement as there shouldnt be too many types of incoming gameRequests
        if(gameRequest instanceof SpinGameRequest) {
            final SpinGameRequest spinGameRequest = (SpinGameRequest)gameRequest;

            Integer creditWager = WhiteBuffaloConfig.COST_TO_COVER * spinGameRequest.getCreditWager();
            this.gameState.setCreditWager(spinGameRequest.getCreditWager());

            // Its a Spin Request. If so, then debit.
            Transaction debitTrans = new Transaction();
            debitTrans.setAction(Transaction.TYPE.BET);
            debitTrans.setPlayerId(this.player.getPlayerId());
            debitTrans.setAmount(spinGameRequest.getCoinValue().multiply(new BigDecimal(creditWager)));
            transactions.add(debitTrans);
        }

        // Now check to see if the game has ended. If so, then credit.
        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FINISHED)) {
            Transaction creditTrans = new Transaction();
            creditTrans.setAction(Transaction.TYPE.RETURN);
            creditTrans.setPlayerId(this.player.getPlayerId());
            creditTrans.setAmount(this.gameState.getCreditValue().multiply(new BigDecimal(this.gameState.getCreditWonTotal()))/*.multiply(new BigDecimal(this.gameState.getCreditPerLine()))*/);
            transactions.add(creditTrans);
        }
        return transactions;
    }

    /**
     * This method will generate the GameResponse per GameRequest. Each has its own custom response.
     * @param gameRequest The gameRequest that will be utilized to generate the response.
     * @return GameResponse. The completed and filled response object - based on the request.
     */
    public GameResponse generateGameResponse(GameRequest gameRequest) {
        GameResponse gameResponse = null;
        // Basic IF statement as there shouldnt be too many types of incoming gameRequests
        if(gameRequest instanceof InitGameRequest) {
            gameResponse = new InitGameResponse();
            // Generate the Response to the Client
        } else if(gameRequest instanceof SpinGameRequest || gameRequest instanceof FreeGameRequest) {
            gameResponse = new SpinGameResponse();
            // Generate the Response to the Client
            ((SpinGameResponse)gameResponse).setReelDisplay(this.gameState.getReelDisplay());
            ((SpinGameResponse)gameResponse).setStopPositions(this.gameState.getStopPositions());
            ((SpinGameResponse)gameResponse).setRePattern(this.gameState.getReplacementPattern());
            ((SpinGameResponse)gameResponse).setSpinResults(this.gameState.getSpinResults());
            ((SpinGameResponse)gameResponse).setFreeAwardTotal(this.gameState.getFreeAwardsTotal());
            ((SpinGameResponse)gameResponse).setFreeAwardUsed(this.gameState.getFreeAwardsUsed());
            ((SpinGameResponse)gameResponse).setFreeAwardWon(this.gameState.getCreditFreeWonTotal());

            RunningResult runningResult = new RunningResult();
            runningResult.setCreditsWagered(this.gameState.getCreditWager()*WhiteBuffaloConfig.COST_TO_COVER);
            runningResult.setCreditsWon(this.gameState.getCreditWonTotal());
            gameResponse.setRunningResult(runningResult);
        }

        // Now check to see if the game has ended. If so, then credit.
        if(this.gameState.getPlayState().equals(GameState.PLAY_STATE.FINISHED)) {
            SummaryResult summaryResult = new SummaryResult();
            summaryResult.setWagered((this.gameState.getCreditValue().multiply(new BigDecimal(this.gameState.getCreditWager()))).multiply(new BigDecimal(WhiteBuffaloConfig.COST_TO_COVER)));
            summaryResult.setWon(this.gameState.getCreditValue().multiply(new BigDecimal(this.gameState.getCreditWonTotal())));
            gameResponse.setSummaryResult(summaryResult);
        }
        gameResponse.setRequestName(gameRequest.getRequestName());
        return gameResponse;
    }

    /**
     * This method will process a spin request. This entails generating a set of reels, and evaluating
     * the winning conditions.
     * @param randomPools The random pool to utilize.
     */
    public void processReelSpin(RandomPools randomPools, Profile profile) {
        final SpinHandler spinHandler = new SpinHandler();
        spinHandler.setGameState(gameState);
        spinHandler.setPersistentState(persistentState);
        spinHandler.setRandomPools(randomPools);
        spinHandler.processSpin(profile);
    }
}