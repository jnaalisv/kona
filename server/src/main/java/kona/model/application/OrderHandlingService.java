package kona.model.application;

import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class OrderHandlingService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    @Inject
    public OrderHandlingService(final DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Transactional
    public void saveNew(DeliveryOrder deliveryOrder) {
        this.deliveryOrderRepository.add(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder loadBy(long id) {
        return deliveryOrderRepository.get(id);
    }
}
