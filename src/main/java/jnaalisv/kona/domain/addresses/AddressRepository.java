package jnaalisv.kona.domain.addresses;

import java.util.Optional;

public interface AddressRepository {

    Address save(Address address);

    Optional<Address> get(long id);
}
