package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.domain.RuleMatchResults;
import com.vgb.prules.demo.common.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ProductMatchingServiceImpl implements ProductMatchingService {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RuleMatcherService ruleMatcherService;

    @Override
    public ProductMatchResult match(Product product, int successfulConditionPercentageThreshold) {
        try {
            final AtomicInteger conditionCount = new AtomicInteger(0);
            final AtomicInteger matchedConditionCount = new AtomicInteger(0);

            //match rules for product and increment counts
            final RuleMatchResults ruleMatchResults = ruleMatcherService.matchRules(product);
            conditionCount.addAndGet(ruleMatchResults.getConditionCount());
            matchedConditionCount.addAndGet(ruleMatchResults.getMatchedConditionCount());

            //calculate success metrics for all rules associated with this product
            final float successfulConditionPercentageForAllConditionsInAllRulesForProduct
                    = conditionCount.get() == 0 ?
                    0 : (float) (100 * matchedConditionCount.get()) / conditionCount.get();

            //decide if we have a match depending on if we exceed the success percentage threshold
            final boolean productMatch = successfulConditionPercentageForAllConditionsInAllRulesForProduct > successfulConditionPercentageThreshold;

            //calculate weighed results for this product
            final double actualProductScore = ruleMatchResults.getRuleMatchResultList().stream().map(result -> result.getTotalConditions() == 0 ? 0 :
                    (result.getMaxScoreForRule() * result.getMatchedConditions()) / result.getTotalConditions()).collect(Collectors.summingDouble(value -> (double) value));

            return new ProductMatchResult(product.getId(), product.name(), (int) product.qty(), product.price(), productMatch, successfulConditionPercentageForAllConditionsInAllRulesForProduct, (float) actualProductScore);
        } catch (Exception e) {
            log.error(e.getMessage());
            //failure, return a false result with 0 score

            return new ProductMatchResult(product.getId(), product.name(), (int) product.qty(), product.price(), false, 0, 0);
        }
    }


}
