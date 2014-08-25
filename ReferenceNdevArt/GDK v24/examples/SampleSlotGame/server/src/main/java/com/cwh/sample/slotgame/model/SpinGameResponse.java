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

import java.util.List;

public class SpinGameResponse extends GameResponse {
    private List<Reel>     reels             = null;
    private SpinResults    spinResults       = null;
    private Integer        numberSevensFound = null;
    private Integer        freeAwardTotal    = null;
    private Integer        freeAwardUsed     = null;
    private Integer        freeAwardWon      = null;

    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }

    public void setSpinResults(SpinResults spinResults) {
        this.spinResults = spinResults;
    }

    public void setNumberSevensFound(Integer numberSevensFound) {
        this.numberSevensFound = numberSevensFound;
    }

    public void setFreeAwardTotal(Integer freeAwardTotal) {
        this.freeAwardTotal = freeAwardTotal;
    }

    public void setFreeAwardUsed(Integer freeAwardUsed) {
        this.freeAwardUsed = freeAwardUsed;
    }

    public void setFreeAwardWon(Integer freeAwardWon) {
        this.freeAwardWon = freeAwardWon;
    }

    @Override
    public Node marshal() {
        Element eMsgdata = new Element("msgdata");
        eMsgdata.addAttribute(new Attribute("reqName", this.requestName));

        Element eReels        = new Element("reels");
        for(Reel aReel : this.reels) {
            eReels.appendChild(aReel.marshal());
        }
        eMsgdata.appendChild(eReels);

        Element eLineResults  = new Element("lines");
        eLineResults.addAttribute(new Attribute("won", this.spinResults.getCreditsWonTotal().toString()));
        for(PaylineResult payLine : this.spinResults.getPaylineResults()) {
            eLineResults.appendChild(payLine.marshal());
        }
        eMsgdata.appendChild(eLineResults);

        eMsgdata.appendChild(this.runningResult.marshal());
        
        if(this.summaryResult != null) {
            eMsgdata.appendChild(this.summaryResult.marshal());
        }

        Element eStars = new Element("stars");
        eStars.addAttribute(new Attribute("total", this.numberSevensFound.toString()));
        eMsgdata.appendChild(eStars);

        Element eFreeSpins = new Element("freeSpins");
        eFreeSpins.addAttribute(new Attribute("total", this.freeAwardTotal.toString()));
        eFreeSpins.addAttribute(new Attribute("used",  this.freeAwardUsed.toString()));
        eFreeSpins.addAttribute(new Attribute("won",   this.freeAwardWon.toString()));
        eMsgdata.appendChild(eFreeSpins);

        return eMsgdata;
    }
}