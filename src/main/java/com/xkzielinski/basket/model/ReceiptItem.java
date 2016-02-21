package com.xkzielinski.basket.model;

import java.math.BigDecimal;

/**
 * Represents a basket item in the context of Receipt (including promotions and quantity)
 */
public class ReceiptItem {

    private Item item;

    private Integer quantity;

    private String promotionName;

    public ReceiptItem(Item item, Integer quantity, String promotionName) {
        this.item = item;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public ReceiptItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return item.getPrice();
    }

    /**
     * @return total price of ReceiptItem (price * quantity)
     */
    public BigDecimal getTotalPrice() {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public String getPromotionName() {
        return promotionName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }
}
