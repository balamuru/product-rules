package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.springframework.stereotype.Service;

@Service
public class StringAttributeEvaluatorService implements AttributeEvaluatorService<StringAttribute> {
    @Override
    public boolean evaluate(StringAttribute conditionAttribute, ComparatorOperator comparatorOperator, StringAttribute actualAttribute) {
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
