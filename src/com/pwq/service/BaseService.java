package com.pwq.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.pwq.dao.BaseDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@SuppressWarnings({ "rawtypes" })
@Service("baseService")
/** 懒加载配置 */
@Lazy(true)
public abstract class BaseService<T> {
	protected final Log log = LogFactory.getLog(getClass());
	Logger logger = Logger.getLogger(BaseService.class);
	private Class clazz;

	protected BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 构造方法，初始化clazz
	 */
	public BaseService() {
		// 获取类的参数化类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	/**
	 * 立即加载单个实体，所有属性都取出
	 */
	public T getObject(Serializable id) {
		return (T) baseDao.getObject(id);
	}

	/**
	 * 获取所有对象
	 */
	public List<T> getObjects() {
		return baseDao.getObjects();
	}

	/**
	 * 添加实体
	 */
	public Serializable insertObject(T entity) {
		return baseDao.insertObject(entity);
	}

	/**
	 * 删除实体
	 */
	public void deleteObject(Serializable id) {
		baseDao.deleteObject(id);
	}

	/**
	 * 通过hql语句删除实体
	 */
	public void deleteObjectByHql(Serializable id) {
		StringBuffer query_str = new StringBuffer();
		query_str.append(" AND " + clazz.getSimpleName().substring(0, 1).toLowerCase() + ".id=");
		query_str.append(id);
		baseDao.deleteObjects(query_str.toString());
	}

	/**
	 * 通过hql语句,逻辑删除实体
	 */
	public void logicDelete(Serializable id) {
		StringBuffer query_str = new StringBuffer();
		query_str.append(" WHERE 1=1 AND " + clazz.getSimpleName().substring(0, 1).toLowerCase()
				+ ".id=");
		query_str.append(id);
		baseDao.logicDelete(query_str.toString());
	}

	/**
	 * 执行更新操作(更新一个实体的所有属性)
	 */
	@Deprecated
	public void updateObject(T values) {
		baseDao.updateObject(values);
	}

	/**
	 * 执行hql语句,更新操作
	 */
	public void updateObject(T t, Integer id) {
		StringBuffer hql = new StringBuffer();
		StringBuffer sb_hql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		try {
			String className = t.getClass().getSimpleName().toString();
			hql.append("UPDATE " + className + " " + className.substring(0, 1).toLowerCase()
					+ " SET ");
			Map<String, Object> propertyMap = getProperty(t);
			Map<String, Object> propertyTypeMap = getPropertyType(t);

			Set<Entry<String, Object>> entrySet = propertyMap.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value != null) {
					if (propertyTypeMap.get(key).toString().equals("int")) {
						int ti = (int) value;
						if (ti > 0) {
							values.add(value);
							hql.append(" " + className.substring(0, 1).toLowerCase() + "." + key
									+ "=?,");
						}
					} else {
						values.add(value);
						hql.append(" " + className.substring(0, 1).toLowerCase() + "." + key
								+ "=?,");
					}
				}
			}
			sb_hql.append(hql.toString().substring(0, hql.toString().length() - 1));
			sb_hql.append(" WHERE " + className.substring(0, 1).toLowerCase() + ".id=" + id);
			baseDao.updateObjectByHql(sb_hql.toString(), values);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 反射获取字段名和字段对应的值的类型
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> getPropertyType(T t) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<? extends Object> ownerClass = t.getClass();
		Field[] fields = ownerClass.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String str = fields[i].toString();
				str = str.substring(str.lastIndexOf('.') + 1, str.length());
				String fieldName = str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
				String typeString = fields[i].getType().toString();
				if (!"serialVersionUID".equals(str) && !"id".equals(str)) {
					map.put(str, typeString);
				}
			}
		} catch (Exception e) {
			log.info("反射获取字段值失败");
			throw new RuntimeException("反射获取字段值失败", e);
		}
		return map;
	}

	/**
	 * 反射获取字段名和字段对应的值
	 */
	private Map<String, Object> getProperty(T t) {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<? extends Object> ownerClass = t.getClass();
		Field[] fields = ownerClass.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String str = fields[i].toString();
				str = str.substring(str.lastIndexOf('.') + 1, str.length());
				String fieldName = str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
				if (!"serialVersionUID".equals(str) && !"id".equals(str)) {
					Object property = ownerClass.getMethod("get" + fieldName).invoke(t);
					map.put(str, property);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			log.info("反射获取字段值失败");
			throw new RuntimeException("反射获取字段值失败", e);
		}
		return map;
	}

	/**
	 * 批量逻辑删除
	 */
	public void logicDeletes(int[] ids) {
		if (ids == null || ids.length < 0) {
			log.error("批量删出错,消息:ids为null或ids为空");
		} else {
			StringBuffer query_str = new StringBuffer();
			try {
				for (int i = 0; i < ids.length; i++) {
					if (i == 0) {
						query_str.append("  WHERE 1=1 AND "
								+ clazz.getSimpleName().substring(0, 1).toLowerCase() + ".id="
								+ ids[i]);
					} else {
						query_str.append(" OR "
								+ clazz.getSimpleName().substring(0, 1).toLowerCase() + ".id="
								+ ids[i]);
					}
				}
				log.info(query_str.toString());
				baseDao.logicDelete(query_str.toString());
			} catch (Exception e) {
				log.error("批量删除出错,消息:" + e.getMessage());
			}
		}
	}

	/**
	 * 批量删除
	 */
	public void deleteObjects(int[] ids) {
		if (ids == null || ids.length < 0) {
			log.error("批量删除出错,消息:ids为null或ids为空");
		} else {
			String query_str = "";
			try {
				for (int i = 0; i < ids.length; i++) {
					if (i == 0) {
						query_str = " AND " + clazz.getSimpleName().substring(0, 1).toLowerCase()
								+ ".id=" + ids[i];
					} else {
						query_str = query_str + " OR "
								+ clazz.getSimpleName().substring(0, 1).toLowerCase() + ".id="
								+ ids[i];
					}
				}
				log.info(query_str);
				baseDao.deleteObjects(query_str.toString());
			} catch (Exception e) {
				log.error("批量删除出错,消息:" + e.getMessage());
			}
		}
	}

	public T getFetchObject(String hql) {
		return baseDao.getFetchObject(hql);
	}

	public int getFetchCount(String hql) {
		return baseDao.getFetchCount(hql);
	}

	public T findById(Serializable entityId) {
		return baseDao.findById(entityId);
	}

	public Serializable persist(T entity) {
		return baseDao.persist(entity);
	}

	public void persistOrUpdate(T entity) {
		baseDao.persistOrUpdate(entity);
	}

	public boolean deleteByPK(Serializable... id) {
		return baseDao.deleteByPK(id);
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void deleteByProperties(String[] propName, Object[] propValue) {
		baseDao.deleteByProperties(propName, propValue);
	}

	public void deleteByProperties(String propName, Object propValue) {
		baseDao.deleteByProperties(propName, propValue);
	}

	public T getByProperties(String[] propName, Object[] propValue) {
		return baseDao.getByProperties(propName, propValue);
	}

	public T getByProperties(String propName, Object propValue) {
		return baseDao.getByProperties(propName, propValue);
	}

	public T getByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedCondition) {
		return baseDao.getByProperties(propName, propValue, sortedCondition);
	}

	public T getByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
		return baseDao.getByProperties(propName, propValue, sortedCondition);
	}

	public List<T> queryByProperties(String[] propName, Object[] propValue) {
		return baseDao.queryByProperties(propName, propValue);
	}

	public List<T> queryByProperties(String propName, Object propValue) {
		return baseDao.queryByProperties(propName, propValue);
	}

	public List<T> queryByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedCondition) {
		return baseDao.queryByProperties(propName, propValue, sortedCondition);
	}

	public List<T> queryByProperties(String propName, Object propValue,
			Map<String, String> sortedCondition) {
		return baseDao.queryByProperties(propName, propValue, sortedCondition);
	}

	public List<T> queryByProperties(String[] propName, Object[] propValue,
			Map<String, String> sortedCondition, Integer top) {
		return baseDao.queryByProperties(propName, propValue, sortedCondition, top);
	}

	public List<T> queryByProperties(String[] propName, Object[] propValue, Integer top) {
		return baseDao.queryByProperties(propName, propValue, top);
	}

	public List<T> queryByProperties(String propName, Object propValue,
			Map<String, String> sortedCondition, Integer top) {
		return baseDao.queryByProperties(propName, propValue, sortedCondition, top);
	}

	public List<T> queryByProperties(String propName, Object propValue, Integer top) {
		return baseDao.queryByProperties(propName, propValue, top);
	}

	public List<T> queryAll() {
		return baseDao.queryAll();
	}

	public List<T> queryAll(Map<String, String> sortedCondition) {
		return baseDao.queryAll(sortedCondition);
	}


	/**
	 * 根据属性查询结果集大小
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public int countByProperties(String[] propName, Object[] propValue) {
		return baseDao.countByProperties(propName, propValue);
	}
	
	/**
	 * 根据属性查询结果集大小
	 * 
	 * @param propName
	 * @param propValue
	 * @return
	 */
	public int countByProperties(String propName, Object propValue) {
		return baseDao.countByProperties(propName, propValue);
	}
	
	public List<T> fuzzyQueryByProperties(String[] propName, Object[] propValue) {
		return baseDao.fuzzyQueryByProperties(propName, propValue);
	}

	public T merge(T entity) {
		return baseDao.merge(entity);
	}

	public void update(T entity, Serializable oldId) {
		baseDao.update(entity, oldId);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public void updateByProperties(String[] conditionName, Object[] conditionValue,
			String[] propertyName, Object[] propertyValue) {
		baseDao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String conditionName, Object conditionValue,
			String[] propertyName, Object[] propertyValue) {
		baseDao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String[] conditionName, Object[] conditionValue,
			String propertyName, Object propertyValue) {
		baseDao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void updateByProperties(String conditionName, Object conditionValue,
			String propertyName, Object propertyValue) {
		baseDao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
	}

	public void evict(T entity) {
		baseDao.evict(entity);
	}

	public void clear() {
		baseDao.clear();
	}

	public Long countAll() {
		return baseDao.countAll();
	}

	public T get(Serializable id) {
		return baseDao.get(id);
	}

	public T load(Serializable id) {
		return baseDao.load(id);
	}

	public List<T> criteriaEqeals(String propName, Object propVal) {
		return baseDao.criteriaEqeals(propName, propVal);
	}

}
