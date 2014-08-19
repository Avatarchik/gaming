package com.cwh.test.wb.prd;


import com.cwh.gdk.tool.GdkRandomGenerator;
import com.cwh.slotstoolbox2.SlotsRandom;

public class PredRNG implements SlotsRandom {
    GdkRandomGenerator random = GdkRandomGenerator.getInstance(GdkRandomGenerator.PoolDescriptor.CODED);
    private final String poolId;

    public PredRNG(final String poolId, final int start, final int end) throws Exception {
        this.poolId = poolId;
        random.addPool(poolId, new Integer [] { start, end });
    }

    public int nextSeq() {
        try {
            return random.dealNumber(poolId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }
}
