package com.vgb.prules.demo.common.domain.attribute;

import java.io.Serializable;

public interface Attribute <T> extends Serializable {
    String getName();
    T getValue();
    boolean matches(T value);
    AttributeConstants.AttributeType getAttributeType();


}
