package com.vgb.prules.demo.buyer.engine;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.service.matcher.ProductMatchingService;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingEngine {


    @Autowired
    private ProductMatchingService productMatchingService;
    @Autowired
    private ProductService productService;

    public List<ProductMatchResult>  match() {
        final Collection<Product> products = Collections.synchronizedCollection(productService.getProducts());
        return products.parallelStream()
                .map(product -> productMatchingService.match(product))
                .filter(productMatchResult -> productMatchResult.isMatch())
                .collect(Collectors.toUnmodifiableList());
    }
}
