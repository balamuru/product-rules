package com.vgb.prules.demo.common.domain.attribute;

/**
 * Number(float) valued attribute
 */

public class NumberAttribute extends AbstractAttribute<Float> {
    public NumberAttribute(String name, float value) {
        super(name, value);
    }

    @Override
    public AttributeConstants.AttributeType getAttributeType() {
        return AttributeConstants.AttributeType.NUMBER;
    }

    @Override
    public String toString() {
        return "NumberAttribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
