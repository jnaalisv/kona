package kona.web.interfaces.product;

import kona.model.domain.CurrencyAmount;
import kona.model.domain.product.Product;

import java.time.LocalDateTime;

public class ProductDTO {

    public long id;
    public long version;
    public String name;
    public String productCode;
    public LocalDateTime createTime;
    public CurrencyAmount price;

    public ProductDTO() { /*jackson*/}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.version = product.getVersion();
        this.name = product.getName();
        this.productCode = product.getProductCode().toString();
        this.createTime = product.getCreateTime();
        this.price = product.getPrice();
    }
}
