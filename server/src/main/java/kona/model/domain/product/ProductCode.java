package kona.model.domain.product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductCode implements Serializable{
    private static final long serialVersionUID = 8760056029912167890L;

    @Column(name="productCode")
    private String value;

    public ProductCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public ProductCode() {/* for Hibernate */}
}
