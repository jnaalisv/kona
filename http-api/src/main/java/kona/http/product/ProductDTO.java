package kona.http.product;

import kona.domain.CurrencyAmount;
import kona.domain.product.Product;
import kona.domain.product.ProductType;

import java.time.LocalDateTime;

public class ProductDTO {

    public long id;
    public long version;
    public String name;
    public String productCode;
    public LocalDateTime createTime;
    public CurrencyAmount price;
    public ProductType productType;

    public ProductDTO() { /*jackson*/}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.version = product.getVersion();
        this.name = product.getName();
        this.productCode = product.getProductCode().toString();
        this.createTime = product.getCreateTime();
        this.price = product.getPrice();
        this.productType = product.getProductType();
    }
}
