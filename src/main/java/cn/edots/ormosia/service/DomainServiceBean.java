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

    public void save(T entity) throws Exception {
        getEntityDAO().save(entity);
    }

    public void delete(T entity) throws Exception {
        getEntityDAO().delete(entity);
    }

    public void update(T entity) throws Exception {
        getEntityDAO().update(entity);
    }

    public void merge(T entity) throws Exception {
        getEntityDAO().merge(entity);
    }

    public T get(PK key) throws Exception {
        return getEntityDAO().get(key);
    }

    public T get(String key) throws Exception {
        return getEntityDAO().get(key);
    }

    public T get(Criterion... criteria) throws Exception {
        return getEntityDAO().get(criteria);
    }

    public List<T> list(Criterion... criteria) throws Exception {
        return getEntityDAO().list(criteria);
    }

    public Pagination<T> paging(Pagination<T> pagination, Criterion... criteria) throws Exception {
        return getEntityDAO().paging(pagination, criteria);
    }
}
