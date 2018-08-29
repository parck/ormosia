package cn.edots.ormosia.dao;


import cn.edots.ormosia.model.Pagination;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public abstract class DomainHDAO<PK extends Serializable, T extends Serializable> implements DomainDAO<PK, T> {

    @Resource
    protected SessionFactory sessionFactory;

    // 实体类类型(由构造方法自动赋值)
    protected final Class<T> type;

    // 构造方法，根据实例类自动获取实体类类型
    public DomainHDAO() {
        Class clazz = getClass();
        Type superclass = clazz.getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) superclass).getActualTypeArguments();
            if (typeArguments != null) {
                if (typeArguments.length == 1)
                    this.type = (Class<T>) typeArguments[0];
                else
                    this.type = (Class<T>) typeArguments[1];
            } else {
                new Throwable(new NullPointerException());
                this.type = null;
            }
        } else {
            throw new IllegalStateException("Unknown Entity Class");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void merge(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Transactional(readOnly = true)
    public T get(PK key) {
        return sessionFactory.getCurrentSession().find(type, key);
    }

    @Transactional(readOnly = true)
    public T get(String uuid) {
        return (T) sessionFactory
                .getCurrentSession()
                .createCriteria(type)
                .add(Restrictions.eq("uuid", uuid))
                .uniqueResult();
    }

    @Transactional(readOnly = true)
    public T get(Criterion... criteria) {
        if (criteria == null) return null;
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(type);
        for (Criterion c : criteria) criterion.add(c);
        return (T) criterion.uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<T> list(Criterion... criteria) {
        if (criteria == null) return null;
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(type);
        for (Criterion c : criteria) criterion.add(c);
        return criterion.list();
    }

    @Transactional(readOnly = true)
    public Pagination<T> paging(Pagination<T> pagination, Criterion... criteria) {
        Criteria criterion = sessionFactory.getCurrentSession().createCriteria(type);
        // 查询记录总数
        long count = (Long) criterion.setProjection(Projections.rowCount()).uniqueResult();
        criterion.setProjection(null);
        // 添加查询条件
        if (criteria != null)
            for (Criterion c : criteria)
                criterion.add(c);
        // 添加排序
        if (pagination.getBy() != null && !"".equals(pagination.getBy()))
            if (pagination.isDesc()) criterion.addOrder(Order.desc(pagination.getBy()));
            else criterion.addOrder(Order.asc(pagination.getBy()));
        // 设置分页数据
        criterion.setFirstResult((pagination.getPage() - 1) * pagination.getSize());
        criterion.setMaxResults(pagination.getSize());
        // 设置数据
        pagination.setDomains(criterion.list());
        pagination.setCount(count);
        return pagination;
    }
}
