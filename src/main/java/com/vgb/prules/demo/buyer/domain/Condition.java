package com.vgb.prules.demo.buyer.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

public class Condition {
    private  final ComparatorOperator comparatorOperator;
    private final Attribute targetNameAndValue;

    public Condition(ComparatorOperator comparatorOperator, Attribute targetNameAndValue) {
        this.comparatorOperator = comparatorOperator;
        this.targetNameAndValue = targetNameAndValue;
    }

    public ComparatorOperator getComparatorOperator() {
        return comparatorOperator;
    }

    public Attribute getTargetNameAndValue() {
        return targetNameAndValue;
    }
}
