package se.jensen.yuki.webshop_admin.model;

/**
 * Represents an appliance product in the system. This class is a specialized type of Product
 * and includes additional properties such as brand.
 */
public class Appliance extends Product {
    private String brand;

    /**
     * Default constructor for the Appliance class.
     * This constructor is primarily intended for JSON deserialization
     * and should be used when creating instances from external data sources.
     */
    public Appliance() {
        // For JSON Reader
    }

    /**
     * Constructs a new instance of the Appliance class with the specified title, price,
     * description, and brand. This constructor initializes the appliance object by
     * invoking the parent class's constructor and setting the brand property.
     *
     * @param title       The title of the appliance.
     * @param price       The price of the appliance.
     * @param description The description of the appliance.
     * @param brand       The brand of the appliance.
     */
    public Appliance(String title, double price, String description, String brand) {
        super(title, price, description);
        this.brand = brand;
    }

    /**
     * Retrieves the article number of the appliance.
     *
     * @return the article number as a String.
     */
    @Override
    public String getArticleNumber() {
        return super.getArticleNumber();
    }

    /**
     * Sets the article number for the appliance.
     *
     * @param articleNumber the article number to set. Should begin with "P-" followed by a serial number.
     */
    @Override
    public void setArticleNumber(String articleNumber) {
        super.setArticleNumber(articleNumber);
    }

    /**
     * Retrieves the description of the appliance.
     * This method overrides the parent class implementation to provide
     * the description for the appliance product.
     *
     * @return the description of the appliance as a String.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Sets the description of the appliance.
     * This method overrides the parent class implementation to provide
     * a description specific to the appliance instance.
     *
     * @param description a text to describe the appliance.
     */
    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    /**
     * Retrieves the price of the appliance.
     * This method overrides the parent class implementation
     * and delegates the call to the superclass.
     *
     * @return the price of the appliance as a double.
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * Sets the price of the appliance.
     * This method overrides the parent class implementation and
     * delegates the call to the superclass's {@code setPrice} method.
     *
     * @param price the price to be set for the appliance.
     */
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    /**
     * Retrieves the title of the appliance.
     * This method overrides the parent class implementation
     * and delegates the call to the superclass's {@code getTitle} method.
     *
     * @return the title of the appliance as a String.
     */
    @Override
    public String getTitle() {
        return super.getTitle();
    }

    /**
     * Sets the title for the appliance.
     * This method overrides the parent class implementation and
     * delegates the call to the superclass's {@code setTitle} method.
     *
     * @param title the title to be set for the appliance.
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    /**
     * Retrieves the brand of the appliance.
     *
     * @return the brand of the appliance as a String.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the appliance.
     *
     * @param brand the brand to be set for the appliance.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Retrieves the category of the appliance.
     *
     * @return the category of the appliance as {@code Category.APPLIANCE}.
     */
    @Override
    public Category category() {
        return Category.APPLIANCE;
    }

    /**
     * Returns a string representation of the appliance, including its brand,
     * description, and details inherited from the parent class.
     *
     * @return a detailed string representation of the appliance object.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Brand: " + brand + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
