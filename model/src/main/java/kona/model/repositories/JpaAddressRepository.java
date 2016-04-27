package kona.model.repositories;

import kona.model.domain.address.Address;
import kona.model.domain.address.AddressRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaAddressRepository implements AddressRepository {

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
