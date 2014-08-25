/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Node;
import nu.xom.Element;

public class Player {
    private Integer playerId = null;

    public Integer getPlayerId() {
        return playerId;
    }

    public Player unmarshal(Node ePlayer) {
        this.playerId = new Integer(((Element)ePlayer).getAttributeValue("id"));
        return this;
    }
}