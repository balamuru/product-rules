package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import com.vgb.prules.demo.common.domain.attribute.Attribute;

public interface AttributeEvaluatorService<T extends  Attribute> {
    boolean evaluate(T conditionAttribute, ComparatorOperator comparatorOperator, T actualAttribute);
}
