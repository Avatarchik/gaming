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

import java.math.BigDecimal;
import java.util.List;

/**
 * This class will contain all values that the game client will require to
 * reset the game client to the last known playable state of a game.
 */
public class ResumeGameResponse extends GameResponse {
    private List<Reel>     reels             = null;
    private SpinResults    spinResults       = null;
    private Integer        numberSevensFound = null;
    private Integer        freeAwardTotal    = null;
    private Integer        freeAwardUsed     = null;
    private Integer        freeAwardWon      = null;
    private BigDecimal     coinValue         = null;
    private Integer        creditsPerLine    = null;

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

    public void setCoinValue(BigDecimal coinValue) {
        this.coinValue = coinValue;
    }

    public void setCreditsPerLine(Integer creditsPerLine) {
        this.creditsPerLine = creditsPerLine;
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

        Element eInitialWager = new Element("initialWager");
        eInitialWager.addAttribute(new Attribute("coinValue",      this.coinValue.toPlainString()));
        eInitialWager.addAttribute(new Attribute("creditsPerLine", this.creditsPerLine.toString()));
        eMsgdata.appendChild(eInitialWager);

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