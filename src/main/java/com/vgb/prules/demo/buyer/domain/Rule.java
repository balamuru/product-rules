package com.vgb.prules.demo.buyer.domain;

import java.util.Collections;
import java.util.List;

public class Rule {
    private final int maxScore;
    private final LogicalOperator logicalOperator;
    private final List<Condition> conditions;

    public Rule(int maxScore, LogicalOperator logicalOperator, List<Condition> conditions) {
        this.maxScore = maxScore;
        this.logicalOperator = logicalOperator;
        this.conditions = conditions;
    }

    public Rule(int maxScore, List<Condition> conditions) {
        this(maxScore, LogicalOperator.AND, conditions);
    }

    public int getMaxScore() {
        return maxScore;
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }
}
