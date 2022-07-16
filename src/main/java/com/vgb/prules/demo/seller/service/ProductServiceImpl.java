package com.vgb.prules.demo.seller.service;

import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.seller.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public Collection<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
