package com.vgb.prules.demo.seller.service;

import com.vgb.prules.demo.common.domain.Product;

import java.util.Collection;

/**
 * Service to handle Products
 */
public interface ProductService {
    /**
     * Add product
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
