package cn.edots.ormosia.service;

import cn.edots.ormosia.model.Pagination;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public interface DomainService<PK extends Serializable, T extends Serializable> {

    void save(T entity) throws Exception;

    void delete(T entity) throws Exception;

    void update(T entity) throws Exception;

    void merge(T entity) throws Exception;

    T get(PK key) throws Exception;

    T get(String key) throws Exception;

    T get(Criterion... criteria) throws Exception;

    List<T> list(Criterion... criteria) throws Exception;

    Pagination<T> paging(Pagination<T> pagination, Criterion... criteria) throws Exception;

}
