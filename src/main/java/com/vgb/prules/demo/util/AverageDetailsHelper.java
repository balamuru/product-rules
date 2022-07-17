package com.vgb.prules.demo.util;

public class AverageDetailsHelper {
    private float weightedSPriceSum = 0;
    private long totalQty = 0;
    private float distinctPriceSum = 0;
    private long distinctQty = 0;

    public void accumulate(float price, int qty) {
        weightedSPriceSum+=price*qty;
        totalQty+=qty;
        distinctPriceSum+=price;
        distinctQty+=1;
    }

    public float weightedPriceAverage() {
        return totalQty == 0 ? 0 : weightedSPriceSum/totalQty;
    }

    public float distinctPriceAverage() {
        return distinctQty == 0 ? 0 : distinctPriceSum/distinctQty;
    }

    public float getWeightedSPriceSum() {
        return weightedSPriceSum;
    }

    public long getTotalQty() {
        return totalQty;
    }

    public float getDistinctPriceSum() {
        return distinctPriceSum;
    }

    public long getDistinctQty() {
        return distinctQty;
    }
}
