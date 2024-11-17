package SYSC4806Project;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lists Products and a quantity for each. Products must be unique by name and quantities must be non-negative.
 */
@Entity
public class ItemQuantityList implements Iterable<ItemQuantityPair> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
     * @param productId type
     * @param quantity to add
     * @return true if the amount could be added
     */
    public boolean addItems(Long productId, int quantity) {
        Product product = this.getProductById(productId);
        return addItems(product, quantity);
    }

    public List<ItemQuantityPair> getItemQuantityPairs() {return itemQuantityPairs;}

    /**
     * Adds a quantity of a product to a list only if that product is in the list.
     * @param product type
     * @param quantity to add
     * @return true if the amount could be added
     */
    public boolean addItems(Product product, int quantity) {
        if (product == null || quantity < 0) {return false;}
        else {
            for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
                if (itemQuantityPair.getProduct().equals(product)) {
                    itemQuantityPair.setQuantity(quantity + itemQuantityPair.getQuantity());
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Adds a product type to the list if that type isn't already in the list.
     * @param product type
     * @return true if the product was added
     */
    public boolean addProduct(Product product) {
        if (product == null || this.contains(product)) {return false;}
        return this.itemQuantityPairs.add(new ItemQuantityPair(product));
    }

    /**
     * Removes a quantity of a produce if the product is in the list and has at least that quantity already.
     * @param product type
     * @param quantity to remove
     * @return true if the quantity was removed
     */
    public boolean removeItems(Product product, int quantity) {
        if (product == null) return false;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                if (itemQuantityPair.quantity < quantity) {
                    System.out.println("Attempted to remove more quantity than available. None were removed.");
                    return false;
                }
                itemQuantityPair.setQuantity(itemQuantityPair.getQuantity() - quantity);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a quantity of a product if the id is in the list and has at least that quantity already.
     * @param id of the product
     * @param quantity to remove
     * @return true if the quantity was removed
     */
    public boolean removeItems(Long id, int quantity) {
        if (id == null) return false;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getId().equals(id)) {
                if (itemQuantityPair.quantity < quantity) {
                    System.out.println("Attempted to remove more quantity than available. None were removed.");
                    return false;
                }
                itemQuantityPair.setQuantity(itemQuantityPair.getQuantity() - quantity);
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
        if (product == null) return false;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                this.itemQuantityPairs.remove(itemQuantityPair);
                return true;
            }
        }return false;
    }

    /**
     * Removes the product type from the list.
     * @param id of the product
     * @return true if the id was in the list and was removed
     */
    public boolean removeProduct(Long id) {
        if (id == null) return false;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getId().equals(id)) {
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

    public int getItemQuantity(Long id) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getId().equals(id)) {
                return itemQuantityPair.getQuantity();
            }
        }

        // TODO: handle this better
        throw new RuntimeException("wasn't able to get product quantity");
    }

    public int getItemQuantity(Product product) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().equals(product)) {
                return itemQuantityPair.getQuantity();
            }
        }
        throw new RuntimeException("Product not in list"); // Product not in list
    }

    public Product getProductById(Long id) {
        if (id == null) return null;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getId().equals(id)) {
                return itemQuantityPair.getProduct();
            }
        }
        return null;
    }

    public Product getProductByName(String name) {
        if (name == null) return null;
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getName().equals(name)) {
                return itemQuantityPair.getProduct();
            }
        }
        return null;
    }

    public double getTotalProductCost(Long id) {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            if (itemQuantityPair.getProduct().getId().equals(id)) {
                return itemQuantityPair.getTotalCost();
            }
        }
        throw new RuntimeException("wasn't able to get total cost");
    }

    public void removeAll() {
        for (ItemQuantityPair itemQuantityPair : this.itemQuantityPairs) {
            itemQuantityPairs.remove(itemQuantityPair);
        }
    }

    @Override
    public String toString() {
        return itemQuantityPairs.toString();
    }

    public int size() {
        return itemQuantityPairs.size();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ItemQuantityPair> iterator() {
        return itemQuantityPairs.iterator();
    }
}
