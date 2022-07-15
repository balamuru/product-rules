package com.vgb.prules.demo.common.domain.attribute;

public class StringAttribute extends AbstractAttribute<String> {

    public StringAttribute(String name, String value) {
        super(name, value);
    }

    @Override
    public AttributeConstants.AttributeType getAttributeType() {
        return AttributeConstants.AttributeType.STRING;
    }

    @Override
    public String toString() {
        return "StringAttribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
