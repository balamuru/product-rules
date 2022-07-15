package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import org.springframework.stereotype.Service;

@Service
public class NumberAttributeEvaluatorService implements AttributeEvaluatorService<NumberAttribute> {
    @Override
    public boolean evaluate(NumberAttribute conditionAttribute, ComparatorOperator comparatorOperator, NumberAttribute actualAttribute) {
        switch (comparatorOperator) {
            case EQUALS:
                return actualAttribute.getValue() == conditionAttribute.getValue();
            case NOT_EQUALS:
                return actualAttribute.getValue() != conditionAttribute.getValue();
            case GREATER_THAN:
                return actualAttribute.getValue() > conditionAttribute.getValue();
            case LESS_THAN:
                return  actualAttribute.getValue() < conditionAttribute.getValue();
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + comparatorOperator);
        }
    }
}
