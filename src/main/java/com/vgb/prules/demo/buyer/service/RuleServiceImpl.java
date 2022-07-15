package com.vgb.prules.demo.buyer.service;

import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.buyer.repository.ProductRulesRepository;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.Attribute;
import com.vgb.prules.demo.seller.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RuleServiceImpl implements RuleService {
    final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRulesRepository productRulesRepository;

    @Value("${rule.success.threshold}")
    private int succesfulConditionPercentageThreshold;

    @Autowired
    @Qualifier("master-attribute-evaluator-service")
    private AttributeEvaluatorService attributeEvaluatorService;

    @Override
    public void matchAllSellerProducts() {
        //TODO: make concurrent
        final Collection<Product> products = productRepository.getProducts();

        //TODO: parallel stream
        products.parallelStream().forEach(product -> {
            {
//                System.err.println("#### evaluating product " + product);
                //TODO: pipeline this
                final Collection<Rule> rules = productRulesRepository.getRuleByProductName(product.name());

                final AtomicInteger conditionCount = new AtomicInteger(0);
                final AtomicInteger matchedConditionCount = new AtomicInteger(0);
                for (Rule rule : rules) {
                    rule.getConditions().parallelStream().forEach(targetCondition -> {
                        {
                            conditionCount.incrementAndGet();
                            final Attribute targetAttribute = targetCondition.getTargetAttribute();
                            final Attribute actualAttribute = product.getAttribute(targetAttribute.getName());
                            final boolean matched = attributeEvaluatorService.evaluate(targetAttribute,
                                    targetCondition.getComparatorOperator(), actualAttribute);

//                            System.err.println(" #### " + matched +
//                                    " ATTEMPTED comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition);
                            if (matched) {
                                matchedConditionCount.incrementAndGet();
                            } else {
                                log.info("Mismatched comparison with actual attribute: " + actualAttribute + " vs target condition: " + targetCondition);
                            }
                        }
                    });
                }

                final int succesfulConditionPercentage = conditionCount.get() == 0 ?
                        0 : (100 * matchedConditionCount.get()) / conditionCount.get();
//                System.err.println("###------------------------- " + succesfulConditionPercentage + " for " + product.name());
                if (succesfulConditionPercentage > succesfulConditionPercentageThreshold) {
                    System.err.println("matched rule for product " + product.getId() + " - " + product.name() + " with success threshold " + succesfulConditionPercentage);
                } else {
                    System.err.println("didn't match rule for product " + product.getId() + " - " + product.name() + " with success threshold " + succesfulConditionPercentage);
                }

            }
        });
    }
}
