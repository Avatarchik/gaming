/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import com.cwh.slotstoolbox2.reels.Reels;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    public static enum PLAY_STATE{NEW, FREE, FINISHED}

    private PLAY_STATE playState          = PLAY_STATE.NEW;
    private BigDecimal creditValue        = new BigDecimal(0);
    private Integer    creditWager        = 0;
    //private Integer    creditPerLine      = 0;
    private Long    creditWonTotal     = 0L;
    private Long    creditFreeWonTotal = 0L;
    private Integer    freeAwardsUsed     = 0;
    private Integer    freeAwardsTotal    = 0;
    private boolean isMaxBet = false;

    private SpinResults spinResults = null;
    private Reels  reels       = null;
    private List<List<String>> reelDisplay = null;
    private List<Integer> stopPositions = null;
    private List<Integer> baseGameStops = null;
    
    private List<String> replacementPattern = null;
    
    private Integer rtp = 0;

    private List<Integer> stickyWilds = new ArrayList<Integer>();

    /*public Integer getCreditPerLine() {
        return creditPerLine;
    }*/

    public BigDecimal getCreditValue() {
        return creditValue;
    }

    public Integer getCreditWager() {
        return creditWager;
    }

    public Long getCreditWonTotal() {
        return creditWonTotal;
    }

    public Long getCreditFreeWonTotal() {
        return creditFreeWonTotal;
    }

    public PLAY_STATE getPlayState() {
        return playState;
    }

    public Reels getReels() {
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

    public List<Integer> getStickyWilds() { return stickyWilds; }

    /*public void setCreditPerLine(Integer creditPerLine) {
        this.creditPerLine = creditPerLine;
    }*/

    public void setCreditValue(BigDecimal creditValue) {
        this.creditValue = creditValue;
    }

    public void setCreditWager(Integer creditWager) {
        this.creditWager = creditWager;
    }

    public void setCreditWonTotal(Long creditWonTotal) {
        this.creditWonTotal = creditWonTotal;
    }

    public void setCreditFreeWonTotal(Long creditFreeWonTotal) {
        this.creditFreeWonTotal = creditFreeWonTotal;
    }

    public void setPlayState(PLAY_STATE playState) {
        this.playState = playState;
    }

    public void setReels(Reels reels) {
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

    public void setStickyWilds (List<Integer> stickyWilds) { this.stickyWilds = stickyWilds; }

    public GameState unmarshal(Node eGameState) {
        if(eGameState.getChildCount() > 0) {
            this.playState          = PLAY_STATE.valueOf(eGameState.query("gameContext/currentPlayState/@state").get(0).getValue());
            this.creditValue        = new BigDecimal(eGameState.query("gameContext/currentWager/@creditValue").get(0).getValue());
            //this.creditPerLine      = new Integer(eGameState.query("gameContext/currentWager/@creditPerLine").get(0).getValue());
            this.creditWager        = new Integer(eGameState.query("gameContext/currentWager/@creditWager").get(0).getValue());
            this.rtp        		= new Integer(eGameState.query("gameContext/currentWager/@rtp").get(0).getValue());
            this.creditWonTotal     = new Long(eGameState.query("gameContext/currentResult/@creditWon").get(0).getValue());
            this.freeAwardsUsed     = new Integer(eGameState.query("gameContext/currentFreeFeature/@usedSpins").get(0).getValue());
            this.freeAwardsTotal    = new Integer(eGameState.query("gameContext/currentFreeFeature/@totalSpins").get(0).getValue());
            this.creditFreeWonTotal = new Long(eGameState.query("gameContext/currentFreeFeature/@totalWon").get(0).getValue());

            Nodes nSticky = eGameState.query("gameContext/stickyWild");

            this.stickyWilds = Marshalling.StickyWildsUnMarshal(nSticky);// Integer(eGameState.query("gameContext/stickyWild/@wilds").get(0).getValue());

            Nodes nReels = eGameState.query("gameContext/reels/reel");
            
            this.reels = Marshalling.ReelsUnMarshal(nReels);
            
            this.reelDisplay = Marshalling.ReelsUnMarshalAsList(nReels);
            
            Nodes nBaseGameStops = eGameState.query("gameContext/baseGameStops");
            this.baseGameStops = Marshalling.BaseGameStopsUnMarshal(nBaseGameStops);
            
            Nodes nReplacementPattern = eGameState.query("gameContext/reels/rePattern");
            this.replacementPattern = Marshalling.ReReplacementUnMarshal(nReplacementPattern);

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
        Element eBaseGameStops      = new Element("baseGameStops");
        Element eCurrentFreeFeature = new Element("currentFreeFeature");
        Element eStickyWild         = new Element("stickyWild");


        // Setup State
        eCurrentPlayState.addAttribute(new Attribute("state", this.playState.name()));
        // Setup CurrentWager
        eCurrentWager.addAttribute(new Attribute("creditValue",   this.creditValue.toPlainString()));
        //eCurrentWager.addAttribute(new Attribute("creditPerLine", this.creditPerLine.toString()));
        eCurrentWager.addAttribute(new Attribute("creditWager",   this.creditWager.toString()));
        eCurrentWager.addAttribute(new Attribute("rtp",   this.rtp.toString()));
        //Setup Sticky Wilds

        if(this.stickyWilds != null)
        {
            Marshalling.StickyWildsMarshal(this.stickyWilds, eStickyWild);
        }
        //eStickyWild.addAttribute(new Attribute("wilds", getStickyWilds().toString()));
        // Setup CurrentReels - If exist
        if(reelDisplay != null) {
        	Marshalling.ReelsMarshal(this.reelDisplay, this.stopPositions, eCurrentReels);
        }
        else if(this.reels != null) {
            Marshalling.ReelsMarshal(this.reels, eCurrentReels);
        }
        
        if(replacementPattern != null) {
        	Marshalling.ReReplacementMarshal(replacementPattern, eCurrentReels);
        }
        
        if(this.baseGameStops != null) {
            Marshalling.BaseGameStopsMarshal(this.baseGameStops, eBaseGameStops);
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
        eGameContext.appendChild(eStickyWild);
        if(this.spinResults != null) {
            eGameContext.appendChild(this.spinResults.marshal());
        }
        eGameContext.appendChild(eCurrentReels);
        eGameContext.appendChild(eCurrentFreeFeature);
        eGameContext.appendChild(eBaseGameStops);

        eGameState.appendChild(eGameContext);
        return eGameState;
    }

	public List<List<String>> getReelDisplay() {
		return reelDisplay;
	}

	public void setReelDisplay(List<List<String>> reelDisplay) {
		this.reelDisplay = reelDisplay;
	}

	public List<Integer> getStopPositions() {
		return stopPositions;
	}

	public void setStopPositions(List<Integer> stopPositions) {
		this.stopPositions = stopPositions;
	}

	public List<String> getReplacementPattern() {
		return replacementPattern;
	}

	public void setReplacementPattern(List<String> replacementPattern) {
		this.replacementPattern = replacementPattern;
	}

	public boolean isMaxBet() {
		return isMaxBet;
	}

	public void setMaxBet(boolean isMaxBet) {
		this.isMaxBet = isMaxBet;
	}
	
	public Integer getRtp() {
		return rtp;
	}

	public void setRtp(Integer rtp) {
		this.rtp = rtp;
	}

	public List<Integer> getBaseGameStops() {
		return baseGameStops;
	}

	public void setBaseGameStops(List<Integer> baseGameStops) {
		this.baseGameStops = baseGameStops;
	}

}