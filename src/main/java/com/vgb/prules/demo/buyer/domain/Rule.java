package com.vgb.prules.demo.buyer.domain;

import java.util.List;

public class Rule {
    private final int scoreIfSuccessful;
    private final LogicalOperator logicalOperator;
    private final List<Condition> conditions;

    public Rule(int scoreIfSuccessful, LogicalOperator logicalOperator, List<Condition> conditions) {
        this.scoreIfSuccessful = scoreIfSuccessful;
        this.logicalOperator = logicalOperator;
        this.conditions = conditions;
    }

}
