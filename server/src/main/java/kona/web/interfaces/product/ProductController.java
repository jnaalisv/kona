package kona.web.interfaces.product;

import kona.model.application.ProductService;
import kona.model.domain.product.Product;
import kona.model.domain.product.ProductCode;
import kona.web.interfaces.KonaWebResources;
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

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(KonaWebResources.PRODUCTS)
public class ProductController {

    private final ProductService productService;

    @Inject
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDTO get(@PathVariable long productId) {
        return new ProductDTO(productService.findBy(productId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> search(@RequestParam(required = false) String name) {
        return productService
                .findBy(name)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {

        Product product = new Product(productDTO.name, new ProductCode(productDTO.productCode));
        productService.save(product);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "products/" + product.getProductCode());
        return new ResponseEntity<>(new ProductDTO(product), responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDTO update(@PathVariable long productId, @RequestBody ProductDTO productDTO) {

        Product product = new Product(productId, productDTO.version, productDTO.createTime, productDTO.name, new ProductCode(productDTO.productCode), productDTO.price);

        productService.update(product);

        return new ProductDTO(product);
    }

    @DeleteMapping(path = "/{productId}")
    public void delete(@PathVariable long productId) {
        productService.delete(productId);
    }
}
