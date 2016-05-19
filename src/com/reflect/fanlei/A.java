package com.reflect.fanlei;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class A<E> {

    private Class entityClass;

    public A() {
        Type type = ((Type) getClass().getGenericSuperclass());
        if (type instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class) p[0];
        }
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
