package com.vgb.prules.demo.buyer.domain;

public class ProductMatchResult {
    private String productId;
    private String productName;
    private float qty;
    private float price;
    private int percentScore;
    private boolean match;

    public ProductMatchResult(String productId, String productName, float qty, float price, boolean match, int percentScore) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.match = match;
        this.percentScore = percentScore;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getQty() {
        return qty;
    }

    public float getPrice() {
        return price;
    }

    public boolean isMatch() {
        return match;
    }

    public int getPercentScore() {
        return percentScore;
    }

    @Override
    public String toString() {
        return "ProductMatchResult{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", percentScore=" + percentScore +
                ", match=" + match +
                '}';
    }
}
