package com.vgb.prules.demo.demo;

import com.vgb.prules.demo.buyer.domain.Condition;
import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;

import java.util.ArrayList;
import java.util.HashMap;

import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.*;
import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.EQUALS;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.QTY;

public class DemoDataUtils {
    public static final Product PRODUCT1 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Nike TShirt"));
        put(TYPE, new StringAttribute(TYPE, "Clothing"));
        put(COLOR, new StringAttribute(COLOR, "Blue"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 5));
        put(PRICE, new NumberAttribute(PRICE, 17.75f));
        put(QTY, new NumberAttribute(QTY, 1000));
    }});
    public static final Product PRODUCT2 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Nike TShirt"));
        put(TYPE, new StringAttribute(TYPE, "Clothing"));
        put(COLOR, new StringAttribute(COLOR, "Red"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 5));
        put(PRICE, new NumberAttribute(PRICE, 10.75f));
        put(QTY, new NumberAttribute(QTY, 5000));
    }});
    public static final Product PRODUCT3 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Reebok Shoe"));
        put(TYPE, new StringAttribute(TYPE, "Footwear"));
        put(COLOR, new StringAttribute(COLOR, "Blue"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 8));
        put(PRICE, new NumberAttribute(PRICE, 10));
        put(QTY, new NumberAttribute(QTY, 2000));
    }});
    public static final Product PRODUCT4 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Golden Goose Feather"));
        put(TYPE, new StringAttribute(TYPE, "Misc"));
        put(COLOR, new StringAttribute(COLOR, "Yellow"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 0.1f));
        put(PRICE, new NumberAttribute(PRICE, 100));
        put(QTY, new NumberAttribute(QTY, 1));
    }});

    public static final Rule RULE1A = new Rule(100,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new StringAttribute(COLOR, "Blue")));
                add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 20f)));
                add(new Condition(GREATER_THAN, new NumberAttribute(QTY, 100)));
            }}
    );
    public static final Rule RULE1B = new Rule(500,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new BooleanAttribute(TRENDING, true)));
            }}
    );
    public static final Rule RULE2 = new Rule(
            100,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new StringAttribute(COLOR, "Yellow")));
                add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 300)));
                add(new Condition(EQUALS, new NumberAttribute(QTY, 5)));
            }}
    );
}
