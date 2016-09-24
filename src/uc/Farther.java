package uc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.List;

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

    public AbstractCollection test() {
        return null;
    }

    protected List test(int i) {
        return null;
    }

    protected List test(int i, long c) {
        return null;
    }

    private List test(long c, int i) {
        return null;
    }

}
