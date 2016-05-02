package ipack;

public class KeyValueTabService<T extends PackKeyValueTab> {

    private Class<T> entityClass;

    KeyValueTabService() {
        this.entityClass = null;
        Class c = getClass();
        Type t = c.getGenericSuperclass();
        System.out.println(t);
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
    }