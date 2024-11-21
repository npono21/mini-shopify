package SYSC4806Project;

import jakarta.persistence.*;

@Entity
public class ItemQuantityPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Product product;
    int quantity;
    double totalCost;

    public ItemQuantityPair() {

    }

    public ItemQuantityPair(Product product) {
        this.product = product;
        quantity = 0;
        totalCost = 0;
    }

    public ItemQuantityPair(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        totalCost = 0;
    }

    public double getTotalCost() {
        return totalCost;
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
        if (quantity < 0){
            System.out.println("Negative quantities are not permitted.");
            return;
        }
        this.quantity = quantity;
        totalCost = quantity * product.getPrice();
    }

    @Override
    public String toString() {
        return "[product=" + product + ", quantity=" + quantity + "]";
    }

}
