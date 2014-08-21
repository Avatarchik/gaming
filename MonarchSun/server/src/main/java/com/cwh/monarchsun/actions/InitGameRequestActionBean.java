/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.actions;

import com.cwh.monarchsun.model.InitGameResponse;
import com.cwh.monarchsun.model.Profile;
import   com.cwh.monarchsun.model.GameState;
import   com.cwh.monarchsun.model.InitGameRequest;
import   com.cwh.monarchsun.model.PersistentState;
import   com.cwh.monarchsun.service.SlotService;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This class will handle the InitReq sent by the FlashClient.
 */
@UrlBinding("/InitReq")
public class InitGameRequestActionBean extends AbstractGameRequestActionBean {

    @DefaultHandler
    public Resolution processSpinRequest() {
        try {
            final InitGameRequest gameRequest     = (InitGameRequest)this.gdkRequest.getGameRequest();
            final GameState       gameState       = this.gdkRequest.getGameState();
            final PersistentState persistentState = this.gdkRequest.getPersistentState();
            final Profile profile = this.gdkRequest.getProfile();
            final SlotService     slotService     = new SlotService();
            // Its a new Init Request, setup the slotService
            slotService.setPlayer(this.gdkRequest.getPlayer());
            gameState.setRtp(profile.getRTPLevel());
            slotService.setGameState(gameState);
            slotService.setPersistentState(persistentState);
            
            InitGameResponse initResponse = (InitGameResponse) slotService.generateGameResponse(gameRequest);
            initResponse.setRTPLevel(profile.getRTPLevel());
            
            // Generate any messageResponses associated with the initRequest
            this.gdkResponse.setGameResponse(initResponse);
            // Ensure that this is a NON-DESTRUCTIVE call to the state management.
            this.gdkResponse.setGameInfoOnly(true);
            // Set the ContentResponse data
            this.setContentResponse(this.gdkResponse.marshal().toXML().getBytes());
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Request: " + t, t);
        }
        return this.getResolution();
    }
    
}