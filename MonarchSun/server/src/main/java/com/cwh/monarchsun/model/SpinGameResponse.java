/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import java.util.List;

import com.cwh.slotstoolbox2.reels.Reels;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPaylineResult;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

public class SpinGameResponse extends GameResponse {
    private Reels reels             = null;
    private SpinResults    spinResults       = null;
    private Integer        freeAwardTotal    = null;
    private Integer        freeAwardUsed     = null;
    private Integer        freeAwardWon      = null;
    List<List<String>> reelDisplay = null;
    List<Integer> stopPositions = null;
    List<String> rePattern = null;

    public void setReels(Reels reels) {
        this.reels = reels;
    }

    public void setSpinResults(SpinResults spinResults) {
        this.spinResults = spinResults;
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
        Marshalling.ReelsMarshal(this.reelDisplay, this.stopPositions, eReels);
        Marshalling.ReReplacementMarshal(this.rePattern, eReels);
        eMsgdata.appendChild(eReels);

        Element eLineResults  = new Element("lines");
        eLineResults.addAttribute(new Attribute("won", this.spinResults.getCreditsWonTotal().toString()));
        for(PowerXStreamPaylineResult payLine : this.spinResults.getPaylineResults()) {
            eLineResults.appendChild(Marshalling.PaylineResultMarshal(payLine));
        }
        eMsgdata.appendChild(eLineResults);

        eMsgdata.appendChild(this.runningResult.marshal());
        
        if(this.summaryResult != null) {
            eMsgdata.appendChild(this.summaryResult.marshal());
        }

        Element eFreeSpins = new Element("freeSpins");
        eFreeSpins.addAttribute(new Attribute("total", this.freeAwardTotal.toString()));
        eFreeSpins.addAttribute(new Attribute("used",  this.freeAwardUsed.toString()));
        eFreeSpins.addAttribute(new Attribute("won",   this.freeAwardWon.toString()));
        eMsgdata.appendChild(eFreeSpins);

        return eMsgdata;
    }

	public void setReelDisplay(List<List<String>> reelDisplay) {
		this.reelDisplay = reelDisplay;
	}

	public void setStopPositions(List<Integer> stopPositions) {
		this.stopPositions = stopPositions;
	}
	
	public void setRePattern(List<String> rePattern) {
		this.rePattern = rePattern;
	}
}