package com.vgb.prules.demo.common.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.vgb.prules.demo.common.domain.attribute.AttributeConstants.*;

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

    public void addAttribute(Attribute attribute) {
        this.attributeMap.put(attribute.getName(), attribute);
    }

    public Map<String, Attribute> getAttributeMap() {
        return Collections.unmodifiableMap(attributeMap);
    }

    public void setAttributeMap(Map<String, Attribute> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public Attribute getAttribute(String attributeName) {
        return attributeMap.getOrDefault(attributeName, null);
    }

    public String getId() {
        //mandatory
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String name() {
        //mandatory
        return (String) this.attributeMap.get(NAME).getValue();
    }

    public float qty() {
        //mandatory
        return (Float) this.attributeMap.get(QTY).getValue();
    }

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
