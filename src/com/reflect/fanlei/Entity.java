package com.reflect.fanlei;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by uc on 2016/3/21.
 */
public abstract class Entity<T> {

    private Class<T> entityClass;

    public Entity() {
        Type genType = (Type) getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
