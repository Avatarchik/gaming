/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package  com.cwh.monarchsun.actions;

import  com.cwh.monarchsun.model.GdkRequest;
import  com.cwh.monarchsun.model.GdkResponse;
import  com.cwh.monarchsun.utils.ThreadSession;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import nu.xom.Builder;
import nu.xom.Document;

import java.io.StringReader;

/**
 * This class just wraps up some common elements for any Game Play Request.
 * It utilizes Stripes' lifecycle to pre and post process the requests.
 */
@UrlBinding("/GDMGameReq")
public class AbstractGameRequestActionBean extends BaseActionBean {
    protected GdkRequest  gdkRequest  = null;
    protected GdkResponse gdkResponse = null;

    /**
     * This method will execute just before the ActionBean is executed.
     * This will setup the gdkRequest and gdkResponse for usage in the ActionBean
     */
    @Before(stages= LifecycleStage.EventHandling)
    private void unmarshalGdkRequest() {
        try {
            final String sRequest = new String(this.getContentRequest());
            logger.debug("Incoming Request: " + sRequest);
            Document xRequest = new Builder().build(new StringReader(sRequest));
            // Take the incoming request, and parse into a GdkRequest object
            this.gdkRequest  = new GdkRequest().unmarshal(xRequest.getRootElement());
            // Instantiate a GDK Response and link up common objects
            this.gdkResponse = new GdkResponse();
            this.gdkResponse.setGameState(this.gdkRequest.getGameState());
            this.gdkResponse.setPersistentState(this.gdkRequest.getPersistentState());
            // Setup the ThreadSession
            ThreadSession.getCurrentSession().reset();
            if(this.gdkRequest.getProfile() != null) {
                ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_CODE,  this.gdkRequest.getProfile().getCurrencyCode());
                ThreadSession.getCurrentSession().add(ThreadSession.KEYS.CURR_SCALE, this.gdkRequest.getProfile().getCurrencyScale().toString());
            }
        } catch(Throwable t) {
            throw new RuntimeException("Unable to unmarshal the incoming GdkRequest: " + t, t);
        }
    }

    /**
     * This method will cleanup the ThreadSession. Resetting the stored keys.
     */
    @After(stages=LifecycleStage.EventHandling)
    private void cleanupGameLogic() {
        try {
            ThreadSession.getCurrentSession().reset();
        } catch(Throwable t) {
            throw new RuntimeException("Unable to cleanup after the game logic: " + t, t);
        }
    }
}