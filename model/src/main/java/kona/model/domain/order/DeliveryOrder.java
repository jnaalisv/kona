package kona.model.domain.order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private long payerID;

    @OneToMany(mappedBy = "deliveryOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

}
