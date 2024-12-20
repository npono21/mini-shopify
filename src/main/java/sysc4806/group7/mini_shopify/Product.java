package sysc4806.group7.mini_shopify;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// TODO: protect against negative prices

/**
 * Products have a name and price that they can be sold for. Tags help buyers find types of products.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();
    @Lob
    private byte[] image;
    private String imagePath;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Shop shop;

    public Product() {}
    public Product(String name, String description, double price, Shop shop) {
        this.name = name;
        this.description = description;
        this.price = price;
        imagePath = "/images/Mini-Shopify Logo.png";
        this.shop = shop;
    }
    public Product(String name, String description, double price, byte[] image, Shop shop) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.shop = shop;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    public void removeAllTags() {
        tags.removeAll(tags);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public int getShopInventoryForProduct() {
        return shop.getProductInventory(this);
    }


    public Shop getShop() {
        return shop;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return this.name.equals(product.name) && this.description.equals(product.description) && this.price == product.price;

    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", tags=" + tags + "]";
    }

    public String getImagePath() {
        return imagePath;
    }
}