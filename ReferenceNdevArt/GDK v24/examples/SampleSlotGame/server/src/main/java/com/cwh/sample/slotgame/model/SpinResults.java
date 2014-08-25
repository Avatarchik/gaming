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
import nu.xom.Nodes;

import java.util.LinkedList;
import java.util.List;

public class SpinResults {
    private Integer             creditsWonTotal  = 0;
    private List<PaylineResult> paylineResults   = new LinkedList<PaylineResult>();

    public void addPaylineResult(PaylineResult paylineResult) {
        this.paylineResults.add(paylineResult);
        this.creditsWonTotal += paylineResult.getCreditsWon();
    }

    public List<PaylineResult> getPaylineResults() {
        return paylineResults;
    }

    public Integer getCreditsWonTotal() {
        return creditsWonTotal;
    }

    public SpinResults unmarshal(Node eLines) {
        this.creditsWonTotal = new Integer(((Element)eLines).getAttributeValue("won"));
        Nodes nPaylines = eLines.query("line");
        for(int i = 0; i < nPaylines.size(); i++) {
            this.paylineResults.add(new PaylineResult().unmarshal(nPaylines.get(i)));
        }
        return this;
    }

    public Node marshal() {
        Element eLines = new Element("lines");
        eLines.addAttribute(new Attribute("won", this.creditsWonTotal.toString()));
        for(PaylineResult payline : this.paylineResults) {
            eLines.appendChild(payline.marshal());
        }
        return eLines;
    }

}