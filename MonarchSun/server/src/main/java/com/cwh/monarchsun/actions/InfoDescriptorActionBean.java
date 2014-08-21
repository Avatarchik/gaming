/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.actions;

import   com.cwh.monarchsun.utils.ResourceLoader;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This class will return the Game Descriptor. The Descriptor will be utilized to
 * help the CGS install the game into the system.
 */
@UrlBinding("/get_game_descriptor")
public class InfoDescriptorActionBean extends BaseActionBean {
    private static final String         DESC_RESOURCE_PATH = "com/cwh/monarchsun/resources/GameDescriptor.xml";
    private        final ResourceLoader resourceLoader     = new ResourceLoader();

    /**
     * This method is defined as the default method that will be executed.
     * As well, the gameDescriptor referenced will be loaded, and returned as the response.
     */
    @DefaultHandler
    public Resolution getGameDescriptor() {
        try {
            this.logger.debug("Game Descriptor resource: " + DESC_RESOURCE_PATH);
            byte[] aboutInfoBytes = this.resourceLoader.getResourceAsBytes(DESC_RESOURCE_PATH);
            // Set the response
            this.setContentResponse(aboutInfoBytes);
        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Game Descriptor Request: " + t, t);
        }
        return this.getResolution();
    }
}