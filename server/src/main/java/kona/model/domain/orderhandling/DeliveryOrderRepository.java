package kona.model.domain.orderhandling;

public interface DeliveryOrderRepository {

    void add(DeliveryOrder deliveryOrder);

    DeliveryOrder get(long id);
}
