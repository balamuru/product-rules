package com.vgb.prules.demo.buyer.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Product rule
 */
public class Rule {
    private String name;
    private final int maxScore;
    private final RuleConstants.LogicalOperator logicalOperator;
    private final List<Condition> conditions;

    /**
     * Constructor
     * @param name
     * @param maxScore
     * @param logicalOperator
     * @param conditions
     */
    public Rule(String name, int maxScore, RuleConstants.LogicalOperator logicalOperator, List<Condition> conditions) {
        this.name = name;
        this.maxScore = maxScore;
        this.logicalOperator = logicalOperator;
        this.conditions = conditions;
    }


    /**
     * Constructor
     * @param name
     * @param maxScore
     * @param conditions
     */
    public Rule(String name, int maxScore, List<Condition> conditions) {
        this(name, maxScore, RuleConstants.LogicalOperator.AND, conditions);
    }

    /**
     * Max possible score if all conditions satisfy
     * @return
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Get operator between conditions
     * @return
     */
    public RuleConstants.LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    /**
     * Get conditions
     * @return
     */
    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return maxScore == rule.maxScore && Objects.equals(name, rule.name) && logicalOperator == rule.logicalOperator && Objects.equals(conditions, rule.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxScore, logicalOperator, conditions);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", maxScore=" + maxScore +
                ", logicalOperator=" + logicalOperator +
                ", conditions=" + conditions +
                '}';
    }
}
