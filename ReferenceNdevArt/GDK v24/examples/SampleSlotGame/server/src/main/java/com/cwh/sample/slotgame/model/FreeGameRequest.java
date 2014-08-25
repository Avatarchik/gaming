/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Element;
import nu.xom.Node;

/**
 * This class will represent the incoming Free Spin request. The values container within the request are strictly
 * used to validate against whats in the State.
 */
public class FreeGameRequest extends GameRequest {
    private Integer freeGameTotal = null;
    private Integer freeGameUsed  = null;

    public Integer getFreeGameUsed() {
        return freeGameUsed;
    }

    public Integer getFreeGameTotal() {
        return freeGameTotal;
    }

    @Override
    public GameRequest unmarshal(Node eGameRequest) {
        Element xGameRequest = (Element)eGameRequest;
        this.requestName  = xGameRequest.getAttributeValue("reqName");
        this.freeGameTotal = new Integer(xGameRequest.getFirstChildElement("freeSpins").getAttributeValue("total").trim());
        this.freeGameUsed  = new Integer(xGameRequest.getFirstChildElement("freeSpins").getAttributeValue("used").trim());
        return this;
    }
}