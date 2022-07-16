package com.vgb.prules.demo.util;

import com.vgb.prules.demo.buyer.domain.ProductMatchResult;
import com.vgb.prules.demo.buyer.engine.MatchingEngine;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        final List<ProductMatchResult> matchedProducts = matchingEngine.match();
        matchedProducts.forEach(productMatchResult -> System.err.println(productMatchResult));
        final Double totalPrice = matchedProducts.stream().map(productMatchResult -> productMatchResult.getPrice()).collect(Collectors.summingDouble(value -> value));
        final int numberOfMatchedProducts = matchedProducts.size();
        System.err.println();
        System.err.println("Total price of all products, chosen at quantity 1 each): " + totalPrice);
        System.err.println("Number of distinct products that pass the conditional filter: " + numberOfMatchedProducts);
        System.err.println("Average price of products: " + (numberOfMatchedProducts == 0 ? 0 : totalPrice / numberOfMatchedProducts));
    }


    private void addSampleRules() {
        //clear
        productRulesService.deleteAll();

        //add some dummy data
        productRulesService.addProductRules("Nike TShirt", new ArrayList<>() {{
            add(RULE1A);
            add(RULE1B);
        }});

        productRulesService.addProductRules("Golden Goose Feather", new ArrayList<>() {{
            add(RULE2);
        }});

    }

    private void addSampleProducts() {
        //clear
        productService.deleteAll();

        //add some dummy data
        productService.addProduct(PRODUCT1);
        productService.addProduct(PRODUCT2);
        productService.addProduct(PRODUCT3);
        productService.addProduct(PRODUCT4);
    }


}
