package com.vgb.prules.demo.common.domain.attribute;

import java.io.Serializable;

/**
 * A key value attribute structure than can be matched by value
 * @param <T>
 */
public interface Attribute<T> extends Serializable {

    /**
     * Get attribute name
     * @return
     */
    String getName();

    /**
     * Get attribute value
     * @return
     */
    T getValue();

    /**
     * matches attribute value
     * @param value
     * @return
     */
    boolean matches(T value);

    //define a specific attribute type - helps in streamlining the code
    AttributeConstants.AttributeType getAttributeType();


}
