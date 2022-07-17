package com.vgb.prules.demo.util;

/**
 * Helper class for aggregating rule match results
 */
public class AverageDetailsHelper {
    private float weightedSPriceSum = 0;
    private long totalQty = 0;
    private float distinctPriceSum = 0;
    private long distinctQty = 0;

    /**
     * Accumulate stats for a matched product
     * @param price
     * @param qty
     */
    public void accumulate(float price, int qty) {
        weightedSPriceSum+=price*qty;
        totalQty+=qty;
        distinctPriceSum+=price;
        distinctQty+=1;
    }

    /**
     * Calculate weighted average price
     * @return
     */
    public float weightedPriceAverage() {
        return totalQty == 0 ? 0 : weightedSPriceSum/totalQty;
    }

    /**
     * Calculate distinct average price across single instance of each product
     * @return
     */
    public float distinctPriceAverage() {
        return distinctQty == 0 ? 0 : distinctPriceSum/distinctQty;
    }

    /**
     * Get weighted price sum
     * @return
     */
    public float getWeightedSPriceSum() {
        return weightedSPriceSum;
    }

    /**
     * Get total quantity of times
     * @return
     */
    public long getTotalQty() {
        return totalQty;
    }

    /**
     * Get distinct price sum
     * @return
     */
    public float getDistinctPriceSum() {
        return distinctPriceSum;
    }

    /**
     * Get distinct number of matched products
     * @return
     */
    public long getDistinctQty() {
        return distinctQty;
    }
}
