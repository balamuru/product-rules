package com.vgb.prules.demo.seller.repository;

import com.vgb.prules.demo.common.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * In memory implementation of the @{@link ProductRepository}
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> productMap = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        productMap.put(product.getId(), product);
    }

    @Override
    public Collection<Product> getProducts() {
        return Collections.unmodifiableCollection(productMap.values());
    }

    @Override
    public void deleteAll() {
        productMap.clear();
    }


}
