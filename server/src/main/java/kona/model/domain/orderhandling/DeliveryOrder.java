package kona.model.domain.orderhandling;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private long ordererID;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public DeliveryOrder() { /* hibernate*/}

    public DeliveryOrder(final long id, final long ordererID, final List<OrderLine> orderLines) {
        this.id = id;
        this.ordererID = ordererID;
        this.orderLines = orderLines;
        this.orderLines.forEach(orderLine -> orderLine.setDeliveryOrder(this));
    }

    public DeliveryOrder(final long id, final long payerID, final OrderLine...orderLines) {
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
}
