package se.jensen.yuki.webshop_admin.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cloth.class, name = "cloth"),
        @JsonSubTypes.Type(value = Appliance.class, name = "appliance"),
        @JsonSubTypes.Type(value = Book.class, name = "book")
})
public abstract class Product {
    private static long counter = 0;
    private String articleNumber;
    private String productName;
    private int price;
    private int inventoryQuantity;

    public Product() {
        // For JSON Reader
    }

    public Product(String productName, int price, int inventoryQuantity) {
        this.articleNumber = createId();
        this.productName = productName;
        this.price = price;
        this.inventoryQuantity = inventoryQuantity;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long counter) {
        Product.counter = counter;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public abstract String category();

    private String createId() {
        return "P-" + (++counter);
    }

    /**
     * Updates the counter when objects are created from JSON
     *
     * @param id
     */
    public void updateCounter(long id) {
        counter = id;
    }

}
