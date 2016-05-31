package kona.model.domain.orderhandling;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    @Inject
    public DeliveryOrderService(final DeliveryOrderRepository deliveryOrderRepository) {
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Transactional
    public void save(DeliveryOrder deliveryOrder) {
        this.deliveryOrderRepository.add(deliveryOrder);
    }

    @Transactional
    public DeliveryOrder load(long id) {
        return deliveryOrderRepository.get(id);
    }
}
