package cn.edots.ormosia.service;

import cn.edots.ormosia.dao.DomainDAO;
import cn.edots.ormosia.model.Pagination;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

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

    public T get(Criterion... criterias) {
        return getEntityDAO().get(criterias);
    }

    public List<T> list(Criterion... criterias) {
        return getEntityDAO().list(criterias);
    }

    public Pagination<T> paging(Pagination<T> pagination, Criterion... criteria) {
        return getEntityDAO().paging(pagination, criteria);
    }
}
