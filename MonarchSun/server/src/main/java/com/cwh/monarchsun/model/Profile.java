/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import nu.xom.*;

import java.util.HashMap;
import java.util.Map;

public class Profile {
    private final Map<String, String> parameters = new HashMap<String, String>();
    private Integer profileId     = null;
    private Integer accountId     = null;
    private String  currencyCode  = null;
    private Integer currencyScale = null;

    public Integer getAccountId() {
        return accountId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Integer getCurrencyScale() {
        return currencyScale;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public Integer getRTPLevel()
    {
        return Integer.valueOf(getParameter("level"));
    }

    public String getParameter(String paramName) {
        return this.parameters.get(paramName);
    }

    public Profile unmarshal(Node eProfile) {
        Element xProfile = (Element)eProfile;
        this.profileId     = new Integer(xProfile.getAttributeValue("profileId"));
        this.accountId     = new Integer(xProfile.getAttributeValue("accId"));
        this.currencyCode  = xProfile.getAttributeValue("currCode");
        this.currencyScale = new Integer(xProfile.getAttributeValue("currScale"));
        // Fill out Parameters
        Element xParam = xProfile.getFirstChildElement("param");
        for(int i = 0; i < xParam.getAttributeCount(); i++) {
            Attribute eAttribute = xParam.getAttribute(i);
            this.parameters.put(eAttribute.getLocalName(), eAttribute.getValue());
        }
        return this;
    }

    public Profile() {}

    // Needed for testing.
    public Profile(Map<String, String> parameters)
    {
        this.parameters.putAll(parameters);
    }
}