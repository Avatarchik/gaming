/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.actions;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunRTP;
import   com.cwh.monarchsun.utils.ResourceLoader;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * This class will return an XML document that describes the RTP of the game.
 * This XML format is standardized and CANNOT be changed.
 *
 * Currently, this slot game was written with no intended RTP. So the RTP defined in the resource
 * is just a place holder.
 */
@UrlBinding("/about_info")
public class InfoAboutActionBean extends BaseActionBean {
    private static final String         ABOUT_RESOURCE_PATH = "com/cwh/monarchsun/resources/GameAboutInfo.xml";
    private        final ResourceLoader resourceLoader      = new ResourceLoader();

    // This is the RTP level requested. Since this Sample game only has paylevel, ignoring this.
    private String level;

    /**
     * The RTP Level is not a concern. This is here only as a place holder, as Stripes will set this value if passed in.
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        if (this.level == null)
            this.level = "2";
        return level;
    }

    @DefaultHandler
    public Resolution getAboutInformation() {
    	try {
            this.logger.debug("AboutInfo resource: " + ABOUT_RESOURCE_PATH);
            String aboutInfoString = new String(this.resourceLoader.getResourceAsBytes(ABOUT_RESOURCE_PATH));

            aboutInfoString = aboutInfoString.replace("${level}", getLevel());
            aboutInfoString = aboutInfoString.replace("${percentageStr}",
            		MonarchSunRTP.getRTPString(Integer.valueOf(getLevel())));
            this.setContentResponse(aboutInfoString.getBytes());

        } catch(Throwable t) {
            throw new RuntimeException("Unable to process the Request: " + t, t);
        }
        return this.getResolution();
    }
}