package com.vgb.prules.demo.buyer.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

public class Condition {
    private  final RuleConstants.ComparatorOperator comparatorOperator;
    private final Attribute targetAttribute;

    public Condition(RuleConstants.ComparatorOperator comparatorOperator, Attribute targetAttribute) {
        this.comparatorOperator = comparatorOperator;
        this.targetAttribute = targetAttribute;
    }

    public RuleConstants.ComparatorOperator getComparatorOperator() {
        return comparatorOperator;
    }

    public Attribute getTargetAttribute() {
        return targetAttribute;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "comparatorOperator=" + comparatorOperator +
                ", targetAttribute=" + targetAttribute +
                '}';
    }
}
