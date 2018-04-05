package kona.model.application;

import kona.domain.address.Address;
import kona.domain.address.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void save(Address address) {
        addressRepository.add(address);
    }

    @Transactional
    public Address get(long id) {
        return addressRepository
                .get(id)
                .orElseThrow(() -> new RuntimeException("address not found with id=" + id));
    }

    @Transactional
    public List<Address> loadAll() {
        return this.addressRepository.getAll();
    }

    @Transactional
    public void update(Address address) {
        addressRepository.update(address);
    }
}
