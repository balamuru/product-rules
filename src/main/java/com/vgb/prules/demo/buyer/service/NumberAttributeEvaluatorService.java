package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import org.springframework.stereotype.Service;

@Service
public class NumberAttributeEvaluatorService implements AttributeEvaluatorService<NumberAttribute> {
    @Override
    public boolean evaluate(NumberAttribute conditionAttribute, ComparatorOperator comparatorOperator, NumberAttribute actualAttribute) {
        switch (comparatorOperator) {
            case EQUALS:
                return conditionAttribute.getValue() == actualAttribute.getValue();
            case NOT_EQUALS:
                return conditionAttribute.getValue() != actualAttribute.getValue();
            case GREATER_THAN:
                return conditionAttribute.getValue() > actualAttribute.getValue();
            case LESS_THAN:
                return conditionAttribute.getValue() < actualAttribute.getValue();
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + comparatorOperator);
        }
    }
}
