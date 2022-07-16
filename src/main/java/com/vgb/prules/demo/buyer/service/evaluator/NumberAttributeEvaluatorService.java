package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NumberAttributeEvaluatorService implements AttributeEvaluatorService<NumberAttribute> {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean evaluate(NumberAttribute conditionAttribute, RuleConstants.ComparatorOperator comparatorOperator, NumberAttribute actualAttribute) throws MatcherException {
        if (!actualAttribute.getName().equals(conditionAttribute.getName())) {
            log.error("Mismatching names" + actualAttribute + " vs " + conditionAttribute);
            throw new MatcherException("Mismatching attribute names" + actualAttribute + " vs " + conditionAttribute);
        }
        switch (comparatorOperator) {
            case EQUALS:
                return actualAttribute.getValue() == conditionAttribute.getValue();
            case NOT_EQUALS:
                return actualAttribute.getValue() != conditionAttribute.getValue();
            case GREATER_THAN:
                return actualAttribute.getValue() > conditionAttribute.getValue();
            case LESS_THAN:
                return actualAttribute.getValue() < conditionAttribute.getValue();
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + comparatorOperator);
        }
    }
}
