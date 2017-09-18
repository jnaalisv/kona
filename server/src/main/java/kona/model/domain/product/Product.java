package kona.model.domain.product;

import kona.model.domain.AbstractEntity;
import kona.model.domain.CurrencyAmount;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
public class Product extends AbstractEntity {

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @NaturalId
    @Embedded
    private ProductCode productCode;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @AttributeOverrides({
        @AttributeOverride(name="amount",column=@Column(name="priceAmount")),
        @AttributeOverride(name="currency",column=@Column(name="priceCurrency"))
    })
    @Embedded
    private CurrencyAmount price;

    public Product(String name, ProductCode productCode) {
        this.name = name;
        this.productCode = productCode;
    }

    public Product(long id, long version, LocalDateTime createTime, String name, ProductCode productCode, CurrencyAmount price) {
        super(id, version);
        this.createTime = createTime;
        this.name = name;
        this.productCode = productCode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    public Product() {}

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public CurrencyAmount getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setName(String name) {
        this.name = name;
    }
}
