package com.vgb.prules.demo.common.domain.attribute;

public class AttributeConstants {

    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String COLOR = "color";
    public static final String WEIGHT = "weight";
    public static final String PRICE = "price";
    public static final String QTY = "qty";
    public static final String TRENDING = "trending";
    public static final String FDA_APPROVED = "fda-approved";

    public enum AttributeType {
        BOOLEAN, NUMBER, STRING, ENUM
    }

    public enum ProductType {
        CLOTHING, FOOTWEAR, FOOD, MISC
    }
}
