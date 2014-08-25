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

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class GameState {
    public static enum PLAY_STATE{NEW, FREE, FINISHED}

    private PLAY_STATE playState          = PLAY_STATE.NEW;
    private BigDecimal creditValue        = new BigDecimal(0);
    private Integer    creditWager        = 0;
    private Integer    creditPerLine      = 0;
    private Integer    creditWonTotal     = 0;
    private Integer    creditFreeWonTotal = 0;
    private Integer    freeAwardsUsed     = 0;
    private Integer    freeAwardsTotal    = 0;

    private SpinResults spinResults = null;
    private List<Reel>  reels       = null;


    public Integer getCreditPerLine() {
        return creditPerLine;
    }

    public BigDecimal getCreditValue() {
        return creditValue;
    }

    public Integer getCreditWager() {
        return creditWager;
    }

    public Integer getCreditWonTotal() {
        return creditWonTotal;
    }

    public Integer getCreditFreeWonTotal() {
        return creditFreeWonTotal;
    }

    public PLAY_STATE getPlayState() {
        return playState;
    }

    public List<Reel> getReels() {
        return reels;
    }

    public SpinResults getSpinResults() {
        return spinResults;
    }

    public Integer getFreeAwardsTotal() {
        return freeAwardsTotal;
    }

    public Integer getFreeAwardsUsed() {
        return freeAwardsUsed;
    }

    public void setCreditPerLine(Integer creditPerLine) {
        this.creditPerLine = creditPerLine;
    }

    public void setCreditValue(BigDecimal creditValue) {
        this.creditValue = creditValue;
    }

    public void setCreditWager(Integer creditWager) {
        this.creditWager = creditWager;
    }

    public void setCreditWonTotal(Integer creditWonTotal) {
        this.creditWonTotal = creditWonTotal;
    }

    public void setCreditFreeWonTotal(Integer creditFreeWonTotal) {
        this.creditFreeWonTotal = creditFreeWonTotal;
    }

    public void setPlayState(PLAY_STATE playState) {
        this.playState = playState;
    }

    public void setReels(List<Reel> reels) {
        this.reels = reels;
    }

    public void setSpinResults(SpinResults spinResults) {
        this.spinResults = spinResults;
    }

    public void setFreeAwardsTotal(Integer freeAwardsTotal) {
        this.freeAwardsTotal = freeAwardsTotal;
    }

    public void setFreeAwardsUsed(Integer freeAwardsUsed) {
        this.freeAwardsUsed = freeAwardsUsed;
    }

    public GameState unmarshal(Node eGameState) {
        if(eGameState.getChildCount() > 0) {
            this.playState          = PLAY_STATE.valueOf(eGameState.query("gameContext/currentPlayState/@state").get(0).getValue());
            this.creditValue        = new BigDecimal(eGameState.query("gameContext/currentWager/@creditValue").get(0).getValue());
            this.creditPerLine      = new Integer(eGameState.query("gameContext/currentWager/@creditPerLine").get(0).getValue());
            this.creditWager        = new Integer(eGameState.query("gameContext/currentWager/@creditWager").get(0).getValue());
            this.creditWonTotal     = new Integer(eGameState.query("gameContext/currentResult/@creditWon").get(0).getValue());
            this.freeAwardsUsed     = new Integer(eGameState.query("gameContext/currentFreeFeature/@usedSpins").get(0).getValue());
            this.freeAwardsTotal    = new Integer(eGameState.query("gameContext/currentFreeFeature/@totalSpins").get(0).getValue());
            this.creditFreeWonTotal = new Integer(eGameState.query("gameContext/currentFreeFeature/@totalWon").get(0).getValue());
            this.reels              = new LinkedList<Reel>();
            Nodes nReels = eGameState.query("gameContext/reels/reel");
            for(int i = 0; i < nReels.size(); i++) {
                this.reels.add(new Reel().unmarshal(nReels.get(i)));
            }
            Nodes nSpinResults      = eGameState.query("gameContext/lines");
            if(nSpinResults.size() > 0) {
                this.spinResults = new SpinResults().unmarshal(nSpinResults.get(0));
            }
        }
        return this;
    }

    public Node marshal() {
        Element eGameState          = new Element("gameState");
        Element eGameContext        = new Element("gameContext");
        Element eCurrentPlayState   = new Element("currentPlayState");
        Element eCurrentWager       = new Element("currentWager");
        Element eCurrentResult      = new Element("currentResult");
        Element eCurrentReels       = new Element("reels");
        Element eCurrentFreeFeature = new Element("currentFreeFeature");
        // Setup State
        eCurrentPlayState.addAttribute(new Attribute("state", this.playState.name()));
        // Setup CurrentWager
        eCurrentWager.addAttribute(new Attribute("creditValue",   this.creditValue.toPlainString()));
        eCurrentWager.addAttribute(new Attribute("creditPerLine", this.creditPerLine.toString()));
        eCurrentWager.addAttribute(new Attribute("creditWager",   this.creditWager.toString()));
        // Setup CurrentReels - If exist
        if(this.reels != null) {
            for(Reel aReel : this.reels) {
                eCurrentReels.appendChild(aReel.marshal());
            }
        }
        eCurrentResult.addAttribute(new Attribute("creditWon", this.creditWonTotal.toString()));
        // Setup CurrentFreeFeature - If exist
        eCurrentFreeFeature.addAttribute(new Attribute("totalSpins", this.freeAwardsTotal.toString()));
        eCurrentFreeFeature.addAttribute(new Attribute("usedSpins",  this.freeAwardsUsed.toString()));
        eCurrentFreeFeature.addAttribute(new Attribute("totalWon",   this.creditFreeWonTotal.toString()));

        // Setup GameState
        eGameContext.appendChild(eCurrentPlayState);
        eGameContext.appendChild(eCurrentWager);
        eGameContext.appendChild(eCurrentResult);
        if(this.spinResults != null) {
            eGameContext.appendChild(this.spinResults.marshal());
        }
        eGameContext.appendChild(eCurrentReels);
        eGameContext.appendChild(eCurrentFreeFeature);

        eGameState.appendChild(eGameContext);
        return eGameState;
    }

}