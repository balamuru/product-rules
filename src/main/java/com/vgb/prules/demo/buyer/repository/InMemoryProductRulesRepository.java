package com.vgb.prules.demo.buyer.repository;

import com.vgb.prules.demo.buyer.domain.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRulesRepository implements ProductRulesRepository {
    private final Map<String, List<Rule>> productNameRulesMap = new LinkedHashMap<>();

    @Override
    public void addProductRules(String productName, List<Rule> rules) {
        productNameRulesMap.put(productName, rules);

    }

    @Override
    public Collection<Rule> getRuleByProductName(String name) {
        return Collections.unmodifiableCollection(productNameRulesMap.getOrDefault(name, new ArrayList<>()));
    }

    @Override
    public void deleteAll() {
        productNameRulesMap.clear();
    }
}
