/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import com.cwh.sample.slotgame.model.GameState;
import com.cwh.sample.slotgame.model.PersistentState;
import com.cwh.sample.slotgame.model.RandomPools;
import com.cwh.sample.slotgame.model.SpinGameRequest;
import com.cwh.sample.slotgame.service.SlotService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This class will handle the SpinReq sent by the FlashClient.
 */
@UrlBinding("/SpinReq")
public class SpinGameRequestActionBean extends AbstractGameRequestActionBean {

    @DefaultHandler
    public Resolution processSpinRequest() {
        try {
            final SpinGameRequest gameRequest     = (SpinGameRequest)this.gdkRequest.getGameRequest();
            final GameState       gameState       = this.gdkRequest.getGameState();
            final PersistentState persistentState = this.gdkRequest.getPersistentState();
            final RandomPools     randomPools     = this.gdkRequest.getRandomPools();
            final SlotService     slotService     = new SlotService();
            // Its a new Init Request, setup the slotService and reset the GameState
            slotService.setPlayer(this.gdkRequest.getPlayer());
            slotService.setGameState(gameState);
            slotService.setPersistentState(persistentState);
            slotService.resetGameService(gameRequest);
            // Now Process the Spin Request
            slotService.processReelSpin(randomPools);
            // Generate any instructions associated with the spinRequest
            this.gdkResponse.addTransactions(slotService.generateInstructions(gameRequest));
            // Generate any messageResponses associated with the spinRequest
            this.gdkResponse.setGameResponse(slotService.generateGameResponse(gameRequest));
            // Set the ContentResponse data
            this.setContentResponse(this.gdkResponse.marshal().toXML().getBytes());
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Request: " + t, t);
        }
        return this.getResolution();
    }
    
}