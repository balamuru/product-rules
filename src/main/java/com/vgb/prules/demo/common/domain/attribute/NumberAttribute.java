package com.vgb.prules.demo.common.domain.attribute;

public class NumberAttribute extends AbstractAttribute<Float> {
    public NumberAttribute(String name, float value) {
        super(name, value);
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.NUMBER;
    }

    @Override
    public String toString() {
        return "NumberAttribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
