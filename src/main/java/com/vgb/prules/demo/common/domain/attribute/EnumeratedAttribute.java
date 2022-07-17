package com.vgb.prules.demo.common.domain.attribute;

/**
 * Enum valued attribute. The actual enum type is bound at instantiation
 */

public class EnumeratedAttribute<E extends Enum<E>> extends AbstractAttribute<E> {
    public EnumeratedAttribute(String name, E value) {
        super(name, value);
    }

    @Override
    public AttributeConstants.AttributeType getAttributeType() {
        return AttributeConstants.AttributeType.ENUM;
    }

}
