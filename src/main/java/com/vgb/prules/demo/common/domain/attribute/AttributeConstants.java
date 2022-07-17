package com.vgb.prules.demo.common.domain.attribute;

/**
 * Attribute constants
 */
public class AttributeConstants {

    /**
     * Attribute key names
     */
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String COLOR = "color";
    public static final String WEIGHT = "weight";
    public static final String PRICE = "price";
    public static final String QTY = "qty";
    public static final String TRENDING = "trending";
    public static final String FDA_APPROVED = "fda-approved";

    /**
     * Attribute types
     */
    public enum AttributeType {
        BOOLEAN, NUMBER, STRING, ENUM
    }

    /**
     * Product Category types
     */
    public enum ProductType {
        CLOTHING, FOOTWEAR, FOOD, MISC
    }
}
