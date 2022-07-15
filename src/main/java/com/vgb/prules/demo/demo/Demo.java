package com.vgb.prules.demo.demo;

import com.vgb.prules.demo.buyer.engine.MatchingEngine;
import com.vgb.prules.demo.buyer.service.ProductRulesService;
import com.vgb.prules.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.vgb.prules.demo.demo.DemoDataUtils.*;

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
        matchingEngine.match();
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
