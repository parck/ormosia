package cn.edots.ormosia.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author ParckLee.
 * @Company wemoons.com
 * @Date 2017/8/11.
 */
@MappedSuperclass
public abstract class Domain implements Serializable {

    protected Long id;
    protected Date dateCreated = new Date();
    protected Date lastUpdated = new Date();
    protected int version = 0;
    protected boolean deleted = false;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "system-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "system-id", strategy = "identity")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domain)) return false;

        Domain domain = (Domain) o;

        // 如果两个对象的ID都是null 认为不相等
        if (id == null && domain.id == null) return false;

        return id != null ? id.equals(domain.id) : domain.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
