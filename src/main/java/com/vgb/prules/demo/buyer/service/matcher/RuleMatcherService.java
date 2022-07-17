package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.RuleMatchResults;
import com.vgb.prules.demo.common.domain.Product;

/**
 * Matches all rules for a product
 */
public interface RuleMatcherService {
    RuleMatchResults matchRules(Product product);
}
