package com.vgb.prules.demo.buyer.repository;

import com.vgb.prules.demo.buyer.domain.Rule;

import java.util.Collection;
import java.util.List;

public interface ProductRulesRepository {
    void addProductRules(String productName, List<Rule> rules);

    Collection<Rule> getRuleByProductName(String name);

    void deleteAll();



}
