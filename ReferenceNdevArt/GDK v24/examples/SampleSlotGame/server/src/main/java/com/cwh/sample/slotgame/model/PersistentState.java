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

public class PersistentState {
    private Integer numBonusSymbols = 0;

    public Integer getNumBonusSymbols() {
        return numBonusSymbols;
    }

    public void setNumBonusSymbols(Integer numBonusSymbols) {
        this.numBonusSymbols = numBonusSymbols;
    }

    public PersistentState unmarshal(Node ePersistentState) {
        if(ePersistentState.getChildCount() > 0) {
            this.numBonusSymbols = new Integer(ePersistentState.query("persistentContext/numberSeven/@count").get(0).getValue());
        }
        return this;
    }

    public Node marshal() {
        Element ePersistentState   = new Element("persistentState");
        Element ePersistentContext = new Element("persistentContext");
        Element eNumberSeven     = new Element("numberSeven");
        ePersistentContext.appendChild(eNumberSeven);
        eNumberSeven.addAttribute(new Attribute("count", this.numBonusSymbols.toString()));

        ePersistentState.appendChild(ePersistentContext);
        return ePersistentState;
    }

}