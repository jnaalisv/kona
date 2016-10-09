package kona.model.domain.orderhandling;

import kona.model.domain.product.ProductCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private ProductCode productCode;

    private BigDecimal amount;

    @ManyToOne
    private DeliveryOrder deliveryOrder;

    public OrderLine() { /* hibernate */}

    public OrderLine(long id, ProductCode productCode, BigDecimal amount) {
        this.id = id;
        this.productCode = productCode;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
