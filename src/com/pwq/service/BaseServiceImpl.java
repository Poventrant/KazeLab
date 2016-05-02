package com.pwq.service;

import com.pwq.dao.BaseDao;
import com.pwq.entity.User;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Lazy(true)
public class BaseServiceImpl<E> implements BaseService<E>{
    protected BaseDao<E> dao;
    public E findById(Serializable entityId) {
        return dao.findById(entityId);
    }

    public Serializable persist(E entity) {
        return dao.persist(entity);
    }

    public boolean deleteByPK(Serializable... id) {
        return dao.deleteByPK(id);
    }

    public void delete(E entity) {
        dao.delete(entity);
    }

    public void deleteByProperties(String[] propName, Object[] propValue) {
        dao.deleteByProperties(propName, propValue);
    }

    public void deleteByProperties(String propName, Object propValue) {
        dao.deleteByProperties(propName, propValue);
    }

    public E getByProperties(String[] propName, Object[] propValue) {
        return dao.getByProperties(propName, propValue);
    }

    public E getByProperties(String propName, Object propValue) {
        return dao.getByProperties(propName, propValue);
    }

    public E getByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
        return dao.getByProperties(propName, propValue, sortedCondition);
    }

    public E getByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
        return dao.getByProperties(propName, propValue, sortedCondition);
    }

    public List<E> queryByProperties(String[] propName, Object[] propValue) {
        return dao.queryByProperties(propName, propValue);
    }

    public List<E> queryByProperties(String propName, Object propValue) {
        return dao.queryByProperties(propName, propValue);
    }

    public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition) {
        return dao.queryByProperties(propName, propValue, sortedCondition);
    }

    public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition) {
        return dao.queryByProperties(propName, propValue, sortedCondition);
    }

    public List<E> queryByProperties(String[] propName, Object[] propValue, Map<String, String> sortedCondition, Integer top) {
        return dao.queryByProperties(propName, propValue, sortedCondition, top);
    }

    public List<E> queryByProperties(String[] propName, Object[] propValue, Integer top) {
        return dao.queryByProperties(propName, propValue, top);
    }

    public List<E> queryByProperties(String propName, Object propValue, Map<String, String> sortedCondition, Integer top) {
        return dao.queryByProperties(propName, propValue, sortedCondition, top);
    }

    public List<E> queryByProperties(String propName, Object propValue, Integer top) {
        return dao.queryByProperties(propName, propValue, top);
    }

    public List<E> queryAll() {
        return dao.queryAll();
    }

    public List<E> queryAll(Map<String, String> sortedCondition) {
        return dao.queryAll(sortedCondition);
    }

    public E merge(E entity) {
        return dao.merge(entity);
    }

    @Override
    public E get(Serializable id) {
        return null;
    }

    @Override
    public E load(Serializable id) {
        return null;
    }

    public void update(E entity, Serializable oldId) {
        dao.update(entity, oldId);
    }

    public void update(E entity) {
        dao.update(entity);
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue, String[] propertyName, Object[] propertyValue) {
        dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
    }

    public void updateByProperties(String conditionName, Object conditionValue, String[] propertyName, Object[] propertyValue) {
        dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
    }

    public void updateByProperties(String[] conditionName, Object[] conditionValue, String propertyName, Object propertyValue) {
        dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
    }

    public void updateByProperties(String conditionName, Object conditionValue, String propertyName, Object propertyValue) {
        dao.updateByProperties(conditionName, conditionValue, propertyName, propertyValue);
    }

    public void evict(E entity) {
        dao.evict(entity);
    }

    public void clear() {
        dao.clear();
    }


}
