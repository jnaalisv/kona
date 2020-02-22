package kona.http;

import kona.domain.ProductType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductType> list() {
        return Arrays.asList(ProductType.values());
    }
}
