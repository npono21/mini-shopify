package SYSC4806Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO: enforce unique product name within the shop

/**
 * A Shop has a name and a merchant who runs it. It contains a list of products available to purchase and the quantities
 * available for each product. It has an account number that payments for products are sent to.
 */
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Merchant merchant;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Product> products = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final ItemQuantityList inventory = new ItemQuantityList();

    public Shop() {};

    public Shop(String name, String description, Merchant merchant) {
        this.name = name;
        this.description = description;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Product> getProducts() {
        return products;
    }

    /**
     * Adds the product to the list of products sold by the shop, if the product isn't already listed
     * @param product to added
     * @return true if product was added or already was on the list.
     */
    public boolean addProduct(Product product) {
        if (products.contains(product)) {return true;}
        products.add(product);
        return inventory.addProduct(product);
    }

    /**
     * Removes the product from the list of products sold by the shop. Any inventory related to that product is also removed.
     * @param product to remove
     * @return true if the product and its inventory were removed. If the product was not in the list false is returned.
     */
    public boolean removeProduct(Product product) {
        boolean removed = products.remove(product);
        return removed && inventory.removeProduct(product);
    }

    // TODO: Guard against negative quantity when adding (this case
    // to be covered by removeInventory())
    /**
     * If the product is offered for sale at the shop the specified quantity of the product is added to the inventory.
     * @param product type
     * @param quantity to add
     * @return true if the product is being sold by the shop and the quantity was added
     */
    public boolean addInventory(Product product, int quantity) {
        if (quantity <= 0){
            return false;
        }
        if (products.contains(product)) {
            return inventory.addItems(product, quantity);
        }
        return false;
    }

    /**
     * Removes the specified quantity of the product from the inventory. If the entire quantity cannot be removed none
     * of the inventory is removed.
     * @param product type
     * @param quantity to remove
     * @return true if the whole quantity was able tobe removed.
     */
    public boolean removeInventory(Product product, int quantity) {
        return inventory.removeItems(product, quantity);
    }


    /**
     * Gets the inventory for the shop.
     * @return list of products and their quantity in the inventory
     */
    public ItemQuantityList getInventory() {
        return inventory;
    }

    public int getProductInventory(Product product) {
        return inventory.getItemQuantity(product);
    }

    @Override
    public String toString() {
        return "Shop [name=" + name + ", id=" + this.getId() + ", Merchant=(" + merchant + "), productList=" + products + ", inventory=" + inventory + "]";
    }
}