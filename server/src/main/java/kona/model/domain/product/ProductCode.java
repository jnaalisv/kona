package kona.model.domain.product;

import javax.persistence.Column;

public class ProductCode {

    @Column(name="product_code")
    private String value;

    public ProductCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
