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

/**
 * To avoid any configuration for the sample game, the Reel will contain the ReelMappings.
 */
public class Reel {

    public static final Integer     REELSTRIP_SIZE  = 15;
    public static final Integer[][] REELSTRIPS      = new Integer[][]{{ 1, 1, 1, 1, 2, 3, 1, 4, 1, 5, 6, 1, 7, 2, 3},
                                                                      { 1, 1, 1, 1, 2, 3, 1, 4, 1, 1, 1, 6, 2, 3, 4},
                                                                      { 1, 1, 1, 1, 2, 3, 1, 4, 1, 5, 6, 1, 1, 5, 1},
                                                                      { 1, 1, 1, 1, 2, 1, 3, 4, 1, 5, 1, 6, 1, 1, 3},
                                                                      { 1, 1, 1, 1, 2, 1, 3, 1, 1, 5, 6, 1, 7, 4, 5}};
    private Integer reelNum  = null;
    private Integer reelPos  = null;

    public Reel setReelNum(Integer reelNum) {
        this.reelNum = reelNum;
        return this;
    }

    public Reel setReelPos(Integer reelPos) {
        this.reelPos = reelPos;
        return this;
    }

    public Integer getReelNum() {
        return reelNum;
    }

    public Integer getReelCellValue(Integer reelCell) {
        Integer adjustedCell = (this.reelPos + reelCell) % REELSTRIP_SIZE;
        return Reel.REELSTRIPS[this.reelNum][adjustedCell];
    }

    public String getReelValues() {
        StringBuilder sReelBuilder = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            sReelBuilder.append(sReelBuilder.length() > 0 ? "," : "");
            sReelBuilder.append(this.getReelCellValue(i));
        }
        return sReelBuilder.toString();
    }

    public Node marshal() {
        Element reel = new Element("reel");
        reel.addAttribute(new Attribute("id",  String.valueOf(this.reelNum + 1)));
        reel.addAttribute(new Attribute("pos", String.valueOf(this.reelPos)));
        reel.appendChild(this.getReelValues());
        return reel;
    }

    public Reel unmarshal(Node eReel) {
        this.reelNum = new Integer(((Element)eReel).getAttribute("id").getValue()) - 1;
        this.reelPos = new Integer(((Element)eReel).getAttribute("pos").getValue());
        return this;
    }
}