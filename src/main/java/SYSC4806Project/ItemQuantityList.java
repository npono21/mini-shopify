package SYSC4806Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO: protect against negative quantities

/**
 * Lists Products and a quantity for each. Products must be unique by name and quantities must be non-negative.
 */
@Entity
public class ItemQuantityList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private final List<ItemQuantityPair> itemQuantityPairs = new ArrayList<>();

    public ItemQuantityList() {}


    public ItemQuantityList(ArrayList<ItemQuantityPair> list) {
        this.itemQuantityPairs.addAll(list);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * Adds a quantity of a product to a list only if that product is in the list.
     * @param product type
     * @param quantity to add
     * @return true if the amount could be added
     */
    public boolean addItems(Product product, int quantity) {

        if (product == null || !this.contains(product)) {return false;}
        else {
            for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
                if (itemQuantityPair.getProduct().equals(product)) {
                    itemQuantityPair.setQuantity(quantity + itemQuantityPair.getQuantity());
                    return true;
                }
            }
            throw new RuntimeException("wasn't able to increment quantity");
        }
    }

    /**
     * Adds a product type to the list if that type isn't already in the list.
     * @param product type
     * @return true if the product was added
     */
    public boolean addProduct(Product product) {
        if (product == null || this.contains(product)) {return false;}
        return this.add(new ItemQuantityPair(product));
    }

    // TODO: if quantity to remove is greater than current quantity, none should be removed.
    /**
     * Removes a quantity of a produce if the product is in the list and has at least that quantity already.
     * @param product type
     * @param quantity to remove
     * @return true if the quantity was removed
     */
    public boolean removeItems(Product product, int quantity) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                itemQuantityPair.setQuantity(quantity - itemQuantityPair.getQuantity());
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the product type from the list.
     * @param product type
     * @return true if the product was in the list and was removed
     */
    public boolean removeProduct(Product product) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                this.itemQuantityPairs.remove(itemQuantityPair);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(o)) {
                return true;
            }
        }
        return false;
    }

    public int getItemQuantity(Product product) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                return itemQuantityPair.getQuantity();
            }
        }

        // TODO: handle this better
        throw new RuntimeException("wasn't able to get product quantity");
    }



}
