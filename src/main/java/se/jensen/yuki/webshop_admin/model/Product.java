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
    /** counter for articleNumber **/
    private static long counter = 0;
    /** P-(number). It must be unique **/
    private String articleNumber;
    /** Name of the product **/
    private String title;
    /** Price of the product **/
    private double price;
    /** Description of the product **/
    private String description;

    public Product() {
        // For JSON Reader
    }

    public Product(String title, double price, String description) {
        this.articleNumber = createId();
        this.title = title;
        this.price = price;
        this.description = description;
    }

    /**
     * Getter for counter. It is for debug.
     *
     * @return counter.
     */
    public static long getCounter() {
        return counter;
    }

    /**
     * Getter for articleNumber.
     *
     * @return articleNumber.
     */
    public String getArticleNumber() {
        return articleNumber;
    }

    /**
     * Setter for articleNumber
     *
     * @param articleNumber "P-" and serial number.
     */
    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    /**
     * Getter for description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     *
     * @param description is a text to describe the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for price.
     *
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price.
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title.
     *
     * @param title is the title for the product.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter articleNumber if an instance is created outside the constructor.
     */
    public void initArticleNumberIfNull() {
        if (articleNumber == null) {
            articleNumber = createId();
        }
    }

    /**
     * Returns category of the class
     *
     * @return
     */
    public abstract Category category();

    /**
     * Creates a string value for articleNumber and increments the counter.
     *
     * @return a string for articleNumber.
     */
    private String createId() {
        return "P-" + (++counter);
    }

    /**
     * Updates the counter when objects are created from JSON
     *
     * @param id is the number of Product objects that are created from JSON
     */
    public static void updateCounter(long id) {
        counter = id;
    }

    /**
     * Returns a String of the instance.
     *
     * @return text of the product.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**************************************\n")
                .append("Article number: " + articleNumber + "\n")
                .append("Title: " + title + "\n")
                .append("Price: " + price + "\n");
        return sb.toString();
    }

}
