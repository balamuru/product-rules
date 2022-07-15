package com.vgb.prules.demo.common.domain.attribute;

import java.util.Objects;

public abstract class AbstractAttribute<T> implements Attribute<T>{
    protected final String name;
    protected final T value;

    protected AbstractAttribute(String name, T value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public boolean matches(T value) {
        return this.value.equals(value);
    }

    @Override
    public String toString() {
        return "AbstractAttribute{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAttribute<?> that = (AbstractAttribute<?>) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
