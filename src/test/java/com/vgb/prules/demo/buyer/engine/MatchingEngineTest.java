package com.vgb.prules.demo.buyer.engine;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.service.matcher.ProductMatchingService;
import com.vgb.prules.demo.seller.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static com.vgb.prules.demo.util.DemoDataUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MatchingEngineTest {

    @InjectMocks
    MatchingEngine matchingEngine;
    @Mock
    ProductMatchingService productMatchingService;
    @Mock
    ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void matchFalse() {

        Mockito.when(productMatchingService.match(PRODUCT4, 50))
                .thenReturn(new ProductMatchResult(
                        PRODUCT4.getId(),
                        PRODUCT4.name(),
                        (int) PRODUCT4.qty(),
                        PRODUCT4.price(),
                        false,
                        40,
                        10));

        final List<ProductMatchResult> matchedProducts = matchingEngine.match(new HashSet<>() {{
            add(PRODUCT4);
        }},50);
        assertEquals(0, matchedProducts.size());

        Mockito.verify(productMatchingService).match(PRODUCT4, 50);
    }

    @Test
    void matchTrue() {
        Mockito.when(productMatchingService.match(PRODUCT4, 50))
                .thenReturn(new ProductMatchResult(
                        PRODUCT4.getId(),
                        PRODUCT4.name(),
                        (int) PRODUCT4.qty(),
                        PRODUCT4.price(),
                        true,
                        40,
                        10));

        final List<ProductMatchResult> matchedProducts = matchingEngine.match(new HashSet<>() {{
            add(PRODUCT4);
        }},50);
        assertEquals(1, matchedProducts.size());
        Mockito.verify(productMatchingService).match(PRODUCT4, 50);
    }


    @Test
    void matchPartial() {
        Mockito.when(productMatchingService.match(PRODUCT1, 50))
                .thenReturn(
                        new ProductMatchResult(
                                PRODUCT1.getId(),
                                PRODUCT1.name(),
                                (int) PRODUCT1.qty(),
                                PRODUCT1.price(),
                                true,
                                70,
                                10));

        Mockito.when(productMatchingService.match(PRODUCT2, 50))
                .thenReturn(
                        new ProductMatchResult(
                                PRODUCT2.getId(),
                                PRODUCT2.name(),
                                (int) PRODUCT2.qty(),
                                PRODUCT2.price(),
                                true,
                                60,
                                10));

        Mockito.when(productMatchingService.match(PRODUCT3, 50))
                .thenReturn(
                        new ProductMatchResult(
                                PRODUCT3.getId(),
                                PRODUCT3.name(),
                                (int) PRODUCT3.qty(),
                                PRODUCT3.price(),
                                false,
                                20,
                                10));

        Mockito.when(productMatchingService.match(PRODUCT4, 50))
                .thenReturn(
                        new ProductMatchResult(
                                PRODUCT4.getId(),
                                PRODUCT4.name(),
                                (int) PRODUCT4.qty(),
                                PRODUCT4.price(),
                                false,
                                10,
                                10));

        final List<ProductMatchResult> matchedProducts = matchingEngine.match(  new HashSet<>() {{
            add(PRODUCT1);
            add(PRODUCT2);
            add(PRODUCT3);
            add(PRODUCT4);
        }}, 50);
        assertEquals(2, matchedProducts.size()); //only 2 of the products match rules
        Mockito.verify(productMatchingService, Mockito.times(1)).match(PRODUCT1, 50);
        Mockito.verify(productMatchingService, Mockito.times(1)).match(PRODUCT2, 50);
        Mockito.verify(productMatchingService, Mockito.times(1)).match(PRODUCT3, 50);
        Mockito.verify(productMatchingService, Mockito.times(1)).match(PRODUCT4, 50);
    }
}