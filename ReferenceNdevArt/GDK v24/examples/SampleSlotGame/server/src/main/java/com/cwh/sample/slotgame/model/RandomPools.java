/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;

import java.util.HashMap;
import java.util.Map;

public class RandomPools {
    private final Map<String, Pool> poolMap = new HashMap<String, Pool>();

    public Integer nextSeq(String poolId) {
        return this.poolMap.get(poolId).nextSeq();
    }

    public RandomPools unmarshal(Node eRandomPools) {
        Element     xRandomPools = (Element)eRandomPools;
        Elements    xPools       = xRandomPools.getChildElements("pool");

        for(int i = 0; i < xPools.size(); i++) {
            Pool aPool = new Pool().unmarshal(xPools.get(i));
            this.poolMap.put(aPool.getPoolId(), aPool);
        }

        return this;
    }
}