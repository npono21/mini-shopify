package SYSC4806Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO: enforce shop name uniqueness

/**
 * Merchants have a name and a list of shops they manage.
 */
@Entity
public class Merchant extends User {

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Shop> shops;

    public Merchant() {
        super();
        this.shops = new ArrayList<>();}

    public Merchant(String name, String password) {
        super(name, password);
        this.shops = new ArrayList<>();
    }

    /**
     * Gets the shops of this merchant.
     * @return shops, the list of shops.
     */
    public List<Shop> getShops() {return this.shops;}
    /**
     * Adds a shop to the list of shop for this merchant.
     * @param shop to be added to the list of shops.
     */
    public void addShop(Shop shop) {this.shops.add(shop);}

    /**
     * Remove a shop.
     * @param shop to remove
     * @return true if the list contained the shop and it was removed
     */
    private boolean removeShop(Shop shop) {
        return shops.remove(shop);
    }

    /**
     * Create a new shop associated with the merchant. Shop is not permitted to have the same name as any other shop in
     * the system.
     * @param name of the shop
     * @return created shop or null
     */
    public Shop createShop(String name, String description) {
        Shop shop = new Shop(name, description, this);
        shops.add(shop);
        return shop;
    }


    public boolean addProductToShop(Shop shop, Product product) {
        return shop.addProduct(product);
    }

    public boolean removeProductFromShop(Shop shop, Product product) {
        return shop.removeProduct(product);
    }

    public boolean setProductQuantity(Shop shop, Product product, int quantity) {
        if (shop.getProducts().contains(product)) {
            return shop.addToInventory(product, quantity);
        }
        return false;
    }

}