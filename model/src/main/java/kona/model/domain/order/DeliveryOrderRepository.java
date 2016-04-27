package kona.model.domain.order;

public interface DeliveryOrderRepository {

    void add(DeliveryOrder deliveryOrder);

    DeliveryOrder get(long id);
}
