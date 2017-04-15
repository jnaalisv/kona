package kona.model.domain.orderhandling;

import java.util.List;

public interface DeliveryOrderRepository {

    void add(DeliveryOrder deliveryOrder);

    DeliveryOrder get(long id);

    List<DeliveryOrder> getAll();

    void update(DeliveryOrder deliveryOrder);
}
