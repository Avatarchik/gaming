/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package com.cwh.sample.slotgame.model;

import nu.xom.Element;
import nu.xom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Pool {
    private String               poolId = null;
    private final Queue<Integer> pool   = new LinkedList<Integer>();

    public String getPoolId() {
        return poolId;
    }

    public Integer nextSeq() {
        return this.pool.poll();
    }

    public void addSeq(Integer seq) {
        this.pool.offer(seq);
    }

    public Pool unmarshal(Node ePool) {
        Element xPool = (Element)ePool;
        this.poolId = xPool.getAttributeValue("id");
        for(String aSeq : xPool.getValue().trim().split(",")) {
            if(aSeq.trim().length() > 0) {
                this.addSeq(new Integer(aSeq.trim()));
            }
        }
        return this;
    }
}