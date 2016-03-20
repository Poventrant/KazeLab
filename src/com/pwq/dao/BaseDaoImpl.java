package com.pwq.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * @title 公用数据访问DaoImpl实现,实现基本的增删改查
 * @author laizhilong
 * @Date:2015年9月11日
 * @param <T>
 *            T 为需要持久化的实体类型，具体的业务Dao须指定具体实体类
 */
@SuppressWarnings("all")
public abstract class BaseDaoImpl<T> {
    // 每个具体传入的实体类型
    protected Class<T> entityClass;

    protected final Logger log = Logger.getLogger(BaseDao.class);

    private static Map<String, Method> MAP_METHOD = new HashMap<String, Method>();

    /*
     * 初始化entityClass对象
     */
    public BaseDaoImpl() {
        Type genType = (Type) getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Resource
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 立即加载单个实体，所有属性都取出
     *
     * @param id
     * @return
     */
    public T getObject(Serializable id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 获取所有
     */
    public List<T> getObjects() {
        String hql = "FROM " + entityClass.getSimpleName() + " AS "
                + entityClass.getSimpleName().substring(0, 1).toLowerCase();
        List<T> list = null;
        try {
            list = getSession().createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取全部失败!", e);
        }
        return list;
    }

    /**
     * 根据条件加载查询List列表
     *
     * @param T
     * @return 返回LIST
     */
    public List<T> getObjectsByQuery(String query_str) {
        String hql = "FROM " + (entityClass.getSimpleName()) + " AS "
                + entityClass.getSimpleName().substring(0, 1).toLowerCase() + " WHERE 1=1 "
                + query_str;
        System.out.println("starstar");
        System.out.println((entityClass.getSimpleName()).toLowerCase());
        return getSession().createQuery(hql).list();
    }

    /**
     * 添加实体
     *
     * @param entity
     * @return
     */
    public Serializable insertObject(T entity) {
        return getSession().save(entity);
    }

    /**
     * 删除实体
     *
     * @param id
     */
    public void deleteObject(Serializable id) {
        T entity = load(id);
        getSession().delete(entity);
    }

    /**
     * 批处理删除
     *
     * @query_str 待删除ID集合的字符串形式
     */
    public void deleteObjects(String query_str) {
        String hql = "DELETE FROM " + entityClass.getSimpleName() + " AS "
                + entityClass.getSimpleName().substring(0, 1).toLowerCase() + " WHERE 1=1 "
                + query_str;
        getSession().createQuery(hql).executeUpdate();
    }

    /**
     * 逻辑删除(可处理批处理删除)
     *
     * @query_str 待删除ID集合的字符串形式
     */
    public void logicDelete(String query_str) {
        String lowerCase = entityClass.getSimpleName().substring(0, 1).toLowerCase();
        String hql = "UPDATE " + entityClass.getSimpleName() + " AS " + lowerCase + " SET "
                + lowerCase + query_str;
        getSession().createQuery(hql).executeUpdate();
    }

    /**
     * 更新一个实体
     */
    public void updateObject(T value) {
        try {
            getSession().update(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败!", e);
        }
    }

    /**
     * 根据hql语句更新实体
     */
    @Deprecated
    public void updateObjectByHql(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        query.executeUpdate();
    }

    /**
     * 根据hql语句更新实体
     */
    public void updateObjectByHql(String hql, List<Object> values) {
        try {
            Query query = getSession().createQuery(hql);
            for (int i = 0; i < values.size(); i++) {
                query.setParameter(i, values.get(i));
            }
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // 以下为分页代码

    /*
     * 根据查询条件获取总记录数
     *
     * @param query_str 条件字符串
     */
    public int getObjectCount(final String query_str) throws HibernateException {
        StringBuffer queryTemp = new StringBuffer();
        queryTemp.append("SELECT count(*) FROM " + entityClass.getSimpleName() + " AS "
                + entityClass.getSimpleName().substring(0, 1).toLowerCase() + " WHERE 1=1");//
        queryTemp.append(query_str);
        System.out.println(queryTemp.toString());
        Long new_count = 0L;
        Query query = getSession().createQuery(queryTemp.toString());
        new_count = ((Long) query.iterate().next()).longValue();
        return new_count.intValue();
    }

    /**
     * hql查询
     */
    public T getFetchObject(String hql) {
        T t = null;
        try {
            t = (T) getSession().createQuery(hql).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("级联获取失败!", e);
        }
        return t;
    }

    /**
     * 级联抓取，获取结果数
     *
     * @param hql
     * @return
     */
    public int getFetchCount(String hql) {
        Query query = getSession().createQuery(hql);
        int num = ((Long) query.iterate().next()).intValue();
        return num;
    }

    /**
     * 级联抓取，返回一个集合
     */
    public List<T> getFetchObjects(String hql) {
        Session session = null;
        List<T> list = null;
        try {
            list = (List<T>) getSession().createQuery(hql).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("级联获取List失败!", e);
        }
        return list;
    }

    /**
     * 根据sql语句自定义封装成实体,entity为需要封装的实体
     *
     * @param sql
     *            sql语句
     * @param propName
     *            实体变量名数组
     * @param propType
     *            实体变量类型数组
     * @return
     */
    public T getCustomEntity(String sql, String[] propName, org.hibernate.type.Type[] propType) {
        T t = null;
        try {
            SQLQuery query = getSession().createSQLQuery(sql);
            if (propName.length != 0 && propType.length == propName.length) {
                for (int i = 0; i < propName.length; i++) {
                    query.addScalar(propName[i], propType[i]);
                }
            }
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(entityClass));
            t = (T) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public T findById(Serializable entityId) {
        return (T) getSession().get(entityClass, entityId);
    };

    public T get(Serializable id) {
        return (T) getSession().get(entityClass, id);
    }

    public T load(Serializable id) {
        return (T) getSession().load(entityClass, id);
    }

    public List<T> criteriaEqeals(String propName, Object propVal) {
        Criteria criteria = getSession().createCriteria(entityClass);
        List<T> list = criteria.add(Restrictions.eq(propName, propVal)).list();
        return list;
    }

    public Serializable persist(T entity) {
        Serializable res = null;
        try {
            res = getSession().save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void persistOrUpdate(T entity) {
        try {
            getSession().saveOrUpdate(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteByPK(Serializable... id) {
        boolean result = false;
        if (id != null && id.length > 0) {
            for (int i = 0; i < id.length; i++) {
                T entity = get(id[i]);
                if (entity != null) {
                    getSession().delete(entity);
                    result = true;
                }
            }
        }
        return result;
    }

    public void deleteByProperties(String[] propName, Object[] propValue) {
        if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0
                && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("delete from " + entityClass.getSimpleName()
                    + " o where 1=1 ");
            appendQL(sb, propName, propValue);
            Query query = getSession().createQuery(sb.toString());
            setParameter(query, propName, propValue);
            query.executeUpdate();
        }
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void deleteByProperties(String propName, Object propValue) {
        deleteByProperties(new String[] { propName }, new Object[] { propValue });
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue,
                                   String[] propertyName, Object[] propertyValue) {
        if (propertyName != null && propertyName.length > 0 && propertyValue != null
                && propertyValue.length > 0 && propertyName.length == propertyValue.length
                && conditionValue != null && conditionValue.length > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("update " + entityClass.getSimpleName() + " o set ");
            for (int i = 0; i < propertyName.length; i++) {
                sb.append(propertyName[i] + " = :p_" + propertyName[i] + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" where 1=1 ");
            appendQL(sb, conditionName, conditionValue);
            Query query = getSession().createQuery(sb.toString());
            for (int i = 0; i < propertyName.length; i++) {
                query.setParameter("p_" + propertyName[i], propertyValue[i]);
            }
            setParameter(query, conditionName, conditionValue);
            query.executeUpdate();
        } else {
            throw new IllegalArgumentException(
                    "Method updateByProperties in BaseDao argument is illegal!");
        }
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue,
                                   String propertyName, Object propertyValue) {
        updateByProperties(conditionName, conditionValue, new String[] { propertyName },
                new Object[] { propertyValue });
    }

    public void updateByProperties(String conditionName, Object conditionValue,
                                   String[] propertyName, Object[] propertyValue) {
        updateByProperties(new String[] { conditionName }, new Object[] { conditionValue },
                propertyName, propertyValue);
    }

    public void updateByProperties(String conditionName, Object conditionValue,
                                   String propertyName, Object propertyValue) {
        updateByProperties(new String[] { conditionName }, new Object[] { conditionValue },
                new String[] { propertyName }, new Object[] { propertyValue });
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void update(T entity, Serializable oldId) {
        deleteByPK(oldId);
        persist(entity);
    }

    public T merge(T entity) {
        return (T) getSession().merge(entity);
    }

    public T getByProperties(String[] propName, Object[] propValue,
                             Map<String, String> sortedCondition) {
        if (propName != null && propName.length > 0 && propValue != null && propValue.length > 0
                && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("select o from " + entityClass.getSimpleName()
                    + " o where 1=1 ");
            appendQL(sb, propName, propValue);
            if (sortedCondition != null && sortedCondition.size() > 0) {
                sb.append(" order by ");
                for (Entry<String, String> e : sortedCondition.entrySet()) {
                    sb.append(e.getKey() + " " + e.getValue() + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            Query query = null;
            try {
                query = getSession().createQuery(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setParameter(query, propName, propValue);
            List<T> list = query.list();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    public T getByProperties(String[] propName, Object[] propValue) {
        return getByProperties(propName, propValue, null);
    }

    public T getByProperties(String propName, Object propValue) {
        return getByProperties(new String[] { propName }, new Object[] { propValue });
    }

    public T getByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
        return getByProperties(new String[] { propName }, new Object[] { propValue },
                sortedCondition);
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue,
                                     Map<String, String> sortedCondition, Integer buttom, Integer top, String[] betweenName,
                                     Object[] minValue, Object[] maxValue) {
        Query query = getQuery(propName, propValue, sortedCondition, buttom, top, betweenName,
                minValue, maxValue);
        return query == null ? null : query.list();
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue,
                                     Map<String, String> sortedCondition, Integer buttom, Integer top) {
        return queryByProperties(propName, propValue, sortedCondition, buttom, top, null, null,
                null);
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue,
                                     Map<String, String> sortedCondition, Integer top) {
        return queryByProperties(propName, propValue, sortedCondition, null, top);
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue, Integer top) {
        return queryByProperties(propName, propValue, null, null, top);
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue,
                                     Map<String, String> sortedCondition) {
        return queryByProperties(propName, propValue, sortedCondition, null, null);
    }

    public List<T> queryByProperties(String propName, Object propValue,
                                     Map<String, String> sortedCondition, Integer top) {
        return queryByProperties(new String[] { propName }, new Object[] { propValue },
                sortedCondition, null, top);
    }

    public List<T> queryByProperties(String propName, Object propValue,
                                     Map<String, String> sortedCondition) {
        return queryByProperties(new String[] { propName }, new Object[] { propValue },
                sortedCondition, null, null);
    }

    public List<T> queryByProperties(String propName, Object propValue, Integer top) {
        return queryByProperties(new String[] { propName }, new Object[] { propValue }, null, null,
                top);
    }

    public List<T> queryByProperties(String[] propName, Object[] propValue) {
        return queryByProperties(propName, propValue, null, null, null);
    }

    public List<T> queryByProperties(String propName, Object propValue) {
        return queryByProperties(new String[] { propName }, new Object[] { propValue }, null, null,
                null);
    }

    public List<T> queryAll(Map<String, String> sortedCondition) {
        return getSession().createQuery(
                "from " + entityClass.getSimpleName() + " o " + buildOrderby(sortedCondition))
                .list();
    }

    public List<T> queryAll() {
        return queryAll(null);
    }


    public List<T> fuzzyQueryByProperties(String[] propName, Object[] propValue) {
        StringBuffer hql = new StringBuffer("select o from" + entityClass.getSimpleName()
                + " o where 1=1 ");
        if (propName != null && propValue != null && propName.length == propValue.length) {
            for (int i = 0; i < propName.length; i++) {
                String name = propName[i];
                Object value = propName[i];
                hql.append("and o." + name + " like '%" + value + "%' ");
            }
            Query query = null;
            try {
                query = getSession().createQuery(hql.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return query.list();
        }
        return null;
    }

    /**
     * 根据属性查询结果集大小
     *
     * @param propName
     * @param propValue
     * @return
     */
    public int countByProperties(String[] propName, Object[] propValue) {
        StringBuffer sb = new StringBuffer("select count(*) from " + entityClass.getSimpleName()
                + " o where 1=1 ");
        appendQL(sb, propName, propValue);
        Query query = getSession().createQuery(sb.toString());
        setParameter(query, propName, propValue);
        return ((Long) query.uniqueResult()).intValue();
    }

    /**
     * 根据属性查询结果集大小
     *
     * @param propName
     * @param propValue
     * @return
     */
    public int countByProperties(String propName, Object propValue) {
        return countByProperties(new String[] { propName }, new Object[] { propValue });
    }

    public void clear() {
        getSession().clear();
    }

    public void evict(T entity) {
        getSession().evict(entity);
    }

    private Query getQuery(String[] propName, Object[] propValue,
                           Map<String, String> sortedCondition, Integer buttom, Integer top, String[] betweenName,
                           Object[] minValue, Object[] maxValue) {
        if (propName != null && propValue != null && propValue.length == propName.length) {
            StringBuffer sb = new StringBuffer("select o from " + entityClass.getSimpleName()
                    + " o where 1=1 ");
            appendQL(sb, propName, propValue);
            if (betweenName != null && minValue != null && maxValue != null) {
                appendBetweenQl(sb, betweenName, minValue, maxValue);
            }
            if (sortedCondition != null && sortedCondition.size() > 0) {
                sb.append(" order by ");
                for (Entry<String, String> e : sortedCondition.entrySet()) {
                    sb.append(e.getKey() + " " + e.getValue() + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            Query query = null;
            try {
                query = getSession().createQuery(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setParameter(query, propName, propValue);
            if (betweenName != null && minValue != null && maxValue != null) {
                setBetweenParameter(query, betweenName, minValue, maxValue);
            }
            if (buttom != null)
                query.setFirstResult(buttom);
            if (top != null)
                query.setMaxResults(top);
            return query;
        }
        return null;
    }

    private void appendQL(StringBuffer sb, String[] propName, Object[] propValue) {
        for (int i = 0; i < propName.length; i++) {
            String name = propName[i];
            Object value = propValue[i];
            if (value instanceof Object[] || value instanceof Collection<?>) {
                Object[] arraySerializable = (Object[]) value;
                if (arraySerializable != null && arraySerializable.length > 0) {
                    sb.append(" and o." + name + " in (:" + name.replace(".", "") + ")");
                }
            } else {
                if (value == null) {
                    sb.append(" and o." + name + " is null ");
                } else {
                    sb.append(" and o." + name + "=:" + name.replace(".", ""));
                }
            }
        }
    }

    private void appendBetweenQl(StringBuffer sb, String[] propName, Object[] minValue,
                                 Object[] maxValue) {
        for (int i = 0; i < propName.length; i++) {
            String name = propName[i];
            sb.append(" and o." + name + " between " + ":min" + name.replace(".", "") + " and :max"
                    + name.replace(".", ""));
        }
    }

    private void setParameter(Query query, String[] propName, Object[] propValue) {
        for (int i = 0; i < propName.length; i++) {
            String name = propName[i];
            Object value = propValue[i];
            if (value != null) {
                if (value instanceof Object[]) {
                    query.setParameterList(name.replace(".", ""), (Object[]) value);
                } else if (value instanceof Collection<?>) {
                    query.setParameterList(name.replace(".", ""), (Collection<?>) value);
                } else {
                    query.setParameter(name.replace(".", ""), value);
                }
            }
        }
    }

    private void setBetweenParameter(Query query, String[] propName, Object[] minValue,
                                     Object[] maxValue) {
        for (int i = 0; i < propName.length; i++) {
            String name = propName[i];
            Object min = minValue[i];
            Object max = maxValue[i];
            if (min != null && max != null) {
                query.setParameter("min" + name.replace(".", ""), min);
                query.setParameter("max" + name.replace(".", ""), max);
            }
        }
    }

    @SuppressWarnings("unused")
    private String transferColumn(String queryCondition) {
        return queryCondition.substring(queryCondition.indexOf('_', 1) + 1);
    }

    protected void setParameter(Map<String, Object> mapParameter, Query query) {
        for (Iterator<String> it = mapParameter.keySet().iterator(); it.hasNext();) {
            String parameterName = (String) it.next();
            Object value = mapParameter.get(parameterName);
            query.setParameter(parameterName, value);
        }
    }

    /** ************ for QBC ********** */
    @SuppressWarnings({ "rawtypes", "unused" })
    private Method getMethod(String name) {
        if (!MAP_METHOD.containsKey(name)) {
            Class<Restrictions> clazz = Restrictions.class;
            Class[] paramType = new Class[] { String.class, Object.class };
            Class[] likeParamType = new Class[] { String.class, String.class, MatchMode.class };
            Class[] isNullType = new Class[] { String.class };
            try {
                Method method = null;
                if ("like".equals(name)) {
                    method = clazz.getMethod(name, likeParamType);
                } else if ("isNull".equals(name)) {
                    method = clazz.getMethod(name, isNullType);
                } else {
                    method = clazz.getMethod(name, paramType);
                }
                MAP_METHOD.put(name, method);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MAP_METHOD.get(name);
    }

    @SuppressWarnings({ "rawtypes", "unused" })
    private Method getExtendMethod(String name) {
        if (!MAP_METHOD.containsKey(name)) {
            Class<Restrictions> clazz = Restrictions.class;
            Class[] paramType = new Class[] { String.class, Object.class };
            Class[] likeParamType = new Class[] { String.class, String.class, MatchMode.class };
            Class[] isNullType = new Class[] { String.class };
            // Class[] inparamType=new Class[]{String.class,Arrays.class};
            try {
                Method method = null;
                if ("like".equals(name)) {
                    method = clazz.getMethod(name, likeParamType);
                } else if ("isNull".equals(name)) {
                    method = clazz.getMethod(name, isNullType);
                } else if ("IN".equals(name.toUpperCase())) {
                    // method=clazz.getMethod(name,inparamType);
                } else {
                    method = clazz.getMethod(name, paramType);
                }
                MAP_METHOD.put(name, method);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MAP_METHOD.get(name);
    }

    @SuppressWarnings("unused")
    private String getOpt(String value) {
        return (value.substring(0, value.indexOf('_', 1))).substring(1);
    }

    @SuppressWarnings("unused")
    private String getPropName(String value) {
        return value.substring(value.indexOf('_', 1) + 1);
    }

    /**
     * 组装order by子句
     *
     * @param orderby
     *            由属性与asc/desc构成的Map，其中key为属性，value为asc/desc
     * @return order by 子句
     */
    protected String buildOrderby(Map<String, String> orderby) {
        StringBuilder orderbyql = new StringBuilder("");
        if (orderby != null && orderby.size() > 0) {
            orderbyql.append(" order by ");
            for (String key : orderby.keySet()) {
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }
        return orderbyql.toString();
    }
}