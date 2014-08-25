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

public class InitGameResponse extends GameResponse {
    private Integer        numberSevensFound = null;

    public void setNumberSevensFound(Integer numberSevensFound) {
        this.numberSevensFound = numberSevensFound;
    }

    @Override
    public Node marshal() {
        Element eMsgdata = new Element("msgdata");
        eMsgdata.addAttribute(new Attribute("reqName", this.requestName));

        Element eStars = new Element("stars");
        eStars.addAttribute(new Attribute("total", this.numberSevensFound.toString()));
        eMsgdata.appendChild(eStars);
        
        return eMsgdata;
    }
}