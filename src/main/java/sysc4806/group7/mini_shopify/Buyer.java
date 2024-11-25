package sysc4806.group7.mini_shopify;

// TODO: add shop search feature

import jakarta.persistence.*;

/**
 * Buyers can look for shops and find products. They have a cart that products can be put in until purchased.
 */
@Entity
public class Buyer extends User{
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Cart cart = new Cart();

    public Buyer(String name, String username, String password) {
        super(name, username, password);
    }

    public Buyer() {
        super("default", "default", "default");
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
