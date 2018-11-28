package ramos.jefferson.base.audity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import ramos.jefferson.base.entity.User;

@Entity
@Table(name = "revisions")
@RevisionEntity(BaseAudityListener.class)
@SuppressWarnings({"IdDefinedInHierarchy", "ConsistentAccessType"})
public class BaseAudity implements Serializable {

    @Id
    @RevisionNumber
    @Basic(optional = false)
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "revision_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "revision_id_seq", sequenceName = "revision_id_seq", allocationSize = 1)
    private long id;

    @RevisionTimestamp
    @Column(name = "timestamp", updatable = false, nullable = false)
    private long timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_revision", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_revision_id"))
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
    public Date getRevisionDate() {
        return new Date(timestamp);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 43 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseAudity other = (BaseAudity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.timestamp != other.timestamp) {
            return false;
        }
        return true;
    }

}
