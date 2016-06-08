package kona.infrastructure.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import kona.model.domain.customer.Customer;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderRepository;

@Repository
public class HibernateDeliveryOrderRepository implements DeliveryOrderRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateDeliveryOrderRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(DeliveryOrder deliveryOrder) {
        getCurrentSession().save(deliveryOrder);
    }

    @Override
    public DeliveryOrder get(long id) {
        return (DeliveryOrder) getCurrentSession()
                .createQuery("select o from DeliveryOrder o left join fetch o.orderLines where o.id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }
}