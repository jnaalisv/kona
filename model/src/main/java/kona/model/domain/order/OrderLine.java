package kona.model.domain.order;

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

    @ManyToOne
    private DeliveryOrder deliveryOrder;

}
