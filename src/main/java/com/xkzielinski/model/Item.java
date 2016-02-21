package com.xkzielinski.model;

import java.math.BigDecimal;

/**
 * Representation of the Basket item.
 */
public class Item {

    public final static String DEFAULT_CURRENCY = "GBP";

    private String name;

    private BigDecimal price;

    private String currency;

    public Item(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.currency = DEFAULT_CURRENCY;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
