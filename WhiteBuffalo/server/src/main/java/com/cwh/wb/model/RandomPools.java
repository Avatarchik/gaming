/*
 * Copyright (c) 2011. Chartwell Technology Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Chartwell Technology Inc.
 * Use is subject to license terms.
 */

package   com.cwh.wb.model;

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

    public RandomPools(final Map<String, Pool> pools) {
        if (pools == null)
            throw new IllegalArgumentException("Pool map must be non-null.");
        this.poolMap.putAll(pools);
    }

    public RandomPools()
    {

    }

    /**
     * Gets the pool identified by {@code poolId}.  Throws {@link IllegalArgumentException} if {@code poolId} is null, empty or doesn't exist.
     *
     * @param poolId identifier of the pool to retrieve
     * @return the pool for the id
     * @throws IllegalArgumentException if {@code poolId} is null, the empty string or has not mapping to a known pool.
     */
    public Pool pool(final String poolId) {
        if (poolId == null || poolId.length() == 0)
            throw new IllegalArgumentException("Pool ID must be non-null and non-empty.");
        if (!poolMap.containsKey(poolId))
            throw new IllegalArgumentException("No such pool with id '"+poolId+"'");
        return poolMap.get(poolId);
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