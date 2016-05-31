package kona.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kona.model.domain.customer.Customer;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderRepository;

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
        return entityManager
                .createQuery("select o from DeliveryOrder o left join fetch o.orderLines where o.id = :id", DeliveryOrder.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
