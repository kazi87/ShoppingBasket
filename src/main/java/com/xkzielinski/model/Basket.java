package com.xkzielinski.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple Basket implementation.
 * Maps the item name to the quantity.
 * The names have to exist in ItemRegistry (@see ItemRegistry)
 */
public class Basket {
    private final Map<String, Integer> items;

    public Basket() {
        items = new HashMap<String, Integer>();
    }

    public void addItem(String itemName, int quantity) {
        if (items.containsKey(itemName)) {
            Integer newQuantity = items.get(itemName) + quantity;
            items.put(itemName, newQuantity);
        } else {
            items.put(itemName, quantity);
        }
    }

    public Map<String, Integer> getItems() {
        return items;
    }

}
