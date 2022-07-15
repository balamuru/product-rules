package com.vgb.prules.demo;

import com.vgb.prules.demo.buyer.domain.Condition;
import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.buyer.repository.ProductRulesRepository;
import com.vgb.prules.demo.buyer.service.RuleService;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;
import com.vgb.prules.demo.seller.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static com.vgb.prules.demo.buyer.domain.ComparatorOperator.*;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;

@Component
public class Demo {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRulesRepository productRulesRepository;

    @Autowired
    private RuleService ruleService;

    public void init() {
        initProductRepository();
        initRulesRepository();


    }

    private void initRulesRepository() {
        //clear
        productRulesRepository.deleteAll();

        //add some dummy data
        productRulesRepository.addProductRules("Nike TShirt", new ArrayList<>() {{
            add(new Rule(100,
                    new ArrayList<>() {{
                        add(new Condition(EQUALS, new StringAttribute(COLOR, "Blue")));
                        add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 20f)));
                        add(new Condition(GREATER_THAN, new NumberAttribute(QTY, 100)));
                    }}
            ));
            add(new Rule(500,
                    new ArrayList<>() {{
                        add(new Condition(EQUALS, new BooleanAttribute(TRENDING, true)));
                    }}
            ));


        }});

        productRulesRepository.addProductRules("Golden Goose Feather", new ArrayList<>() {{
            add(new Rule(
                    100,
                    new ArrayList<>() {{
                        add(new Condition(EQUALS, new StringAttribute(COLOR, "Yellow")));
                        add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 300)));
                        add(new Condition(EQUALS, new NumberAttribute(QTY, 5)));
                    }}
            ));
        }});

    }

    private void initProductRepository() {
        //clear
        productRepository.deleteAll();

        //add some dummy data
        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put(NAME, new StringAttribute(NAME, "Nike TShirt"));
            put(TYPE, new StringAttribute(TYPE, "Clothing"));
            put(COLOR, new StringAttribute(COLOR, "Blue"));
            put(WEIGHT, new NumberAttribute(WEIGHT, 5));
            put(PRICE, new NumberAttribute(PRICE, 17.75f));
            put(QTY, new NumberAttribute(QTY, 1000));
        }}));

        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put(NAME, new StringAttribute(NAME, "Nike TShirt"));
            put(TYPE, new StringAttribute(TYPE, "Clothing"));
            put(COLOR, new StringAttribute(COLOR, "Red"));
            put(WEIGHT, new NumberAttribute(WEIGHT, 5));
            put(PRICE, new NumberAttribute(PRICE, 10.75f));
            put(QTY, new NumberAttribute(QTY, 5000));
        }}));

        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put(NAME, new StringAttribute(NAME, "Reebok Shoe"));
            put(TYPE, new StringAttribute(TYPE, "Footwear"));
            put(COLOR, new StringAttribute(COLOR, "Blue"));
            put(WEIGHT, new NumberAttribute(WEIGHT, 8));
            put(PRICE, new NumberAttribute(PRICE, 10));
            put(QTY, new NumberAttribute(QTY, 2000));
        }}));

        productRepository.addProduct(new Product(null, new HashMap<>() {{
            put(NAME, new StringAttribute(NAME, "Golden Goose Feather"));
            put(TYPE, new StringAttribute(TYPE, "Misc"));
            put(COLOR, new StringAttribute(COLOR, "Yellow"));
            put(WEIGHT, new NumberAttribute(WEIGHT, 0.1f));
            put(PRICE, new NumberAttribute(PRICE, 100));
            put(QTY, new NumberAttribute(QTY, 1));
        }}));
    }


    public void run() {
//        productRepository.getProducts().forEach(product -> System.err.println(product));
//        final Collection<Product> products = productRepository.getProducts("Nike TShirt", "Golden Goose Feather");


        ruleService.matchAllSellerProducts();
    }


}
