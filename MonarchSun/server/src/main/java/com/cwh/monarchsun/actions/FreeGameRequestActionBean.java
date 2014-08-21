/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.actions;

import com.cwh.monarchsun.model.GameState.PLAY_STATE;
import com.cwh.monarchsun.service.Validator;
import   com.cwh.monarchsun.model.FreeGameRequest;
import   com.cwh.monarchsun.model.GameState;
import   com.cwh.monarchsun.model.PersistentState;
import com.cwh.monarchsun.model.Profile;
import   com.cwh.monarchsun.model.RandomPools;
import   com.cwh.monarchsun.service.SlotService;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This class will handle the SpinReq sent by the FlashClient.
 */
@UrlBinding("/FreeReq")
public class FreeGameRequestActionBean extends AbstractGameRequestActionBean {

	@DefaultHandler
	public Resolution processFreeRequest() {
		try {
			final FreeGameRequest gameRequest     = (FreeGameRequest)this.gdkRequest.getGameRequest();
			final GameState           gameState       = this.gdkRequest.getGameState();
			final PersistentState     persistentState = this.gdkRequest.getPersistentState();
			final RandomPools         randomPools     = this.gdkRequest.getRandomPools();
			final SlotService         slotService     = new SlotService();
			final Profile profile = this.gdkRequest.getProfile();
			if(Validator.ValidateState(gameState.getPlayState(), PLAY_STATE.FREE)) {
				// Validate that the incoming request has the same info as the state
				if(!gameRequest.getFreeGameUsed() .equals(gameState.getFreeAwardsUsed()) ||
						!gameRequest.getFreeGameTotal().equals(gameState.getFreeAwardsTotal())) {
					throw new RuntimeException("State and Request data inconsistent"); 
				}
				// Its a Free Request, setup the slotService
				slotService.setPlayer(this.gdkRequest.getPlayer());
				gameState.setRtp(profile.getRTPLevel());
				slotService.setGameState(gameState);
				slotService.setPersistentState(persistentState);
				// Now Process the Spin Request
				slotService.processReelSpin(randomPools, this.gdkRequest.getProfile());
				// Generate any instructions associated with the spinRequest
				this.gdkResponse.addTransactions(slotService.generateInstructions(gameRequest));
				// Generate any messageResponses associated with the spinRequest
				this.gdkResponse.setGameResponse(slotService.generateGameResponse(gameRequest));
				// Set the ContentResponse data
				this.setContentResponse(this.gdkResponse.marshal().toXML().getBytes());
			}  else {
				this.gdkResponse.setGameError("Missmatch of Play State");
				this.setContentResponse(this.gdkResponse.marshal().toXML().getBytes());
			}
		} catch(Throwable t) {
			throw new RuntimeException("Unable to process the Request: " + t, t);
		}
		return this.getResolution();
	}
}