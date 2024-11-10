package SYSC4806Project;

// TODO: add shop search feature

/**
 * Buyers can look for shops and find products. They have a cart that products can be put in until purchased.
 */
public class Buyer extends User{
    private final Cart cart = new Cart();

    public Buyer(String name, String password) {
        super(name, password);
    }

    /** public Shop getShop(String shopName) {
        return main.getShopByName(shopName);
    }
    */

    private boolean addItemToCart(Product product) {
        return cart.addItem(product);
    }

    private boolean removeItemFromCart(Product product) {
        return cart.removeItem(product);
    }
}
