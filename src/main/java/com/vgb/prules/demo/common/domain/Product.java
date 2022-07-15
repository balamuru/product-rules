package com.vgb.prules.demo.common.domain;

import com.vgb.prules.demo.common.domain.attribute.Attribute;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttributeMap(Map<String, Attribute> attributeMap) {
        this.attributeMap = attributeMap;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", attributeMap=" + attributeMap +
                '}';
    }
}
