package kona.model.application;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;

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

    @Transactional
    public void save(Product product) {
        productRepository.add(product);
    }

    @Transactional
    public Product load(long id) {
        return productRepository
                .get(id)
                .orElseThrow(() -> NotFoundException.byId(Product.class, id));
    }
}
