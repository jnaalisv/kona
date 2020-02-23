package kona.persistance;

import kona.domain.Product;
import kona.domain.ProductRepository;
import kona.domain.ProductType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemProductRepository implements ProductRepository {

    public Set<Product> products = Set.of(
            new Product(1, 0, LocalDateTime.now(), "Typewriter, Mk. I.", "TW.I", ProductType.EQUIPMENT, new BigDecimal("120.00")),
            new Product(2, 0, LocalDateTime.now(), "Central Computing Unit", "CCU", ProductType.EQUIPMENT, new BigDecimal("280.00")),
            new Product(3, 0, LocalDateTime.now(), "Liquid Crystal Display", "LCD", ProductType.EQUIPMENT, new BigDecimal("300.00")),
            new Product(4, 0, LocalDateTime.now(), "Key Based Input Device, Mk. I", "KBID.I", ProductType.EQUIPMENT, new BigDecimal("400.00")),
            new Product(5, 0, LocalDateTime.now(), "Portable Touch-Screen Device, prototype", "PTSD", ProductType.EQUIPMENT, new BigDecimal("500.00")),
            new Product(6, 0, LocalDateTime.now(), "Reserve Power Pack", "RPP", ProductType.EQUIPMENT, new BigDecimal("600.00")),
            new Product(7, 0, LocalDateTime.now(), "Portable Computing Unit (without Display)", "PCU", ProductType.EQUIPMENT, new BigDecimal("700.00")),
            new Product(8, 0, LocalDateTime.now(), "Long Range Wireless Signal Enhancer, prototype", "LRWSE", ProductType.EQUIPMENT, new BigDecimal("800.00")),
            new Product(9, 0, LocalDateTime.now(), "Network Adapter", "NA", ProductType.EQUIPMENT, new BigDecimal("130.00")),
            new Product(10, 0, LocalDateTime.now(), "High Performance Computing Unit, Mk.II", "HPCU", ProductType.EQUIPMENT, new BigDecimal("120.00")),
            new Product(11, 0, LocalDateTime.now(), "Standard Mouse, Mk. I", "SM.I", ProductType.EQUIPMENT, new BigDecimal("170.00"))
    );

    @Override
    public List<Product> findBy(String name) {
        return products
                .stream()
                .filter(product -> product.name().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public Optional<Product> findBy(long id) {
        return products
                .stream()
                .filter(product -> product.id() == id)
                .findFirst();
    }

    @Override
    public void update(Product product) {
        products.add(product);
    }

    @Override
    public int delete(long productId) {
        return 0;
    }
}
