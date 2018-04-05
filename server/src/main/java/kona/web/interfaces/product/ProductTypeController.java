package kona.web.interfaces.product;

import kona.domain.product.ProductType;
import kona.web.interfaces.KonaWebResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(KonaWebResources.PRODUCT_TYPES)
public class ProductTypeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductType> list() {
        return Arrays.asList(ProductType.values());
    }
}
