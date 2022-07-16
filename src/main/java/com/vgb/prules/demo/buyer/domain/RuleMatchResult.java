package com.vgb.prules.demo.buyer.domain;

import java.util.Objects;

public class RuleMatchResult {
    final int matchedConditions;
    final int totalConditions;
    final int maxScoreForRule;

    public RuleMatchResult(int matchedConditions, int totalConditions, int maxScoreForRule) {
        this.matchedConditions = matchedConditions;
        this.totalConditions = totalConditions;
        this.maxScoreForRule = maxScoreForRule;
    }

    public int getMatchedConditions() {
        return matchedConditions;
    }

    public int getTotalConditions() {
        return totalConditions;
    }

    public int getMaxScoreForRule() {
        return maxScoreForRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleMatchResult that = (RuleMatchResult) o;
        return matchedConditions == that.matchedConditions && totalConditions == that.totalConditions && maxScoreForRule == that.maxScoreForRule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchedConditions, totalConditions, maxScoreForRule);
    }

    @Override
    public String toString() {
        return "RuleMatchResult{" +
                "matchedConditions=" + matchedConditions +
                ", totalConditions=" + totalConditions +
                ", maxScoreForRule=" + maxScoreForRule +
                '}';
    }
}
