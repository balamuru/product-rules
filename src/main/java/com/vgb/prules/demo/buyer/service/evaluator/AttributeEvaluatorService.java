package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.Attribute;

/**
 * Match document attributes against rule condition attribute
 * @param <T>
 */
public interface AttributeEvaluatorService<T extends Attribute> {
    /**
     * Evaluate document attributes against rule condition attribute
     * @param actualAttribute
     * @param comparatorOperator
     * @param conditionAttribute
     * @return
     * @throws MatcherException
     */
    boolean evaluate(T actualAttribute, ComparatorOperator comparatorOperator, T conditionAttribute) throws MatcherException;
}
