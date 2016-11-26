package kona.model.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findBy(String name);

    List<Product> getAll();

    void add(Product product);

    Optional<Product> findBy(long id);

    void update(Product product);

    int delete(long productId);
}
