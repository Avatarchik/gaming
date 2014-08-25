/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Element;
import nu.xom.Node;

import java.math.BigDecimal;

public class SpinGameRequest extends GameRequest {
    private BigDecimal coinValue      = null;
    private Integer    creditsPerLine = null;

    public BigDecimal getCoinValue() {
        return coinValue;
    }

    public Integer getCreditsPerLine() {
        return creditsPerLine;
    }

    @Override
    public GameRequest unmarshal(Node eGameRequest) {
        Element xGameRequest = (Element)eGameRequest;
        this.requestName    = xGameRequest.getAttributeValue("reqName");
        this.coinValue      = new BigDecimal(xGameRequest.getFirstChildElement("coinValue").getValue().trim());
        this.creditsPerLine = new Integer(xGameRequest.getFirstChildElement("creditsPerLine").getValue().trim());
        return this;
    }

}