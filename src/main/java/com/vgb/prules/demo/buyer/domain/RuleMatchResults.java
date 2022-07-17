package com.vgb.prules.demo.buyer.domain;

import java.util.ArrayList;
import java.util.List;

public class RuleMatchResults {
    int conditionCount = 0;
    int matchedConditionCount = 0;
    List<RuleMatchResult> ruleMatchResultList = new ArrayList<>();

    public void incrementConditionCount(int i) {
        this.conditionCount += i;
    }

    public void incrementMatchedConditionCount(int i) {
        this.matchedConditionCount += i;
    }

    public int getConditionCount() {
        return conditionCount;
    }

    public int getMatchedConditionCount() {
        return matchedConditionCount;
    }

    public List<RuleMatchResult> getRuleMatchResultList() {
        return ruleMatchResultList;
    }

    public void add(RuleMatchResult ruleMatchResult) {
        ruleMatchResultList.add(ruleMatchResult);
    }

    @Override
    public String toString() {
        return "RuleMatchResults{" +
                "conditionCount=" + conditionCount +
                ", matchedConditionCount=" + matchedConditionCount +
                ", ruleMatchResultList=" + ruleMatchResultList +
                '}';
    }
}
