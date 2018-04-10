package kona.application.services;

import kona.domain.product.Product;
import kona.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

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
    public Product findBy(long id) {
        return productRepository
                .findBy(id)
                .orElseThrow(() -> new NotFoundException(Product.class, "id="+id));
    }

    @Transactional
    public void update(Product product) {
        productRepository.update(product);
    }

    @Transactional
    public void delete(long productId) {
        int deletedRows = productRepository.delete(productId);
        if (deletedRows == 0) {
            throw new NotFoundException(Product.class, "id="+productId);
        }
    }
}
