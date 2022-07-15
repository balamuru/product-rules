package com.vgb.prules.demo.seller.repository;

import com.vgb.prules.demo.common.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepository {
    private Map<String, Product> productMap = new LinkedHashMap<>();

    public void addProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        productMap.put(product.getId(), product);
    }

    public Collection<Product> getProducts() {
        return productMap.values();
    }
}
