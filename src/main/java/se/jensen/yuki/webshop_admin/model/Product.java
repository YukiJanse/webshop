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
    private String title;
    private int price;
    private String description;

    public Product() {
        // For JSON Reader
    }

    public Product(String title, int price, String description) {
        this.articleNumber = createId();
        this.title = title;
        this.price = price;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void initArticleNumberIfNull() {
        if (articleNumber == null) {
            articleNumber = createId();
        }
    }

    public abstract Category category();

    private String createId() {
        return "P-" + (++counter);
    }

    /**
     * Updates the counter when objects are created from JSON
     *
     * @param id
     */
    public static void updateCounter(long id) {
        counter = id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**************************************\n")
                .append("Article number: " + articleNumber + "\n")
                .append("Title: " + title + "\n")
                .append("Price: " + price + "\n");
        return sb.toString();
    }

}
