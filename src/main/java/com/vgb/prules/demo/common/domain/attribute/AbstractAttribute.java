package com.vgb.prules.demo.common.domain.attribute;

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
}
