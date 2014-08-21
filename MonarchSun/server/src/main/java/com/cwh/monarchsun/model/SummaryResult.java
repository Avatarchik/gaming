/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import java.math.BigDecimal;

import   com.cwh.monarchsun.utils.GameRounding;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

public class SummaryResult {
    private BigDecimal Won     = null;
    private BigDecimal Wagered = null;

    public void setWagered(BigDecimal wagered) {
        this.Wagered = wagered;
    }

    public void setWon(BigDecimal won) {
        this.Won = won;
    }

    public Node marshal() {
        Element eResults = new Element("results");
        eResults.addAttribute(new Attribute("wagered", this.Wagered.toPlainString()));
        eResults.addAttribute(new Attribute("won",     this.Won.toPlainString()));
        eResults.addAttribute(new Attribute("net",     this.Won.subtract(this.Wagered).toPlainString()));
        return eResults;
    }
}