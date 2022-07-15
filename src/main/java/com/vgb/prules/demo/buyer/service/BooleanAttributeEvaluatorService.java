package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import org.springframework.stereotype.Service;

@Service
public class BooleanAttributeEvaluatorService implements AttributeEvaluatorService<BooleanAttribute> {
    @Override
    public boolean evaluate(BooleanAttribute conditionAttribute, ComparatorOperator comparatorOperator, BooleanAttribute actualAttribute) {
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
