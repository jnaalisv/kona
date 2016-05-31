package kona.model.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    private String name;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
