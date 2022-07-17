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

/**
 * Entrypoint to the matching algorithm
 */
@Service
public class MatchingEngine {


    @Autowired
    private ProductMatchingService productMatchingService;
    @Autowired
    private ProductService productService;

    /**
     * Get all products
     * For each product,
     *  If there are rules for this product,
     *   Attempt to match
     * @return
     */
    public List<ProductMatchResult> match() {

        final Collection<Product> products = Collections.synchronizedCollection(productService.getProducts());

        //for each product, see if it matches rules setup for that particular product
        return products.stream()
                .map(product -> productMatchingService.match(product))
                .filter(productMatchResult -> productMatchResult.isMatch())
                .collect(Collectors.toUnmodifiableList());
    }
}
