/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import nu.xom.Element;
import nu.xom.Node;

import java.math.BigDecimal;

public class SpinGameRequest extends GameRequest {
    private BigDecimal coinValue      = null;
    private Integer    creditWager = null;

    public BigDecimal getCoinValue() {
        return coinValue;
    }

    public Integer getCreditWager() {
        return creditWager;
    }

    @Override
    public GameRequest unmarshal(Node eGameRequest) {
        Element xGameRequest = (Element)eGameRequest;
        this.requestName    = xGameRequest.getAttributeValue("reqName");
        this.coinValue      = new BigDecimal(xGameRequest.getFirstChildElement("coinValue").getValue().trim());
        this.creditWager = new Integer(xGameRequest.getFirstChildElement("creditWager").getValue().trim());
        return this;
    }

}