package com.vgb.prules.demo.buyer.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Rule {
    private final int maxScore;
    private final RuleConstants.LogicalOperator logicalOperator;
    private final List<Condition> conditions;

    public Rule(int maxScore, RuleConstants.LogicalOperator logicalOperator, List<Condition> conditions) {
        this.maxScore = maxScore;
        this.logicalOperator = logicalOperator;
        this.conditions = conditions;
    }

    public Rule(int maxScore, List<Condition> conditions) {
        this(maxScore, RuleConstants.LogicalOperator.AND, conditions);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public RuleConstants.LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return maxScore == rule.maxScore && logicalOperator == rule.logicalOperator && Objects.equals(conditions, rule.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxScore, logicalOperator, conditions);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "maxScore=" + maxScore +
                ", logicalOperator=" + logicalOperator +
                ", conditions=" + conditions +
                '}';
    }
}
