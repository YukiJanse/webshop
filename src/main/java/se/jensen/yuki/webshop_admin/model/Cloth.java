package se.jensen.yuki.webshop_admin.model;

/**
 * The Cloth class represents a type of product that is categorized as clothing.
 * This class extends the Product class and introduces the 'size' property specific to clothing items.
 */
public class Cloth extends Product {
    private String size;

    /**
     * Default constructor for the Cloth class.
     * This constructor is primarily intended to be used for JSON deserialization.
     */
    public Cloth() {
        // For JSON Reader
    }

    /**
     * Constructs a new Cloth instance with the specified title, price, description, and size.
     *
     * @param title       The title of the cloth.
     * @param price       The price of the cloth.
     * @param description The description of the cloth.
     * @param size        The size of the cloth.
     */
    public Cloth(String title, double price, String description, String size) {
        super(title, price, description);
        this.size = size;
    }

    /**
     * Retrieves the article number of this cloth product.
     *
     * @return the article number as a String.
     */
    @Override
    public String getArticleNumber() {
        return super.getArticleNumber();
    }

    /**
     * Sets the article number for the cloth item.
     *
     * @param articleNumber the article number to set. Expected to begin with "P-" followed by a serial number.
     */
    @Override
    public void setArticleNumber(String articleNumber) {
        super.setArticleNumber(articleNumber);
    }

    /**
     * Retrieves the description of the cloth product.
     *
     * @return the description of the product as a String.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Sets the description for the cloth item.
     *
     * @param description a text to describe the cloth product.
     */
    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    /**
     * Retrieves the price of the cloth product.
     *
     * @return the price of the product as a double.
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * Sets the price of the cloth product.
     *
     * @param price the price to be set for the cloth product
     */
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    /**
     * Retrieves the title of the product.
     *
     * @return the title of the product as a String.
     */
    @Override
    public String getTitle() {
        return super.getTitle();
    }

    /**
     * Sets the title of the cloth item.
     *
     * @param title the title to be set for the cloth item.
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    /**
     * Retrieves the size of the cloth.
     *
     * @return the size of the cloth as a String.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the cloth.
     *
     * @param size the size to be set for the cloth
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Retrieves the category of the cloth product.
     *
     * @return the category of the product as {@code Category.CLOTH}.
     */
    @Override
    public Category category() {
        return Category.CLOTH;
    }

    /**
     * Returns a string representation of the cloth object, including its size, description,
     * and details inherited from the parent class.
     *
     * @return a detailed string representation of the cloth object.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Size: " + size + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
