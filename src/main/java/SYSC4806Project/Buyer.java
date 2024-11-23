package SYSC4806Project;

// TODO: add shop search feature

import jakarta.persistence.*;

/**
 * Buyers can look for shops and find products. They have a cart that products can be put in until purchased.
 */
@Entity
public class Buyer extends User{
    @OneToOne
    private final Cart cart = new Cart();

    public Buyer(String name, String password) {
        super(name, password);
    }

    public Buyer() {
        super("default", "default");
    }

    public boolean addItemToCart(Product product) {
        return cart.addItem(product);
    }

    public boolean removeItemFromCart(Product product) {
        return cart.removeItem(product.getId());
    }

    public double getCartTotalPrice() {
        return cart.getRunningTotal();
    }
}
