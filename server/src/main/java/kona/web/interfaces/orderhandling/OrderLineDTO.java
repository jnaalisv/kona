package kona.web.interfaces.orderhandling;

import java.math.BigDecimal;

public class OrderLineDTO {
    public long id;
    public String productCode;
    public BigDecimal amount;

    public OrderLineDTO() {}

    public OrderLineDTO(final long id, final String productCode, final BigDecimal amount) {
        this.id = id;
        this.productCode = productCode;
        this.amount = amount;
    }
}
