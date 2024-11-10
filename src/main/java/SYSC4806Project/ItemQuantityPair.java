package SYSC4806Project;

import jakarta.persistence.*;

@Entity
public class ItemQuantityPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    Product product;
    int quantity;

    public ItemQuantityPair() {

    }

    public ItemQuantityPair(Product product) {
        this.product = product;
        quantity = 0;
    }

    public ItemQuantityPair(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "[product=" + product + ", quantity=" + quantity + "]";
    }

}
