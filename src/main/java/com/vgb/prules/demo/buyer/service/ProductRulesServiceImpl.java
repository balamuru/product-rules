package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.buyer.repository.ProductRulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductRulesServiceImpl implements ProductRulesService {

    @Autowired
    private ProductRulesRepository productRulesRepository;


    @Override
    public void addProductRules(String productName, List<Rule> rules) {
        productRulesRepository.addProductRules(productName, rules);

    }

    @Override
    public Collection<Rule> getRuleByProductName(String name) {
        return productRulesRepository.getRuleByProductName(name);
    }

    @Override
    public void deleteAll() {
        productRulesRepository.deleteAll();
    }
}
