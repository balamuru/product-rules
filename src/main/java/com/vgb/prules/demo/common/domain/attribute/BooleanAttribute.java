package com.vgb.prules.demo.common.domain.attribute;

public class BooleanAttribute extends AbstractAttribute<Boolean> {

    public BooleanAttribute(String name, boolean value) {
        super(name, value);
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.BOOLEAN;
    }

    @Override
    public String toString() {
        return "BooleanAttribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}