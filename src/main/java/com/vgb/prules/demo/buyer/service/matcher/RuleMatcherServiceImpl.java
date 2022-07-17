package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.*;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.buyer.service.evaluator.AttributeEvaluatorService;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RuleMatcherServiceImpl implements RuleMatcherService {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProductRulesService productRulesService;

    @Autowired
    @Qualifier("masterAttributeEvaluatorService")
    private AttributeEvaluatorService attributeEvaluatorService;

    @Override
    public RuleMatchResults matchRules(Product product) {
        final RuleMatchResults ruleMatchResults = new RuleMatchResults();
        //get rules for product
        final Collection<Rule> rules = productRulesService.getRuleByProductName(product.name());
        //match product attributes for each condition associated with this rule
        //accumulate total count for product
        //record result
        rules.stream().forEach(rule -> {
            final List<Condition> targetConditions = rule.getConditions();
            AtomicInteger totalConditionsForRule = new AtomicInteger(rule.getConditions().size());
            AtomicInteger matchedConditionsForRule = new AtomicInteger(0);
            targetConditions.forEach(targetCondition -> {
                final boolean matchedCondition = matchConditionAndIncrementCounts(product, targetCondition);
                if (matchedCondition) {
                    matchedConditionsForRule.incrementAndGet();
                }
            });
            ruleMatchResults.incrementMatchedConditionCount(matchedConditionsForRule.get());
            ruleMatchResults.incrementConditionCount(totalConditionsForRule.get());
            ruleMatchResults.add(new RuleMatchResult(matchedConditionsForRule.get(), totalConditionsForRule.get(), rule.getMaxScore()));
        });

        return ruleMatchResults;
    }


    private boolean matchConditionAndIncrementCounts(Product product, Condition targetCondition) {

        final Attribute targetAttribute = targetCondition.getTargetAttribute();
        final Attribute actualAttribute = product.getAttribute(targetAttribute.getName());

        //match the actual condition
        final boolean matched = isMatched(actualAttribute, targetCondition.getComparatorOperator(), targetAttribute);

        if (matched) {
            log.debug("Matched comparison " + " for product/id " + product.name() + "..." + product.getId() +
                    " with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
        } else {
            log.debug("Mismatched comparison " + " for product/id " + product.name() + "..." + product.getId() +
                    " with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
        }
        return matched;
    }

    private boolean isMatched(Attribute actualAttribute, RuleConstants.ComparatorOperator comparatorOperator, Attribute targetAttribute) {
        boolean matched;
        try {
            matched = attributeEvaluatorService.evaluate(actualAttribute, comparatorOperator, targetAttribute);
        } catch (MatcherException e) {
            matched = false;
            log.debug(e.getMessage());
        }
        return matched;
    }

}
