package cn.edots.ormosia.service;

import cn.edots.ormosia.model.Pagination;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public interface DomainService<PK extends Serializable, T extends Serializable> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    void merge(T entity);

    T get(PK key);

    T get(String key);

    Pagination<T> paging(Pagination<T> pagination, Criterion... criteria);

}
