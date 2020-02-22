package kona.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findBy(String name) {
        if (name == null) {
            return productRepository.getAll();
        } else {
            return productRepository.findBy(name);
        }
    }

    public void save(Product product) {
        productRepository.add(product);
    }

    public Product findBy(long id) {
        return productRepository
                .findBy(id)
                .orElseThrow(() -> new NotFoundException(Product.class, "id="+id));
    }

    public void update(Product product) {
        productRepository.update(product);
    }

    public void delete(long productId) {
        int deletedRows = productRepository.delete(productId);
        if (deletedRows == 0) {
            throw new NotFoundException(Product.class, "id="+productId);
        }
    }
}
