package com.vgb.prules.demo.buyer.service.matcher;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.domain.Rule;
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
    private int succesfulConditionPercentageThreshold;

    @Autowired
    @Qualifier("master-attribute-evaluator-service")
    private AttributeEvaluatorService attributeEvaluatorService;

    @Override
    public ProductMatchResult match(Product product) {
        try {
            //                System.err.println("#### evaluating product " + product);
            //TODO: pipeline this
            final Collection<Rule> rules = productRulesService.getRuleByProductName(product.name());

            final AtomicInteger conditionCount = new AtomicInteger(0);
            final AtomicInteger matchedConditionCount = new AtomicInteger(0);
            for (Rule rule : rules) {
                rule.getConditions().parallelStream().forEach(targetCondition -> {
                    {
                        conditionCount.incrementAndGet();
                        final Attribute targetAttribute = targetCondition.getTargetAttribute();
                        final Attribute actualAttribute = product.getAttribute(targetAttribute.getName());
                        boolean matched;
                        try {
                            matched = attributeEvaluatorService.evaluate(actualAttribute, targetCondition.getComparatorOperator(), targetAttribute
                            );
                        } catch (MatcherException e) {
                            matched = false;
                            log.error(e.getMessage());
                        }
                        if (matched) {
                            matchedConditionCount.incrementAndGet();
                        } else {
                            log.debug("Mismatched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition);
                        }
                    }
                });
            }

            final int succesfulConditionPercentage = conditionCount.get() == 0 ?
                    0 : (100 * matchedConditionCount.get()) / conditionCount.get();
            final boolean productMatch = succesfulConditionPercentage > succesfulConditionPercentageThreshold;

            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), productMatch, succesfulConditionPercentage);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ProductMatchResult(product.getId(), product.name(), product.qty(), product.price(), false, 0);
        }
    }
}
