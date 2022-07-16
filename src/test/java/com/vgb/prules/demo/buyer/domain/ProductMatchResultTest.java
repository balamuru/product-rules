package com.vgb.prules.demo.buyer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMatchResultTest {

    ProductMatchResult productMatchResult;

    @BeforeEach
    void init() {
        productMatchResult = new ProductMatchResult(
                "aaaa", "silly socks", 4, 10.50f, true, 70);

    }

    @Test
    void getProductId() {
        assertEquals("aaaa", productMatchResult.getProductId());
    }

    @Test
    void getProductName() {
        assertEquals("silly socks", productMatchResult.getProductName());
    }

    @Test
    void getQty() {
        assertEquals(4, productMatchResult.getQty());
    }

    @Test
    void getPrice() {
        assertEquals(10.50f, productMatchResult.getPrice());
    }

    @Test
    void isMatch() {
        assertEquals(true, productMatchResult.isMatch());
    }

    @Test
    void getPercentScore() {
        assertEquals(70, productMatchResult.getPercentScore());
    }

    @Test
    void testToString() {
    }
}