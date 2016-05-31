package kona.model.domain.product;

import java.util.List;

public interface ProductRepository {

    List<Product> findBy(String name);

    List<Product> getAll();
}
