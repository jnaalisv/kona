package kona.web.interfaces.product;

import kona.model.application.ProductService;
import kona.web.interfaces.KonaWebResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDTO> search(@RequestParam(required = false) String name) {
        return productService
                .findBy(name)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}
