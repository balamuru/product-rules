package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.common.domain.Product;

/**
 * This class finds matches for a particular threshold
 */
public interface ProductMatchingService {

    /**
     * Finds match results for a product
     * @param product product to match
     * @param successThreshold minimum condition success threshold for the product across all matching rules
     * @return
     */
    ProductMatchResult match(Product product, int successThreshold);
}
