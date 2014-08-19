/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

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
        return this;
    }

    public Node marshal() {
        Element ePersistentState   = new Element("persistentState");
        Element ePersistentContext = new Element("persistentContext");

        ePersistentState.appendChild(ePersistentContext);
        return ePersistentState;
    }

}