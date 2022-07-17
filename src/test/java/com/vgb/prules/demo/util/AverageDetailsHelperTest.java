package com.vgb.prules.demo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AverageDetailsHelperTest {

    AverageDetailsHelper helper;

    @BeforeEach
    public void init() {
        helper = new AverageDetailsHelper();

        helper.accumulate(1, 100);
        helper.accumulate(2, 200);
        helper.accumulate(3, 300);
    }

    @Test
    void accumulate() {
        assertEquals(3, helper.getDistinctQty());
        assertEquals(6, helper.getDistinctPriceSum());
        assertEquals(600, helper.getTotalQty());
        assertEquals(1400, helper.getWeightedSPriceSum());
        assertEquals(6 / 3f, helper.distinctPriceAverage());
        assertEquals((100 + 400 + 900) / (float) helper.getTotalQty(), helper.weightedPriceAverage());
        helper.accumulate(1, 1);
        assertEquals(4, helper.getDistinctQty());
        assertEquals(7, helper.getDistinctPriceSum());
        assertEquals(601, helper.getTotalQty());
        assertEquals(1401, helper.getWeightedSPriceSum());
        assertEquals(7 / 4f, helper.distinctPriceAverage());
        assertEquals((100 + 400 + 900 + 1) / (float) helper.getTotalQty(), helper.weightedPriceAverage());
    }


}