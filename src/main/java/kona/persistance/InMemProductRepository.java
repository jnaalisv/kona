package kona.persistance;

import kona.domain.Product;
import kona.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemProductRepository implements ProductRepository {

    public Set<Product> products = new HashSet<>();

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
