package com.vgb.prules.demo.common.domain.attribute;

public class EnumeratedAttribute<E extends Enum<E>> extends AbstractAttribute<E> {
    public EnumeratedAttribute(String name, E value) {
        super(name, value);
    }

    @Override
    public AttributeConstants.AttributeType getAttributeType() {
        return AttributeConstants.AttributeType.ENUM;
    }

}
