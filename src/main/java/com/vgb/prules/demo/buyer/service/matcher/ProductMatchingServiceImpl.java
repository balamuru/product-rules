package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.*;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
            List<RuleMatchResult>  ruleMatchResults = matchRules(product, conditionCount, matchedConditionCount);

            //calculate success metrics for all rules associated with this product
            final int successfulConditionPercentageForAllConditionsInAllRulesForProduct
                    = conditionCount.get() == 0 ?
                    0 : (100 * matchedConditionCount.get()) / conditionCount.get();

            //decide if we have a match depending on if we exceed the success percentage threshold
            final boolean productMatch = successfulConditionPercentageForAllConditionsInAllRulesForProduct > successfulConditionPercentageThreshold;

            //calculate weighed results for this product
            final int actualProductScore = ruleMatchResults.stream().map(result -> result.getTotalConditions() == 0 ? 0 :
                    (result.getMaxScoreForRule()*result.getMatchedConditions())/result.getTotalConditions()).collect(Collectors.summingInt(value -> value));

            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), productMatch, successfulConditionPercentageForAllConditionsInAllRulesForProduct, actualProductScore);
        } catch (Exception e) {
            log.error(e.getMessage());
            //failure, return a false result with 0 score

            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), false, 0, 0);
        }
    }

    private List<RuleMatchResult> matchRules(Product product, AtomicInteger conditionCount, AtomicInteger matchedConditionCount) {
        final List<RuleMatchResult> ruleMatchResults = new ArrayList<>();
        //get rules for product
        final Collection<Rule> rules = productRulesService.getRuleByProductName(product.name());
        //match product attributes for each condition associated with this rule

        //rules.parallelStream().forEach(rule -> rule.getConditions().parallelStream().forEach(targetCondition -> matchConditionAndIncrementCounts(product, targetCondition, conditionCount, matchedConditionCount)));

        //accumulate total count for product
        //record result
        rules.parallelStream().forEach(rule -> {
            final List<Condition> targetConditions = rule.getConditions();
            AtomicInteger totalConditionsForRule = new AtomicInteger(rule.getConditions().size());
            AtomicInteger matchedConditionsForRule = new AtomicInteger(0);
            targetConditions.forEach(targetCondition -> {
                final boolean matchedCondition = matchConditionAndIncrementCounts(product, targetCondition);
                if (matchedCondition) {
                    matchedConditionsForRule.incrementAndGet();
                }
            });
            matchedConditionCount.addAndGet(matchedConditionsForRule.get());
            conditionCount.addAndGet(totalConditionsForRule.get());
            ruleMatchResults.add(new RuleMatchResult(matchedConditionsForRule.get(), totalConditionsForRule.get(), rule.getMaxScore()));
        });

        return ruleMatchResults;
    }

    private boolean matchConditionAndIncrementCounts(Product product, Condition targetCondition) {

        final Attribute targetAttribute = targetCondition.getTargetAttribute();
        final Attribute actualAttribute = product.getAttribute(targetAttribute.getName());

        //match the actual condition
        boolean matched = isMatched(actualAttribute, targetCondition.getComparatorOperator(), targetAttribute);
        if (matched) {
            log.debug("Matched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
        } else {
            log.debug("Mismatched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition + " for product " + product);
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

    public void setSuccessfulConditionPercentageThreshold(int successfulConditionPercentageThreshold) {
        this.successfulConditionPercentageThreshold = successfulConditionPercentageThreshold;
    }

    public void setAttributeEvaluatorService(AttributeEvaluatorService attributeEvaluatorService) {
        this.attributeEvaluatorService = attributeEvaluatorService;
    }
}
