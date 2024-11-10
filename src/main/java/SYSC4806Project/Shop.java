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
    private int accountNumber;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Merchant merchant;

    @OneToMany
    private final List<Product> productList = new ArrayList<>();
    @OneToOne
    private final ItemQuantityList inventory = new ItemQuantityList();

    public Shop() {};

    public Shop(String name, int accountNumber, Merchant merchant) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * Adds the product to the list of products sold by the shop, if the product isn't already listed
     * @param product to added
     * @return true if product was added or already was on the list.
     */
    public boolean addProduct(Product product) {
        if (productList.contains(product)) {return true;}
        productList.add(product);
        return inventory.addProduct(product);
    }

    /**
     * Removes the product from the list of products sold by the shop. Any inventory related to that product is also removed.
     * @param product to remove
     * @return true if the product and its inventory were removed. If the product was not in the list false is returned.
     */
    public boolean removeProduct(Product product) {
        boolean removed = productList.remove(product);
        return removed && inventory.removeProduct(product);
    }

    /**
     * If the product is offered for sale at the shop the specified quantity of the product is added to the inventory.
     * @param product type
     * @param quantity to add
     * @return true if the product is being sold by the shop and the quantity was added
     */
    public boolean addInventory(Product product, int quantity) {
        if (productList.contains(product)) {
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
     * Gets the list of products sold by the shop.
     * @return list of products
     */
    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Gets the inventory for the shop.
     * @return list of products and their quantity in the inventory
     */
    public ItemQuantityList getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shop [name=" + name + ", accountNumber=" + accountNumber + ", Merchant=(" + merchant + "), productList=" + productList + ", inventory=" + inventory + "]";
    }
}