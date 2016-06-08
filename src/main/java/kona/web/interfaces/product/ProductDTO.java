package kona.web.interfaces.product;

import kona.model.domain.product.Product;

public class ProductDTO {

    public Long id;
    public String name;

    public ProductDTO() { /*jackson*/}

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.id = product.getId();
    }
}