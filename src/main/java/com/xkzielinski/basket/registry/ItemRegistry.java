package com.xkzielinski.basket.registry;

import com.xkzielinski.basket.model.Item;

/**
 * Interface for available items.
 */
public interface ItemRegistry {

    Item getItemByName(String name);

}
