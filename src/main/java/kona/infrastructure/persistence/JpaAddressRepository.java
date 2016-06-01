package kona.infrastructure.persistence;

import kona.model.domain.address.Address;
import kona.model.domain.address.AddressRepository;
import kona.model.domain.customer.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Address address) {
        entityManager.persist(address);
    }

    @Override
    public Optional<Address> get(long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    public List<Address> getAll() {
        return entityManager
                .createQuery("select a from Address a", Address.class)
                .getResultList();
    }
}
