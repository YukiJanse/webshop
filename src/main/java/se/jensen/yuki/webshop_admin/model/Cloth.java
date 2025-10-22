package se.jensen.yuki.webshop_admin.model;

public class Cloth extends Product {
    private String size;

    public Cloth() {
        // For JSON Reader
    }

    public Cloth(String title, int price, String description, String size) {
        super(title, price, description);
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public Category category() {
        return Category.CLOTH;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Size: " + size + "\n"
                + "Description: \n" + super.getDescription()
                + "\n**************************************\n";
    }
}
