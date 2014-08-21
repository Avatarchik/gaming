/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.monarchsun.model;

import com.cwh.slotstoolbox2.SlotsRandom;
import nu.xom.Element;
import nu.xom.Node;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Pool implements SlotsRandom{
    private String               poolId = null;
    private final Queue<Integer> pool   = new LinkedList<Integer>();

    /**
     * Constructs a pool for the given identifier with the given sequence of numbers.
     *
     * @param id   name/id of the pool
     * @param ints list of integers
     */
    public Pool(final String id, final Collection<Integer> ints) {
        this.poolId = id;
        this.pool.clear();
        this.pool.addAll(ints);
    }


    public Pool()
    {

    }

    public String getPoolId() {
        return poolId;
    }

    public int nextSeq() {
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