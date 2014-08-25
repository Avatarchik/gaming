/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import com.cwh.sample.slotgame.model.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This action bean will process the incoming resume_game request. It will consume the incoming state, and
 * reconstruct a valid response that will allow the flash client to reset itself into the last known playable
 * state of the game.
 */
@UrlBinding("/resume_game")
public class ResumeGameActionBean extends AbstractGameRequestActionBean {

    @DefaultHandler
    public Resolution processResumeRequest() {
        try {
            final GameState          gameState       = this.gdkRequest.getGameState();
            final PersistentState    persistentState = this.gdkRequest.getPersistentState();
            // Now take the States and construct a Resume Response
            final ResumeGameResponse resumeResponse  = new ResumeGameResponse();
            resumeResponse.setRequestName("ResumeGameReq");
            resumeResponse.setCoinValue(gameState.getCreditValue());
            resumeResponse.setCreditsPerLine(gameState.getCreditPerLine());
            resumeResponse.setFreeAwardTotal(gameState.getFreeAwardsTotal());
            resumeResponse.setFreeAwardUsed(gameState.getFreeAwardsUsed());
            resumeResponse.setFreeAwardWon(gameState.getCreditFreeWonTotal());
            resumeResponse.setReels(gameState.getReels());
            resumeResponse.setSpinResults(gameState.getSpinResults());
            resumeResponse.setNumberSevensFound(persistentState.getNumBonusSymbols());

            RunningResult runningResult = new RunningResult();
            runningResult.setCreditsWagered(gameState.getCreditWager());
            runningResult.setCreditsWon(gameState.getCreditWonTotal());
            resumeResponse.setRunningResult(runningResult);

            // Now check to see if the game has ended. If so, then credit.
            if(gameState.getPlayState().equals(GameState.PLAY_STATE.FINISHED)) {
                SummaryResult summaryResult = new SummaryResult();
                summaryResult.setCreditsWagered(gameState.getCreditWager());
                summaryResult.setCreditsWon(gameState.getCreditWonTotal());
                resumeResponse.setSummaryResult(summaryResult);
            }

            // Generate any messageResponses associated with the resumeRequest            
            this.gdkResponse.setGameResponse(resumeResponse);
            this.gdkResponse.setGameState(null);
            this.gdkResponse.setPersistentState(null);
            // Set the ContentResponse data
            this.setContentResponse(this.gdkResponse.marshal().toXML().getBytes());
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Request: " + t, t);
        }
        return this.getResolution();
    }


    
}