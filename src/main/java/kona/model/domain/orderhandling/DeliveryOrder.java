package kona.model.domain.orderhandling;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private long payerID;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public DeliveryOrder(final long id, final long payerID, final List<OrderLine> orderLines) {
        this.id = id;
        this.payerID = payerID;
        this.orderLines = orderLines;
    }

    public long getId() {
        return id;
    }

    public long getPayerID() {
        return payerID;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
}
