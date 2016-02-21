package com.xkzielinski.registry;

import com.xkzielinski.model.Item;

/**
 * Interface for available items.
 */
public interface ItemRegistry {

    Item getItemByName(String name);

}
