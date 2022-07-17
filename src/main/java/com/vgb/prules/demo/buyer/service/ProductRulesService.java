package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.Rule;

import java.util.Collection;
import java.util.List;

/**
 * Product rules service
 */
public interface ProductRulesService {

    /**
     * Add rules for product
     * @param productName
     * @param rules
     */
    void addProductRules(String productName, List<Rule> rules);


    /**
     * Get rules for product
     * @param name
     * @return
     */
    Collection<Rule> getRuleByProductName(String name);

    /**
     * Delete all rules
     */
    void deleteAll();
}
