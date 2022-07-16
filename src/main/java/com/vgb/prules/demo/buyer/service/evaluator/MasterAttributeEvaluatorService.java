package com.vgb.prules.demo.buyer.service.evaluator;

import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("master-attribute-evaluator-service")
public class MasterAttributeEvaluatorService implements AttributeEvaluatorService<Attribute> {

    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BooleanAttributeEvaluatorService booleanAttributeEvaluatorService;

    @Autowired
    NumberAttributeEvaluatorService numberAttributeEvaluatorService;

    @Autowired
    StringAttributeEvaluatorService stringAttributeEvaluatorService;


    @Override
    public boolean evaluate(Attribute actualAttribute, RuleConstants.ComparatorOperator comparatorOperator, Attribute conditionAttribute) throws MatcherException {

        //it is possible that the attribute might not exist in the document
        if (actualAttribute == null) {
            log.debug("No actual attribute found matching " + conditionAttribute);
            return false;
        }

        if (!actualAttribute.getName().equals(conditionAttribute.getName())) {
            log.error("Mismatching names" + actualAttribute + " vs " + conditionAttribute);
            throw new MatcherException("Mismatching attribute names" + actualAttribute + " vs " + conditionAttribute);
        }

        if (actualAttribute.getAttributeType() != conditionAttribute.getAttributeType()) {
            throw new MatcherException("Mismatching attribute types" + actualAttribute + " vs " + conditionAttribute);
        }
        switch (conditionAttribute.getAttributeType()) {
            case NUMBER:
                return numberAttributeEvaluatorService.evaluate((NumberAttribute) actualAttribute, comparatorOperator, (NumberAttribute) conditionAttribute);
            case STRING:
                return stringAttributeEvaluatorService.evaluate((StringAttribute) actualAttribute, comparatorOperator, (StringAttribute) conditionAttribute);
            case BOOLEAN:
                return booleanAttributeEvaluatorService.evaluate((BooleanAttribute) actualAttribute, comparatorOperator, (BooleanAttribute) conditionAttribute);
            default:
                throw new UnsupportedOperationException("Unsupported attribute type: " + conditionAttribute.getAttributeType());

        }
    }

}
