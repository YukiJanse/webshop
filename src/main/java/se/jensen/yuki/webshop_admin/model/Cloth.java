package se.jensen.yuki.webshop_admin.model;

public class Cloth extends Product {

    public Cloth() {
        // For JSON Reader
    }

    public Cloth(String productName, int price, int inventoryQuantity) {
        super(productName, price, inventoryQuantity);
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
    public int getInventoryQuantity() {
        return super.getInventoryQuantity();
    }

    @Override
    public void setInventoryQuantity(int inventoryQuantity) {
        super.setInventoryQuantity(inventoryQuantity);
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
    public String getProductName() {
        return super.getProductName();
    }

    @Override
    public void setProductName(String productName) {
        super.setProductName(productName);
    }

    @Override
    public String category() {
        return Category.CLOTH.getString();
    }
}
