package com.vgb.prules.demo.buyer.domain;

public class ProductMatchResult {
    private String productId;
    private String productName;
    private float qty;
    private float price;
    private boolean match;
    private int percentConditionsSatisfied;
    private int score;


    public ProductMatchResult(String productId, String productName, float qty, float price, boolean match, int percentScore, int score) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.match = match;
        this.percentConditionsSatisfied = percentScore;
        this.score = score;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public int getPercentConditionsSatisfied() {
        return percentConditionsSatisfied;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ProductMatchResult{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", match=" + match +
                ", percentConditionsSatisfied=" + percentConditionsSatisfied +
                ", weightedScore=" + score +
                '}';
    }
}
