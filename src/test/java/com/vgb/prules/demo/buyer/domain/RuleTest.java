package com.vgb.prules.demo.buyer.domain;

import com.vgb.prules.demo.demo.DemoDataUtils;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.EQUALS;
import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.LESS_THAN;
import static com.vgb.prules.demo.buyer.domain.RuleConstants.LogicalOperator.AND;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuleTest {

    Rule rule;

    @BeforeEach
    void init() {
        rule = DemoDataUtils.RULE2;
    }

    @Test
    void testRule() {
        assertEquals(100, rule.getMaxScore());
        assertEquals(AND, rule.getLogicalOperator());
        final List<Condition> conditions = rule.getConditions();
        assertEquals(3, conditions.size());
        assertEquals(new Condition(EQUALS, new StringAttribute(COLOR, "Yellow")), conditions.get(0));
        assertEquals(new Condition(LESS_THAN, new NumberAttribute(PRICE, 300)), conditions.get(1));
        assertEquals(new Condition(EQUALS, new NumberAttribute(QTY, 5)), conditions.get(2));
    }


}
