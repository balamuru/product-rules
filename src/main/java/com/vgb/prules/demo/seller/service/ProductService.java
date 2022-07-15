package com.vgb.prules.demo.seller.service;

import com.vgb.prules.demo.common.domain.Product;

import java.util.Collection;

public interface ProductService {
    void addProduct(Product product);

    Collection<Product> getProducts();

    void deleteAll();

    Collection<Product> getProducts(String... productNames);
}
