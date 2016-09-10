package com.iyzico.utility;

import com.iyzico.entity.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hikuley on 28/08/16.
 */

@Repository
public class GenericDaoImpl<T extends BaseEntity> implements GenericDao<T> {


    @PersistenceContext
    EntityManager entityManager;

    private Class<T> clazz;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public GenericDaoImpl() {
        this.clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDao.class);
    }

    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    @Transactional
    public T create(T t) {
        t.setCreateTime(Calendar.getInstance());
        t.setUpdateTime(Calendar.getInstance());
        this.getSession().save(t);
        return t;
    }

    @Override
    @Transactional
    public void delete(Long id, Class aClass) {
        T t = (T) getSession().get(aClass, id);
        this.getSession().delete(t);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        T t = (T) getSession().get(getClazz(), id);
        this.getSession().delete(t);
    }

    @Override
    @Transactional
    public T update(T t) {
        t.setUpdateTime(Calendar.getInstance());
        this.getSession().update(t);
        return t;
    }

    @Override
    @Transactional
    public T findById(Long id, Class aClass) {
        T t = (T) getSession().get(aClass, id);
        return t;
    }

    @Override
    @Transactional
    public T findById(Long id) {
        T t = (T) getSession().get(getClazz(), id);
        return t;
    }

    @Override
    @Transactional
    public List<T> listWithPagination(Integer start, Integer limit, Class clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult(start);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id")); // select * from T t order by id desc
        List<T> list = criteria.list();
        return list;
    }

    @Override
    @Transactional
    public List<T> listAll(Class clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.addOrder(Order.desc("id")); // select * from T t order by id desc
        List<T> list = criteria.list();
        return list;
    }


    @Override
    @Transactional
    public List<T> listWithPagination(Integer start, Integer limit) {
        Criteria criteria = getSession().createCriteria(getClazz());
        criteria.setFirstResult(start);
        criteria.setMaxResults(limit);
        criteria.addOrder(Order.desc("id")); // select * from T t order by id desc
        List<T> list = criteria.list();
        return list;
    }

    @Override
    @Transactional
    public List<T> listAll() {
        Criteria criteria = getSession().createCriteria(getClazz());
        criteria.addOrder(Order.desc("id")); // select * from T t order by id desc
        List<T> list = criteria.list();
        return list;
    }

    @Override
    @Transactional
    public Long recordSize() {
        return (Long) getSession().createCriteria(getClazz()).setProjection(Projections.rowCount()).uniqueResult();
    }


    @Override
    @Transactional
    public Long recordSize(Class aClass) {
        return (Long) getSession().createCriteria(aClass).setProjection(Projections.rowCount()).uniqueResult();
    }


}
