package kona.infrastructure;

import kona.domain.address.Address;
import kona.domain.address.AddressRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Optional;

@Repository
public class HibernateAddressRepository implements AddressRepository {

    private SessionFactory sessionFactory;

    @Inject
    public HibernateAddressRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Address address) {
        currentSession().save(address);
    }

    @Override
    public Optional<Address> get(long id) {
        return Optional.ofNullable(currentSession().get(Address.class, id));
    }
}
