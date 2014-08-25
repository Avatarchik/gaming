/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import com.cwh.sample.slotgame.utils.GameRounding;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

public class SummaryResult {
    private Integer creditsWon     = null;
    private Integer creditsWagered = null;

    public void setCreditsWagered(Integer creditsWagered) {
        this.creditsWagered = creditsWagered;
    }

    public void setCreditsWon(Integer creditsWon) {
        this.creditsWon = creditsWon;
    }

    public Node marshal() {
        Element eResults = new Element("results");
        eResults.addAttribute(new Attribute("wagered", GameRounding.shiftForCurrency(this.creditsWagered).toPlainString()));
        eResults.addAttribute(new Attribute("won",     GameRounding.shiftForCurrency(this.creditsWon).toPlainString()));
        eResults.addAttribute(new Attribute("net",     GameRounding.shiftForCurrency(this.creditsWon - this.creditsWagered).toPlainString()));
        return eResults;
    }
}