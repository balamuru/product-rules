package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StringAttributeEvaluatorService implements AttributeEvaluatorService<StringAttribute> {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean evaluate(StringAttribute actualAttribute, RuleConstants.ComparatorOperator comparatorOperator, StringAttribute conditionAttribute) throws MatcherException {
        if (!actualAttribute.getName().equals(conditionAttribute.getName())) {
            log.error("Mismatching names" + actualAttribute + " vs " + conditionAttribute);
            throw new MatcherException("Mismatching attribute names" + actualAttribute + " vs " + conditionAttribute);
        }
        switch (comparatorOperator) {
            case EQUALS:
                return conditionAttribute.getValue().equals(actualAttribute.getValue());
            case NOT_EQUALS:
                return !conditionAttribute.getValue().equals(actualAttribute.getValue());
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + comparatorOperator);
        }
    }
}
