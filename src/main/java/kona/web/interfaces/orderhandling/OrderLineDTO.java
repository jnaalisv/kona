package kona.web.interfaces.orderhandling;

import java.math.BigDecimal;

public class OrderLineDTO {
    public long id;
    public long productID;
    public BigDecimal amount;

    public OrderLineDTO() {}

    public OrderLineDTO(final long id, final long productID, final BigDecimal amount) {
        this.id = id;
        this.productID = productID;
        this.amount = amount;
    }
}
