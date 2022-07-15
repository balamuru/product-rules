package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.Rule;

import java.util.Collection;
import java.util.List;

public interface ProductRulesService {
    void addProductRules(String productName, List<Rule> rules);

    Collection<Rule> getRuleByProductName(String name);

    void deleteAll();
}
