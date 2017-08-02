package kona.model.domain.address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    void add(Address address);

    void update(Address address);

    Optional<Address> get(long id);

    List<Address> getAll();
}
