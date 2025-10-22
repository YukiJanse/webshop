package se.jensen.yuki.webshop_admin.model;

public class Book extends Product {
    private String author;

    public Book() {
        // For JSON Reader
    }

    public Book(String title, int price, String description, String author) {
        super(title, price, description);
        this.author = author;
    }

    @Override
    public String getArticleNumber() {
        return super.getArticleNumber();
    }

    @Override
    public void setArticleNumber(String articleNumber) {
        super.setArticleNumber(articleNumber);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price);
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Category category() {
        return Category.BOOK;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Author: " + author + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
