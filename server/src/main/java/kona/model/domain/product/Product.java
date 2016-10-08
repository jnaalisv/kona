package kona.model.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence") // this is actually default
    @Column(name = "id")
    private long id;

    private String name;

    public Product() {/* hibernate */}

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
