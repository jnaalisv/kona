package kona.infrastructure;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import kona.domain.address.Address;
import kona.domain.address.AddressRepository;

@Repository
public class HibernateAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Address address) {
        entityManager.persist(address);
    }

    @Override
    public Optional<Address> get(long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }
}
