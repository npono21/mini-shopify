package SYSC4806Project;

// TODO: add running total

/**
 * Cart hold a list of all the products in it and how many of each there are. The running total of everything in the cart
 * is also held.
 */
public class Cart {
    private final ItemQuantityList items = new ItemQuantityList();
    private double runningTotal;

    public Cart() {}

    public boolean addItem(Product product) {
        return addItems(product, 1);

    }

    public boolean addItems(Product product, int quantity) {
        boolean productInCart = items.contains(product);
        if (!productInCart) {
            items.addProduct(product);
        }
        return items.addItems(product, quantity);
    }

    public boolean removeItem(Product product) {
        return removeItems(product, 1);
    }

    public boolean removeItems(Product product, int quantity) {
        boolean removed = items.removeItems(product, quantity);
        if (items.getItemQuantity(product) == 0) {items.removeProduct(product);}
        return removed;
    }

    public ItemQuantityList getItems() {
        return items;
    }

    public double getRunningTotal() {
        return runningTotal;
    }
}
