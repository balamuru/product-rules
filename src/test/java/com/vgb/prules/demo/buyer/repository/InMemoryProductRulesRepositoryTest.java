package com.vgb.prules.demo.buyer.repository;

import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.util.DemoDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryProductRulesRepositoryTest {

    ProductRulesRepository repository;
    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRulesRepository();
    }


    @Test
    void testProductRules() {

        List<Rule> rules = new ArrayList<>() {{
            add(DemoDataUtils.RULE1A);
            add(DemoDataUtils.RULE1B);
        }};
        repository.addProductRules("foo", rules);

        final Collection<Rule> retrievedRules = repository.getRuleByProductName("foo");
        assertEquals(rules.size(), retrievedRules.size());
        rules.forEach(rule ->
                assertTrue(retrievedRules.contains(rule))
        );
    }

}