package kona.infrastructure.persistence;

import kona.domain.order.DeliveryOrder;
import kona.domain.order.DeliveryOrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaDeliveryOrderRepository implements DeliveryOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(DeliveryOrder deliveryOrder) {
        entityManager.persist(deliveryOrder);
    }

    @Override
    public DeliveryOrder get(long id) {
        return entityManager.find(DeliveryOrder.class, id);
    }
}
