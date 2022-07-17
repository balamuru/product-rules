package com.vgb.prules.demo.common.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;

/**
 * Multi-attribute Product
 */
public class Product implements Serializable {
    private String id;
    private Map<String, Attribute> attributeMap;

    public Product() {
        this(null, new HashMap<>());
    }

    public Product(String id, Map<String, Attribute> attributeMap) {
        this.id = id;
        this.attributeMap = attributeMap;
    }

    /**
     * get attribute by attribute name
     * @param attributeName
     * @return
     */
    public Attribute getAttribute(String attributeName) {
        return attributeMap.getOrDefault(attributeName, null);
    }

    /**
     * Get product id
     * @return
     */
    public String getId() {
        //mandatory
        return id;
    }

    /**
     * Set product id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name attribute
     * @return
     */
    public String name() {
        //mandatory
        return (String) this.attributeMap.get(NAME).getValue();
    }

    /**
     * Get available quantity attribute
     * @return
     */
    public float qty() {
        //mandatory
        return (Float) this.attributeMap.get(QTY).getValue();
    }

    /**
     * Get price attribute
     * @return
     */
    public float price() {
        //mandatory
        return (Float) this.attributeMap.get(PRICE).getValue();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", attributeMap=" + attributeMap +
                '}';
    }
}
