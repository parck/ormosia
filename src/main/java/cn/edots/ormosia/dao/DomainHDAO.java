package cn.edots.ormosia.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
public abstract class DomainHDAO<PK extends Serializable, T extends Serializable> implements DomainDAO<PK, T> {

    protected SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
    public T get(String key) {
        return (T) sessionFactory
                .getCurrentSession()
                .createQuery("FROM " + type.getSimpleName() + " AS t WHERE t." + type.getSimpleName().toLowerCase() + "Id = :key")
                .setParameter("key", key).uniqueResult();
    }
}
