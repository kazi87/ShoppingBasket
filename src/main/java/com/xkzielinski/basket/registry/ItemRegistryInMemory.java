package com.xkzielinski.basket.registry;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.xkzielinski.basket.model.Item;

/**
 * Simple in-memory implementation of the ItemRegistry interface.
 */
public class ItemRegistryInMemory implements ItemRegistry {
    private final Map<String, Item> registry = new HashMap() {
            {
                put("apple", new Item("Apple", new BigDecimal(0.25), Item.DEFAULT_CURRENCY));
                put("orange", new Item("Orange", new BigDecimal(0.30), Item.DEFAULT_CURRENCY));
                put("garlic", new Item("Garlic", new BigDecimal(0.15), Item.DEFAULT_CURRENCY));
                put("papaya", new Item("Papaya", new BigDecimal(0.50), Item.DEFAULT_CURRENCY));

            }
        };

    public Item getItemByName(String name) {
        if(name == null ){
            throw new IllegalArgumentException("Item name can not be null");
        }
        String nameNormalized = name.toLowerCase();
        if (!registry.containsKey(nameNormalized)) {
            throw new IllegalArgumentException("Item with given name does not exist in the registry: " + name +
                ". Available items:" + registry.keySet());
        }
        return registry.get(nameNormalized);
    }
}
