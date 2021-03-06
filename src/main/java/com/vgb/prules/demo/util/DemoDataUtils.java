package com.vgb.prules.demo.util;

import com.vgb.prules.demo.buyer.domain.Condition;
import com.vgb.prules.demo.buyer.domain.Rule;
import com.vgb.prules.demo.common.domain.Product;
import com.vgb.prules.demo.common.domain.attribute.BooleanAttribute;
import com.vgb.prules.demo.common.domain.attribute.EnumeratedAttribute;
import com.vgb.prules.demo.common.domain.attribute.NumberAttribute;
import com.vgb.prules.demo.common.domain.attribute.StringAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.vgb.prules.demo.buyer.domain.RuleConstants.ComparatorOperator.*;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;
import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.ProductType.*;

/**
 * Sample data for demo
 */
public class DemoDataUtils {


    public static final Product PRODUCT1 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Nike TShirt"));
        put(TYPE, new EnumeratedAttribute(TYPE, CLOTHING));
        put(COLOR, new StringAttribute(COLOR, "Blue"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 5));
        put(PRICE, new NumberAttribute(PRICE, 17.75f));
        put(QTY, new NumberAttribute(QTY, 1000));
    }});
    public static final Product PRODUCT2 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Nike TShirt"));
        put(TYPE, new EnumeratedAttribute(TYPE, CLOTHING));
        put(COLOR, new StringAttribute(COLOR, "Red"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 5));
        put(PRICE, new NumberAttribute(PRICE, 40.75f));
        put(QTY, new NumberAttribute(QTY, 5000));
    }});
    public static final Product PRODUCT3 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Reebok Shoe"));
        put(TYPE, new EnumeratedAttribute(TYPE, FOOTWEAR));
        put(COLOR, new StringAttribute(COLOR, "Blue"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 8));
        put(PRICE, new NumberAttribute(PRICE, 10));
        put(QTY, new NumberAttribute(QTY, 2000));
    }});
    public static final Product PRODUCT4 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Golden Goose Feather"));
        put(TYPE, new EnumeratedAttribute(TYPE, MISC));
        put(COLOR, new StringAttribute(COLOR, "Yellow"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 0.1f));
        put(PRICE, new NumberAttribute(PRICE, 100));
        put(QTY, new NumberAttribute(QTY, 1));
    }});
    public static final Product PRODUCT5 = new Product(null, new HashMap<>() {{
        put(NAME, new StringAttribute(NAME, "Cheezy Bread"));
        put(TYPE, new EnumeratedAttribute(TYPE, FOOD));
        put(COLOR, new StringAttribute(COLOR, "Yellow"));
        put(WEIGHT, new NumberAttribute(WEIGHT, 1f));
        put(PRICE, new NumberAttribute(PRICE, 1));
        put(FDA_APPROVED, new BooleanAttribute(FDA_APPROVED, false));
        put(QTY, new NumberAttribute(QTY, 10000));
    }});

    public static final Rule RULE1A = new Rule("product-1/rule-a", 100,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new StringAttribute(COLOR, "Blue")));
                add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 20f)));
                add(new Condition(GREATER_THAN, new NumberAttribute(QTY, 100)));
            }}
    );
    public static final Rule RULE1B = new Rule("product-1/rule-b", 500,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new BooleanAttribute(TRENDING, true)));
                add(new Condition(EQUALS, new EnumeratedAttribute(TYPE, CLOTHING)));

            }}
    );
    public static final Rule RULE2 = new Rule(
            "product-2/rule", 100,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new StringAttribute(COLOR, "Yellow")));
                add(new Condition(LESS_THAN, new NumberAttribute(PRICE, 300)));
                add(new Condition(EQUALS, new NumberAttribute(QTY, 5)));
            }}
    );

    public static final Rule RULE3A = new Rule(
            "product-3/rule-a",
            200,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new StringAttribute(COLOR, "Yellow")));
                add(new Condition(GREATER_THAN, new NumberAttribute(QTY, 5000)));
            }}
    );

    public static final Rule RULE3B = new Rule(
            "product-3/rule-b",
            -1000,
            new ArrayList<>() {{
                add(new Condition(EQUALS, new BooleanAttribute(FDA_APPROVED, false)));
            }}
    );

    public static final List<Product> PRODUCTS = new ArrayList<>() {{
        add(PRODUCT1);
        add(PRODUCT2);
        add(PRODUCT3);
        add(PRODUCT4);
        add(PRODUCT5);
    }};

    public static final ArrayList<Rule> RULES_FOR_PRODUCT_FEATHER = new ArrayList<>() {{
        add(RULE2);
    }};
    public static final ArrayList<Rule> RULES_FOR_PRODUCT_TSHIRT = new ArrayList<>() {{
        add(RULE1A);
        add(RULE1B);
    }};
    public static final ArrayList<Rule> RULES_FOR_PRODUCT_BREAD = new ArrayList<>() {{
        add(RULE3A);
        add(RULE3B);
    }};


}
