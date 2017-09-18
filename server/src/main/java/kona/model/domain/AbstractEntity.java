package kona.model.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity {

    /* surrogate key and no arg ctor for hibernate */
    @Id
    @GeneratedValue
    private long id;

    @Version
    private long version = 0L;

    private String createdBy;
    private String updatedBy;

    public AbstractEntity(long id, long version) {
        this.id = id;
        this.version = version;
    }

    public AbstractEntity() {}

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
