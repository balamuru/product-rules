package com.vgb.prules.demo.util;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.engine.MatchingEngine;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static com.vgb.prules.demo.util.DemoDataUtils.*;

/**
 * Demo driver for Product Rule matcher
 */
@Component
public class Demo {



    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRulesService productRulesService;

    @Autowired
    private MatchingEngine matchingEngine;

    @Value("${rule.success.threshold}")
    private int successfulConditionPercentageThreshold;

    public void init() {
        addSampleProducts();
        addSampleRules();
    }

    public void run() {
        //Get products we're interested in (all in this demo case)
        final Collection<Product> products = productService.getProducts();

        //dump all products
        System.err.println("All Products:");
        products.forEach(product -> System.err.println(product));

        //match all products
        final List<ProductMatchResult> matchedProducts = matchingEngine.match(products, successfulConditionPercentageThreshold);
        System.err.println();

        //print matched products
        System.err.println("Matched Products:");
        matchedProducts.forEach(productMatchResult -> System.err.println(productMatchResult));

        //calculate stats
        final AverageDetailsHelper averageDetailsHelper = new AverageDetailsHelper();
        matchedProducts.forEach(productMatchResult -> {
            averageDetailsHelper.accumulate(productMatchResult.getPrice(), productMatchResult.getQty());
        });

        System.err.println();
        System.err.println("Distinct Averages");
        System.err.println("Total price of all distinct products: " + averageDetailsHelper.getDistinctPriceSum());
        System.err.println("Number of distinct products that pass the conditional filter: " + averageDetailsHelper.getDistinctQty());
        System.err.println("Average price of distinct products: " + averageDetailsHelper.distinctPriceAverage());

        System.err.println();
        System.err.println("Weighted Averages");
        System.err.println("Total price of all products: " + averageDetailsHelper.getWeightedSPriceSum());
        System.err.println("Number of products that pass the conditional filter: " + averageDetailsHelper.getTotalQty());
        System.err.println("Average weighted price of products: " + averageDetailsHelper.weightedPriceAverage());

    }


    private void addSampleRules() {
        //clear
        productRulesService.deleteAll();

        //add some dummy data
        productRulesService.addProductRules("Nike TShirt", RULES_FOR_PRODUCT_TSHIRT);
        productRulesService.addProductRules("Golden Goose Feather", RULES_FOR_PRODUCT_FEATHER);
        productRulesService.addProductRules("Cheezy Bread", RULES_FOR_PRODUCT_BREAD);

    }

    private void addSampleProducts() {
        //clear
        productService.deleteAll();

        //add some dummy data
        PRODUCTS.forEach(product -> productService.addProduct(product));

    }


}
