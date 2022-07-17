package com.vgb.prules.demo.util;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.engine.MatchingEngine;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.vgb.prules.demo.util.DemoDataUtils.*;

@Component
public class Demo {



    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRulesService productRulesService;

    @Autowired
    private MatchingEngine matchingEngine;

    public void init() {
        addSampleProducts();
        addSampleRules();
    }

    public void run() {
        System.err.println("All Products:");
        productService.getProducts().forEach(product -> System.err.println(product));
        final List<ProductMatchResult> matchedProducts = matchingEngine.match();
        System.err.println();
        System.err.println("Matched Products:");
        matchedProducts.forEach(productMatchResult -> System.err.println(productMatchResult));
//        final Double totalPriceUsingSingleInstanceOfProduct = matchedProducts.stream().map(productMatchResult -> productMatchResult.getPrice()).collect(Collectors.summingDouble(value -> value));
//        final Double totalPrice = matchedProducts.stream().map(productMatchResult -> productMatchResult.getPrice()).collect(Collectors.summingDouble(value -> value));

//        matchedProducts.stream().map(productMatchResult -> productMatchResult.getPrice()*productMatchResult.getQty()).
        final AverageDetailsHelper averageDetailsHelper = new AverageDetailsHelper();
        matchedProducts.forEach(productMatchResult -> {
            averageDetailsHelper.accumulate(productMatchResult.getPrice(), productMatchResult.getQty());
        });

        final int numberOfMatchedProducts = matchedProducts.size();
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
