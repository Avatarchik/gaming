/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.actions;

import com.cwh.sample.slotgame.utils.ResourceLoader;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/initialize_game")
public class InfoInitializerActionBean extends BaseActionBean {
    private static final String         INIT_RESOURCE_PATH = "com/cwh/sample/slotgame/resources/GameInitializer.xml";
    private        final ResourceLoader resourceLoader     = new ResourceLoader();

    /**
     * This method is defined as the default method that will be executed.
     * As well, the gameDescriptor referenced will be loaded, and returned as the response.
     */
    @DefaultHandler
    public Resolution getGameDescriptor() {
        try {
            this.logger.debug("Game INIT_RESOURCE_PATH resource: " + INIT_RESOURCE_PATH);
            byte[] aboutInfoBytes = this.resourceLoader.getResourceAsBytes(INIT_RESOURCE_PATH);
            // Set the response
            this.setContentResponse(aboutInfoBytes);
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Game INIT_RESOURCE_PATH Request: " + t, t);
        }
        return this.getResolution();
    }
}