package kona.http;

import kona.domain.Product;
import kona.domain.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product get(@PathVariable long productId) {
        return productService.findBy(productId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> search(@RequestParam(required = false) String name) {
        return productService.findBy(name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "products/" + product.id());
        return new ResponseEntity<>(product, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product update(@PathVariable long productId, @RequestBody Product product) {
        productService.update(product);
        return product;
    }

    @DeleteMapping(path = "/{productId}")
    public void delete(@PathVariable long productId) {
        productService.delete(productId);
    }
}
