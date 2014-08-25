/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

public class PaylineResult {
    private Integer creditsWon = 0;
    private String  positions  = null;
    private String  iconWon    = null;
    private Integer paylineId  = null;

    public Integer getCreditsWon() {
        return creditsWon;
    }

    public void setCreditsWon(Integer creditsWon) {
        this.creditsWon = creditsWon;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public void setIconWon(String iconWon) {
        this.iconWon = iconWon;
    }

    public void setPaylineId(Integer paylineId) {
        this.paylineId = paylineId;
    }

    public PaylineResult unmarshal(Node ePaylineResults) {
        this.paylineId  = new Integer(((Element)ePaylineResults).getAttributeValue("id")) -1;
        this.creditsWon = new Integer(((Element)ePaylineResults).getAttributeValue("won"));
        this.iconWon    = ((Element)ePaylineResults).getAttributeValue("icon");
        this.positions  = ((Element)ePaylineResults).getAttributeValue("positions");
        return this;
    }

    public Node marshal() {
        Element ePayline = new Element("line");
        ePayline.addAttribute(new Attribute("id",        String.valueOf(this.paylineId + 1)));
        ePayline.addAttribute(new Attribute("won",       this.creditsWon.toString()));
        ePayline.addAttribute(new Attribute("icon",      this.iconWon));
        ePayline.addAttribute(new Attribute("positions", this.positions));
        return ePayline;
    }
}