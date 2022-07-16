package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.Condition;
import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.buyer.domain.RuleConstants;
import com.vgb.prules.demo.buyer.exception.MatcherException;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.buyer.service.evaluator.AttributeEvaluatorService;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import com.vgb.prules.demo.seller.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductMatchingServiceImpl implements ProductMatchingService {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRulesService productRulesService;

    @Value("${rule.success.threshold}")
    private int successfulConditionPercentageThreshold;

    @Autowired
    @Qualifier("masterAttributeEvaluatorService")
    private AttributeEvaluatorService attributeEvaluatorService;

    @Override
    public ProductMatchResult match(Product product) {
        try {
            final AtomicInteger conditionCount = new AtomicInteger(0);
            final AtomicInteger matchedConditionCount = new AtomicInteger(0);

            //match rules for product
            matchRules(product, conditionCount, matchedConditionCount);

            //calculate success metrics for all rules associated with this product
            final int successfulConditionPercentage = conditionCount.get() == 0 ?
                    0 : (100 * matchedConditionCount.get()) / conditionCount.get();

            //decide if we have a match depending on if we exceed the succes percentage threshold
            final boolean productMatch = successfulConditionPercentage > successfulConditionPercentageThreshold;

            //return aggregated results for this product
            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), productMatch, successfulConditionPercentage);
        } catch (Exception e) {
            log.error(e.getMessage());
            //failure, return a false result with 0 score
            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), false, 0);
        }
    }

    private void matchRules(Product product, AtomicInteger conditionCount, AtomicInteger matchedConditionCount) {
        //get rules for product
        final Collection<Rule> rules = productRulesService.getRuleByProductName(product.name());
        //match product attributes for each condition associated with this rule
        rules.parallelStream().forEach(rule -> rule.getConditions().parallelStream().forEach(targetCondition -> matchConditionAndIncrementCounts(product, targetCondition, conditionCount, matchedConditionCount)));
    }

    private void matchConditionAndIncrementCounts(Product product, Condition targetCondition, AtomicInteger conditionCount, AtomicInteger matchedConditionCount) {
        conditionCount.incrementAndGet();
        final Attribute targetAttribute = targetCondition.getTargetAttribute();
        final Attribute actualAttribute = product.getAttribute(targetAttribute.getName());

        //match the actual condition
        boolean matched = isMatched(actualAttribute, targetCondition.getComparatorOperator(), targetAttribute);
        if (matched) {
            matchedConditionCount.incrementAndGet();
            log.debug("Matched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
        } else {
            log.debug("Mismatched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
        }
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

    public void setSuccessfulConditionPercentageThreshold(int successfulConditionPercentageThreshold) {
        this.successfulConditionPercentageThreshold = successfulConditionPercentageThreshold;
    }

    public void setAttributeEvaluatorService(AttributeEvaluatorService attributeEvaluatorService) {
        this.attributeEvaluatorService = attributeEvaluatorService;
    }
}
