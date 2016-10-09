package kona.web.interfaces.product;

import kona.model.domain.product.Product;

public class ProductDTO {

    public String name;
    public String productCode;

    public ProductDTO() { /*jackson*/}

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.productCode = product.getProductCode();
    }
}
