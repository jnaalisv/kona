package kona.http.product;

import kona.services.ProductService;
import kona.domain.product.Product;
import kona.http.KonaHttpResources;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(KonaHttpResources.PRODUCTS)
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDTO get(@PathVariable long productId) {
        return ProductAssembler.assembleTo(productService.findBy(productId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> search(@RequestParam(required = false) String name) {
        return productService
                .findBy(name)
                .stream()
                .map(ProductAssembler::assembleTo)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {

        Product product = ProductAssembler.assembleFrom(productDTO);
        productService.save(product);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "products/" + product.getProductCode());
        return new ResponseEntity<>(ProductAssembler.assembleTo(product), responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDTO update(@PathVariable long productId, @RequestBody ProductDTO productDTO) {

        Product product = ProductAssembler.assembleFrom(productDTO);

        productService.update(product);

        return ProductAssembler.assembleTo(product);
    }

    @DeleteMapping(path = "/{productId}")
    public void delete(@PathVariable long productId) {
        productService.delete(productId);
    }
}
