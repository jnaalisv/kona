package kona.model.application;

import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderHandlingService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    public OrderHandlingService(final DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Transactional
    public void saveNew(DeliveryOrder deliveryOrder) {
        this.deliveryOrderRepository.add(deliveryOrder);
    }

    @Transactional(readOnly = true)    public DeliveryOrder loadBy(long id) {
        return deliveryOrderRepository.get(id);
    }

    @Transactional(readOnly = true)
    public List<DeliveryOrder> getAll() {
        return deliveryOrderRepository.getAll();
    }

    @Transactional
    public void update(DeliveryOrder deliveryOrder) {
        deliveryOrderRepository.update(deliveryOrder);
    }
}
