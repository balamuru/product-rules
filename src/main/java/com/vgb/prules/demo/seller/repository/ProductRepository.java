package com.vgb.prules.demo.seller.repository;

import com.vgb.prules.demo.common.domain.Product;

import java.util.Collection;

public interface ProductRepository {
    void addProduct(Product product);

    Collection<Product> getProducts();

    void deleteAll();

}
