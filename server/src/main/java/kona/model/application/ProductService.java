package kona.model.application;

import kona.model.domain.product.Product;
import kona.model.domain.product.ProductRepository;
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

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public Product load(String productCode) {
        return productRepository
                .get(productCode)
                .orElseThrow(() -> new NotFoundException(Product.class, "productCode="+productCode));
    }

    @Transactional
    public void update(Product product) {
        productRepository.update(product);
    }
}
