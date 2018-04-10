package kona.http.orderhandling;

import java.math.BigDecimal;

public class OrderLineDTO {
    public long id;
    public long productId;
    public BigDecimal amount;
    public String productCode;

    public OrderLineDTO() {}

    public OrderLineDTO(final long id, final long productId, final BigDecimal amount, final String productCode) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.productCode = productCode;
    }
}
