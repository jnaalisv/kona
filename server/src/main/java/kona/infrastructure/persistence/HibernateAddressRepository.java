package kona.infrastructure.persistence;

import kona.model.domain.address.Address;
import kona.model.domain.address.AddressRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Repository
public class HibernateAddressRepository implements AddressRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateAddressRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Address address) {
        getCurrentSession().save(address);
    }

    @Override
    public Optional<Address> get(long id) {
        return getCurrentSession()
                .createQuery("select a from Address a where a.id = :id", Address.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public List<Address> getAll() {
        return getCurrentSession()
                .createQuery("select a from Address a", Address.class)
                .list();
    }
}
