package kona.domain.orderhandling;

import kona.domain.product.ProductCode;

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
    private long id;

    private ProductCode productCode;

    private long productId;

    private BigDecimal amount;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    public OrderLine() { /* hibernate */}

    public OrderLine(long id, long productId, ProductCode productCode, BigDecimal amount) {
        this.id = id;
        this.productId = productId;
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

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
