package kona.model.domain.orderhandling;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "orderer_id")
    private long ordererID;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Version
    private long version;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public PurchaseOrder() { /* hibernate*/}

    public PurchaseOrder(final long id, final long ordererID, final List<OrderLine> orderLines) {
        this.id = id;
        this.ordererID = ordererID;
        this.orderLines = orderLines;
        this.orderLines.forEach(orderLine -> orderLine.setPurchaseOrder(this));
    }

    public PurchaseOrder(final long id, final long payerID, final OrderLine...orderLines) {
        this(id, payerID, Arrays.asList(orderLines));
    }

    public long getId() {
        return id;
    }

    public long getOrdererID() {
        return ordererID;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
