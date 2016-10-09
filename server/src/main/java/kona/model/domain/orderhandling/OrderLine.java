package kona.model.domain.orderhandling;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private String productCode;

    private BigDecimal amount;

    @ManyToOne
    private DeliveryOrder deliveryOrder;

    public OrderLine() { /* hibernate */}

    public OrderLine(long id, String productCode, BigDecimal amount) {
        this.id = id;
        this.productCode = productCode;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
