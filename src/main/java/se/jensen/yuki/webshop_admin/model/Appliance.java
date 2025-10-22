package se.jensen.yuki.webshop_admin.model;

public class Appliance extends Product {
    private String brand;

    public Appliance() {
        // For JSON Reader
    }

    public Appliance(String title, int price, String description, String brand) {
        super(title, price, description);
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Category category() {
        return Category.APPLIANCE;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Brand: " + brand + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
