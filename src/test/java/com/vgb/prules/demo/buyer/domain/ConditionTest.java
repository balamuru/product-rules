package com.vgb.prules.demo.buyer.domain;

import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.EQUALS;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.COLOR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionTest {

    Condition condition;

    @BeforeEach
    void init() {
        condition = new Condition(EQUALS, new StringAttribute(COLOR, "White"));
    }

    @Test
    void getComparatorOperator() {
        assertEquals(EQUALS, condition.getComparatorOperator());
    }

    @Test
    void getTargetAttribute() {
        assertEquals(new StringAttribute(COLOR, "White"), condition.getTargetAttribute());
    }
}