package kona.model.domain.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Inject
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> findBy(String name) {
        if (name == null) {
            return productRepository.getAll();
        } else {
            return productRepository.findBy(name);
        }
    }
}
