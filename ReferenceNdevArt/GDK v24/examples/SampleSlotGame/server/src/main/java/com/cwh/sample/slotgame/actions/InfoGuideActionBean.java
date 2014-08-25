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

import java.util.HashMap;
import java.util.Map;

/**
 * This class will return a gaming guide. A HTML document.
 * The character set must be correctly set for the language requested.
 */
@UrlBinding("/gaming_guide")
public class InfoGuideActionBean extends BaseActionBean {
    private static final String              GUIDE_RESOURCE_PATH = "com/cwh/sample/slotgame/resources/guide/";
    private static final String              CONTENT_TYPE        = "text/html;charset=";
    private static final String              DEFAULT_CHARSET     = "utf-8";
    private static final String              DEFAULT_LANG        = "en";
    private static final String              DEFAULT_EXTENSION   = ".html";
    private static final Map<String, String> LANG_CHARSET        = new HashMap<String, String>(){{
        // Add to list the Langs that don't support UTF8
    }};

    private final ResourceLoader resourceLoader = new ResourceLoader();
    private       String         lang           = DEFAULT_LANG;

    /**
     * The Stripes Framework will set the LANG parameter if it is passed in as a Query Parameter
     * @param lang The Lang Code that represents a language.
     */
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getLang() {
        return lang;
    }

    @DefaultHandler
    public Resolution getGamingGuide() {
        // Set the CharSet to be used on the outgoing response
        final String charSet = this.retrieveCharSet(this.lang);
        this.setContentType(CONTENT_TYPE + charSet);
        // Retrieve the GamingGuide
        this.setContentResponse(this.retrieveGamingGuide(this.lang));
        return this.getResolution();
    }

    private byte[] retrieveGamingGuide(String lang) {
        byte[] guideResourceBytes = null;   
        try {
            final String resourcePath = GUIDE_RESOURCE_PATH + lang.toLowerCase() + DEFAULT_EXTENSION;
            // Generate the Resource Filepath and bytes
            this.logger.debug("GameGuide: [Lang: " + this.lang + "][Path: " + resourcePath + "]");
            guideResourceBytes = this.resourceLoader.getResourceAsBytes(resourcePath);
        } catch(Throwable t) {
            // On any error, return the english guide
            guideResourceBytes = this.retrieveGamingGuide(DEFAULT_LANG);
        }
        return guideResourceBytes;
    }

    private String retrieveCharSet(String lang) {
        String charset = DEFAULT_CHARSET;
        // Check to see if the language exists. If not, use the DefaultLanguage
        if(LANG_CHARSET.containsKey(lang)) {
            charset = LANG_CHARSET.get(lang);
        }
        // Figure out the Character set based on the Lang
        return charset;
    }


}