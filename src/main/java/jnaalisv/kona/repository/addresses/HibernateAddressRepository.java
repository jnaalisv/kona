package jnaalisv.kona.repository.addresses;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jnaalisv.kona.domain.addresses.Address;
import jnaalisv.kona.domain.addresses.AddressRepository;

@Repository
public class HibernateAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /*
    @PersistenceContext
    public HibernateAddressRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    */

    @Override
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Optional<Address> get(long id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }
}
