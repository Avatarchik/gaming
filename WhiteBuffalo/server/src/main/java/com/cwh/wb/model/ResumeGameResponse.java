/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import com.cwh.wb.model.Marshalling;
import com.cwh.slotstoolbox2.reels.Reels;
import com.cwh.wb.WhiteBuffaloConfig.PowerXStreamPaylineResult;
import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloFreeSpinsReelsConfig;
import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloReelsConfig;

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
    private Reels     reels             = null;
    private SpinResults    spinResults       = null;
    private Integer        freeAwardTotal    = null;
    private Integer        freeAwardUsed     = null;
    private Integer        freeAwardWon      = null;
    private BigDecimal     coinValue         = null;
    private Integer        creditWager    = null;
    private List<Integer> baseGameStops = null;
    private List<List<String>>     reelsAsList             = null;
    
    Integer rtpLevel = 0;
	private List<String> replacementPattern;
    
    public void setRTPLevel(Integer rtpLevel) {
		this.rtpLevel = rtpLevel;
	}

    public void setReels(Reels reels) {
        this.reels = reels;
    }
    
    public void setReelsAsList(List<List<String>> reels) {
        this.reelsAsList = reels;
    }

    public void setSpinResults(SpinResults spinResults) {
        this.spinResults = spinResults;
    }

    public List<String> getReplacementPattern() {
		return replacementPattern;
	}

	public void setReplacementPattern(List<String> replacementPattern) {
		this.replacementPattern = replacementPattern;
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

    public void setCreditWager(Integer creditWager) {
        this.creditWager = creditWager;
    }

    @Override
    public Node marshal() {
        Element eMsgdata = new Element("msgdata");
        eMsgdata.addAttribute(new Attribute("reqName", this.requestName));
        
        Element eReelsMain        = new Element("mainReelsConfig");
        Marshalling.ReelsMapMarshal(WhiteBuffaloReelsConfig.getReels(), eReelsMain, rtpLevel);
        eMsgdata.appendChild(eReelsMain);
        
        Element eReelsFreeSpins       = new Element("freeSpinReelsConfig");
        Marshalling.ReelsMapMarshal(WhiteBuffaloFreeSpinsReelsConfig.getReels(), eReelsFreeSpins, rtpLevel);
        eMsgdata.appendChild(eReelsFreeSpins);
        
        Element eReels        = new Element("reels");

        Marshalling.ReelsMarshalResume(this.reels, this.reelsAsList, eReels);
        
        Marshalling.ReReplacementMarshal(replacementPattern, eReels);
        
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

        Element eInitialWager = new Element("initialWager");
        eInitialWager.addAttribute(new Attribute("coinValue",      this.coinValue.toPlainString()));
        eInitialWager.addAttribute(new Attribute("creditWager", this.creditWager.toString()));
        eMsgdata.appendChild(eInitialWager);

        Element eFreeSpins = new Element("freeSpins");
        eFreeSpins.addAttribute(new Attribute("total", this.freeAwardTotal.toString()));
        eFreeSpins.addAttribute(new Attribute("used",  this.freeAwardUsed.toString()));
        eFreeSpins.addAttribute(new Attribute("won",   this.freeAwardWon.toString()));
        eMsgdata.appendChild(eFreeSpins);
        
        Element eBaseGameStops  = new Element("baseGameStops");
        Marshalling.BaseGameStopsMarshal(baseGameStops, eBaseGameStops);
        eMsgdata.appendChild(eBaseGameStops);

        return eMsgdata;
    }
    
    public void setBaseGameStops(List<Integer> baseGameStops) {
		this.baseGameStops = baseGameStops;
	}
}