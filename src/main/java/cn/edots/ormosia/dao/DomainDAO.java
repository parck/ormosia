package cn.edots.ormosia.dao;

import cn.edots.ormosia.model.Pagination;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public interface DomainDAO<PK extends Serializable, T extends Serializable> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    void merge(T entity);

    T get(PK key);

    T get(String uuid);

    T get(Criterion... criteria);

    List<T> list(Criterion... criteria);

    Pagination<T> paging(Pagination<T> pagination, Criterion... criteria);
}
