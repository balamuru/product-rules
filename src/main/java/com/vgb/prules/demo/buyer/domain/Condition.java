package com.vgb.prules.demo.buyer.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

import java.util.Objects;

/**
 * Rule matcher condition
 */
public class Condition {
    private final RuleConstants.ComparatorOperator comparatorOperator;
    private final Attribute targetAttribute;

    /**
     * Constructor
     * @param comparatorOperator
     * @param targetAttribute
     */
    public Condition(RuleConstants.ComparatorOperator comparatorOperator, Attribute targetAttribute) {
        this.comparatorOperator = comparatorOperator;
        this.targetAttribute = targetAttribute;
    }

    /**
     * get comparator criteria
     * @return
     */
    public RuleConstants.ComparatorOperator getComparatorOperator() {
        return comparatorOperator;
    }

    /**
     * Get the attribute to match against
     * @return
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return comparatorOperator == condition.comparatorOperator && Objects.equals(targetAttribute, condition.targetAttribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparatorOperator, targetAttribute);
    }
}
