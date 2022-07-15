package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.ComparatorOperator;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("master-attribute-evaluator-service")
public class MasterAttributeEvaluatorService implements AttributeEvaluatorService<Attribute>{

    final Logger log = LoggerFactory.getLogger(this.getClass());
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
            log.warn("No actual attribute found matching " + conditionAttribute);
            return false;
        }

        if (!actualAttribute.getName().equals(conditionAttribute.getName())) {
            log.error("Mismatching names" + actualAttribute + " vs " + conditionAttribute);
            //TODO Throw exception
            return false;
        }

        if (actualAttribute.getAttributeType() != conditionAttribute.getAttributeType()) {
            log.error("Mismatching types" + actualAttribute + " vs " + conditionAttribute);
            //TODO Throw exception
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
