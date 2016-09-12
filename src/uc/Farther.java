package uc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by kaze on 16-4-25.
 */
public class Farther<E extends AbstractTest> {

    protected Class<E> entityClass;

    Farther() {
        Class c =  getClass();
        Type type = c.getGenericSuperclass();

        if(type instanceof ParameterizedType) {
            entityClass = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }

        System.out.println(entityClass.getSimpleName());
    }

}
