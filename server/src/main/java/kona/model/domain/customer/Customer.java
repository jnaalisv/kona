package kona.model.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column
    private String name;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Version
    private long version;

    public Customer() {}

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
