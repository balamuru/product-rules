package com.vgb.prules.demo.seller.repository;

import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.util.DemoDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryProductRepositoryTest {

    ProductRepository repository;

    @BeforeEach
    void setup() {
        repository = new InMemoryProductRepository();
    }

    @Test
    void testProductRepository() {
        repository.addProduct(DemoDataUtils.PRODUCT1);
        repository.addProduct(DemoDataUtils.PRODUCT2);

        Collection<Product> products = repository.getProducts();
        assertEquals(2, products.size());
        assertTrue(products.contains(DemoDataUtils.PRODUCT1));
        assertTrue(products.contains(DemoDataUtils.PRODUCT2));

        //delete all and verify no more rules
        repository.deleteAll();
        assertEquals(0, repository.getProducts().size());

    }
}

