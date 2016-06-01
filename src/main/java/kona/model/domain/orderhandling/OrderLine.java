package kona.model.domain.orderhandling;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private long productID;

    private BigDecimal amount;

    @ManyToOne
    private DeliveryOrder deliveryOrder;

    public OrderLine() { /* hibernate */}

    public OrderLine(long id, long productID, BigDecimal amount) {
        this.id = id;
        this.productID = productID;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getProductID() {
        return productID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
