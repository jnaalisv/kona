package kona.model.addresses;

import java.util.Optional;

public interface AddressRepository {

    void save(Address address);

    Optional<Address> get(long id);
}
