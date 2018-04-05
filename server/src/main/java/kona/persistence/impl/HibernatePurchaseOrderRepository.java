package kona.persistence.impl;

import kona.domain.orderhandling.PurchaseOrder;
import kona.domain.orderhandling.PurchaseOrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernatePurchaseOrderRepository implements PurchaseOrderRepository {

    private final SessionFactory sessionFactory;

    public HibernatePurchaseOrderRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(PurchaseOrder purchaseOrder) {
        getCurrentSession().save(purchaseOrder);
    }

    @Override
    public PurchaseOrder get(long id) {
        return getCurrentSession()
                .createQuery("select o from kona.domain.orderhandling.PurchaseOrder o left join fetch o.orderLines where o.id = :id", PurchaseOrder.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return getCurrentSession()
                .createQuery("select distinct o from kona.domain.orderhandling.PurchaseOrder o left join fetch o.orderLines", PurchaseOrder.class)
                .getResultList();
    }

    @Override
    public void update(PurchaseOrder purchaseOrder) {
        getCurrentSession().saveOrUpdate(purchaseOrder);
    }
}
