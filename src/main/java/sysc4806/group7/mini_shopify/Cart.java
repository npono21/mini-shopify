package sysc4806.group7.mini_shopify;

// TODO: add running total

import jakarta.persistence.*;

/**
 * Cart hold a list of all the products in it and how many of each there are. The running total of everything in the cart
 * is also held.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private final ItemQuantityList items = new ItemQuantityList();
    private double runningTotal = 0.00;

    public Cart() {}

    public boolean addProduct(Product product) {
        return items.addProduct(product);
    }

    public boolean removeProduct(Long id) {
        runningTotal -= items.getTotalProductCost(id);
        return items.removeProduct(id);
    }

    public boolean addItem(Product product) {
        if (product == null) return false;
        items.addProduct(product); // make sure the product type is in the list
        return addItems(product, 1);
    }

    public boolean addItem(Long productId) {
        return addItems(productId, 1);
    }

    public boolean addItems(Long productId, int quantity) {
        Product product = items.getProductById(productId);
        if (product == null) {
            return false;
        } else {
            return addItems(product, quantity);
        }
    }

    public boolean addItems(Product product, int quantity) throws RuntimeException {
        if (product == null) return false;
        boolean productInCart = items.contains(product);
        if (!productInCart) {
            items.addProduct(product);
        }

        int quantityInCart = items.getItemQuantity(product);
        if (quantity + ((quantityInCart == -1) ? 0 : quantityInCart) > product.getShopInventoryForProduct()) {
            throw new RuntimeException("Shop only has " + product.getShopInventoryForProduct()
                    + " of that item. Your request can not be fulfilled");
        }
        runningTotal += product.getPrice() * quantity;
        return items.addItems(product, quantity);
    }

    public boolean removeItem(Long productId) {
        if (productId == null) return false;
        return removeItems(productId, 1);
    }

    public boolean removeItems(Long productId, int quantity) {
        if (productId == null) return false;
        Product product = items.getProductById(productId);
        if (product == null) {
            return false;
        }
        return removeItems(product, quantity);
    }

    public boolean removeItems(Product product, int quantity) {
        if (product == null) return false;
        boolean removed = items.removeItems(product, quantity);
        if (removed) {
            runningTotal -= product.getPrice() * quantity;
        }
        if (items.getItemQuantity(product) == 0) {items.removeProduct(product);}
        return removed;
    }

    public double getTotalCostForProduct(Long productId) {
        return items.getTotalProductCost(productId);
    }

    public int getQuantity(Long id) {
        return items.getItemQuantity(id);
    }

    public boolean updateQuantity(Long productId, int quantity) {
        int productInCart = items.getItemQuantity(productId);
        if (productInCart > quantity) {
            return removeItems(productId, productInCart - quantity);
        } else {
            return addItems(productId, quantity - productInCart);
        }
    }

    public double getRunningTotal() {
        return runningTotal;
    }

   public void checkout() {
       System.out.println(items);
        for (ItemQuantityPair p : items) {
            p.product.getShop().removeInventory(p.product, p.quantity);
        }
        System.out.println(items);
        items.removeAll();
        runningTotal = 0.0;
    }

    public ItemQuantityList getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id + ", items=" + items + ", runningTotal=$" + runningTotal + "]";
    }
}
