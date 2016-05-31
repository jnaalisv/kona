package kona.model.domain.orderhandling;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    public OrderLine(long id, long productID, BigDecimal amount, DeliveryOrder deliveryOrder) {
        this.id = id;
        this.productID = productID;
        this.amount = amount;
        this.deliveryOrder = deliveryOrder;
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
}
