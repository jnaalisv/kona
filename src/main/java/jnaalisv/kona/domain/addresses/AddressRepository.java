package jnaalisv.kona.domain.addresses;

import java.util.Optional;

public interface AddressRepository {

    void save(Address address);

    Optional<Address> get(long id);
}
