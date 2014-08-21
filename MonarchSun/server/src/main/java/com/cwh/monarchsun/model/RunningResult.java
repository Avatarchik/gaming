/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;

/**
 * This class just strictly represents the current balance of the game.
 */
public class RunningResult {
    private Integer creditsWon     = null;
    private Integer creditsWagered = null;

    public void setCreditsWagered(Integer creditsWagered) {
        this.creditsWagered = creditsWagered;
    }

    public void setCreditsWon(Integer creditsWon) {
        this.creditsWon = creditsWon;
    }

    public Node marshal() {
        Element eCreditResults  = new Element("creditResults");
        eCreditResults.addAttribute(new Attribute("wagered", this.creditsWagered.toString()));
        eCreditResults.addAttribute(new Attribute("won",     this.creditsWon.toString()));
        return eCreditResults;
    }
}