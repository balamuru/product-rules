package com.vgb.prules.demo.buyer.domain;

/**
 * Value Object containing the result of a product match across all rules
 */
public class ProductMatchResult {
    private String productId;
    private String productName;
    private int qty;
    private float price;
    private boolean match;
    private float percentConditionsSatisfied;
    private float score;

    /**
     * Constructor
     * @param productId
     * @param productName
     * @param qty
     * @param price
     * @param match
     * @param percentScore
     * @param score
     */
    public ProductMatchResult(String productId, String productName, int qty, float price, boolean match, float percentScore, float score) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.match = match;
        this.percentConditionsSatisfied = percentScore;
        this.score = score;
    }

    /**
     * Get product id
     * @return
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Set product id
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Get product name
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Get product quantity
     * @return
     */
    public int getQty() {
        return qty;
    }

    /**
     * Get product price
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Did product match exceed the treshold
     * @return
     */
    public boolean isMatch() {
        return match;
    }

    /**
     * Get percent of conditions satisfied
     * @return
     */
    public float getPercentConditionsSatisfied() {
        return percentConditionsSatisfied;
    }

    /**
     * Get weighted score
     * @return
     */
    public float getScore() {
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
