package kona.web.interfaces.product;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kona.model.application.ProductService;
import kona.model.domain.product.Product;
import kona.web.interfaces.KonaWebResources;

@RestController
@RequestMapping(KonaWebResources.PRODUCTS)
public class ProductController {

    private final ProductService productService;

    @Inject
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDTO get(@PathVariable long id) {
        return new ProductDTO(productService.load(id));
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
    public ResponseEntity<ProductDTO> postNewProduct(@RequestBody ProductDTO aNewProduct) {

        Product product = new Product(0l, aNewProduct.name);
        productService.save(product);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "products/" + product.getId());
        return new ResponseEntity<>(new ProductDTO(product), responseHeaders, HttpStatus.CREATED);
    }
}
