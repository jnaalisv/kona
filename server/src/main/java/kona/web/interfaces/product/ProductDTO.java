package kona.web.interfaces.product;

import kona.model.domain.product.Product;

public class ProductDTO {

    public long version;
    public String name;
    public String productCode;

    public ProductDTO() { /*jackson*/}

    public ProductDTO(Product product) {
        this.version = product.getVersion();
        this.name = product.getName();
        this.productCode = product.getProductCode().toString();
    }
}
