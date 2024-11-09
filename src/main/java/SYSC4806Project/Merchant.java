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
    private Main main;
    private String name;
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Shop> shops;

    public Merchant() {
        super();
        this.shops = new ArrayList<>();}

    public Merchant(String name, String password, Main main) {
        super(name, password);
        this.main = main;
    }
    public List<Shop> getShops() {return shops;}
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
     * @param accountNumber of the shop
     * @return created shop or null
     */
    public Shop createShop(String name, int accountNumber) {
        if (main.getShopByName(name) != null) {
            // TODO: handle this better
            throw new IllegalArgumentException("Shop already exists");
        }
        Shop shop = new Shop(name, accountNumber, this);
        shops.add(shop);
        main.addShop(shop);
        return shop;
    }


    public boolean addProductToShop(Shop shop, Product product) {
        return shop.addProduct(product);
    }

    public boolean removeProductFromShop(Shop shop, Product product) {
        return shop.removeProduct(product);
    }

    public boolean setProductQuantity(Shop shop, Product product, int quantity) {
        if (shop.getProductList().contains(product)) {
            return shop.addInventory(product, quantity);
        }
        return false;
    }

}
