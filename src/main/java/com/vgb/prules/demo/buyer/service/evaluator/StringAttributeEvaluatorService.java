package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.springframework.stereotype.Service;

@Service
public class StringAttributeEvaluatorService implements AttributeEvaluatorService<StringAttribute> {
    @Override
    public boolean evaluate(StringAttribute conditionAttribute, RuleConstants.ComparatorOperator comparatorOperator, StringAttribute actualAttribute) {
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
