package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.springframework.beans.factory.annotation.Autowired;

public class MasterAttributeEvaluatorService implements AttributeEvaluatorService<Attribute>{

    @Autowired
    BooleanAttributeEvaluatorService booleanAttributeEvaluatorService;

    @Autowired
    NumberAttributeEvaluatorService numberAttributeEvaluatorService;

    @Autowired
    StringAttributeEvaluatorService stringAttributeEvaluatorService;


    @Override
    public boolean evaluate(Attribute conditionAttribute, ComparatorOperator comparatorOperator, Attribute actualAttribute) {

        //it is possible that the attribute might not exist in the document
        if (actualAttribute == null) {
            //TODO: add logs
            return false;
        }

        if (!actualAttribute.getName().equals(conditionAttribute) || actualAttribute.getAttributeType() != conditionAttribute.getAttributeType()) {
            return false;
        }

        switch (conditionAttribute.getAttributeType()) {
            case NUMBER:
                return numberAttributeEvaluatorService.evaluate((NumberAttribute) conditionAttribute, comparatorOperator, (NumberAttribute) actualAttribute);
            case STRING:
                return stringAttributeEvaluatorService.evaluate((StringAttribute) conditionAttribute, comparatorOperator, (StringAttribute) actualAttribute);
            case BOOLEAN:
                return booleanAttributeEvaluatorService.evaluate((BooleanAttribute) conditionAttribute, comparatorOperator, (BooleanAttribute) actualAttribute);
            default:
                throw new UnsupportedOperationException("Unsupported attribute type: " + conditionAttribute.getAttributeType());

        }
    }

}
