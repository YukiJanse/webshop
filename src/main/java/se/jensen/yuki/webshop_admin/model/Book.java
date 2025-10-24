package se.jensen.yuki.webshop_admin.model;

/**
 * Represents a book as a specific type of product.
 * This class extends the {@code Product} class and adds the author property
 * specific to books.
 */
public class Book extends Product {
    private String author;

    /**
     * Default constructor for the Book class.
     * This constructor is primarily used for JSON deserialization.
     */
    public Book() {
        // For JSON Reader
    }

    /**
     * Constructs a new Book instance with the specified title, price, description, and author.
     *
     * @param title       The title of the book.
     * @param price       The price of the book.
     * @param description The description of the book.
     * @param author      The author of the book.
     */
    public Book(String title, double price, String description, String author) {
        super(title, price, description);
        this.author = author;
    }

    /**
     * Retrieves the article number of the product.
     *
     * @return the article number as a String.
     */
    @Override
    public String getArticleNumber() {
        return super.getArticleNumber();
    }

    /**
     * Sets the article number for a book.
     *
     * @param articleNumber the article number to set. Should begin with "P-" followed by a serial number.
     */
    @Override
    public void setArticleNumber(String articleNumber) {
        super.setArticleNumber(articleNumber);
    }

    /**
     * Retrieves the description of the product.
     *
     * @return the description of the product as a String.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Sets the description for the book.
     *
     * @param description a text to describe the book.
     */
    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product as a double.
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * Sets the price of the book.
     *
     * @param price the price to be set for the book
     */
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    /**
     * Retrieves the title of the product.
     *
     * @return the title of the product
     */
    @Override
    public String getTitle() {
        return super.getTitle();
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title to be set for the book.
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    /**
     * Retrieves the author of the book.
     *
     * @return the author of the book as a String.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author to be set for the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Retrieves the category of the book.
     *
     * @return the category of the product as {@code Category.BOOK}.
     */
    @Override
    public Category category() {
        return Category.BOOK;
    }

    /**
     * Returns a string representation of the book, including its author, description,
     * and details inherited from the parent class.
     *
     * @return a detailed string representation of the book object.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Author: " + author + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
