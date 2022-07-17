package com.vgb.prules.demo.seller.repository;

import com.vgb.prules.demo.common.domain.Product;

import java.util.Collection;

/**
 * Simple product repository
 */
public interface ProductRepository {

    /**
     * Add a product
     * @param product
     */
    void addProduct(Product product);

    /**
     * Get all products
     * @return
     */
    Collection<Product> getProducts();

    /**
     * Delete all products
     */
    void deleteAll();

}
