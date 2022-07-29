package com.kstd.shinmy.registrationapi.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class GenericDAO<T, E extends Serializable> {
    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    @PostConstruct
    private void init() {
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    protected <T> List<T> getResultList(CriteriaQuery<T> criteriaQuery) {
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    protected <T> List getResultList(String sql, Class<T> tClass) {
        Query query = entityManager.createNativeQuery(sql, tClass);
        return query.getResultList();
    }

    protected <T> T getSingleResult(CriteriaQuery<T> criteriaQuery) {
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    protected <T> Object getSingleResult(String sql, Class<T> tClass) {
        Query query = entityManager.createNativeQuery(sql, tClass);
        return query.getSingleResult();
    }

    public T find(Class<T> tClass, E primaryKey) {
        return entityManager.find(tClass, primaryKey);
    }

    public T save(T entity) {
        entityManager.persist(entity);
        entityManager.refresh(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
