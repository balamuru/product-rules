package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.Attribute;

public interface AttributeEvaluatorService<T extends Attribute> {
    boolean evaluate(T actualAttribute, ComparatorOperator comparatorOperator, T conditionAttribute) throws MatcherException;
}
