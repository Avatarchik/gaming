/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

import com.cwh.wb.WhiteBuffaloConfig.PowerXStreamPaylineResult;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;

import java.util.LinkedList;
import java.util.List;

public class SpinResults {
    private Integer             creditsWonTotal  = 0;
    private List<PowerXStreamPaylineResult> paylineResults   = new LinkedList<PowerXStreamPaylineResult>();

    public void addPaylineResult(PowerXStreamPaylineResult paylineResult) {
        this.paylineResults.add(paylineResult);
        this.creditsWonTotal += paylineResult.won();
    }

    public void addPaylineResult(List<PowerXStreamPaylineResult> paylineResults)
    {
        for (PowerXStreamPaylineResult result : paylineResults)
        {
            this.paylineResults.add(result);
            this.creditsWonTotal += result.won();
        }
    }

    public List<PowerXStreamPaylineResult> getPaylineResults() {
        return paylineResults;
    }

    public Integer getCreditsWonTotal() {
        return creditsWonTotal;
    }

    public SpinResults unmarshal(Node eLines) {
        this.creditsWonTotal = new Integer(((Element)eLines).getAttributeValue("won"));
        Nodes nPaylines = eLines.query("line");
        for(int i = 0; i < nPaylines.size(); i++) {
            this.paylineResults.add(Marshalling.PaylineResultUnMarshal(nPaylines.get(i)));
        }
        return this;
    }

    public Node marshal() {
        Element eLines = new Element("lines");
        eLines.addAttribute(new Attribute("won", this.creditsWonTotal.toString()));
        for(PowerXStreamPaylineResult payline : this.paylineResults)
        {
            eLines.appendChild(Marshalling.PaylineResultMarshal(payline));
        }

        return eLines;
    }

}