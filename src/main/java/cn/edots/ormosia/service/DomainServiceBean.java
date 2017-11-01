package cn.edots.ormosia.service;

import cn.edots.ormosia.dao.DomainDAO;

import java.io.Serializable;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public abstract class DomainServiceBean<PK extends Serializable, T extends Serializable> implements DomainService<PK, T> {


    public abstract DomainDAO<PK, T> getEntityDAO();

    public void save(T entity) {
        getEntityDAO().save(entity);
    }

    public void delete(T entity) {
        getEntityDAO().delete(entity);
    }

    public void update(T entity) {
        getEntityDAO().update(entity);
    }

    public void merge(T entity) {
        getEntityDAO().merge(entity);
    }

    public T get(PK key) {
        return getEntityDAO().get(key);
    }

    public T get(String key) {
        return getEntityDAO().get(key);
    }

}
