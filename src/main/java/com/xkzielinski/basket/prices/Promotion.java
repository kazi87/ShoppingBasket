package com.xkzielinski.basket.prices;

import java.util.List;

import com.xkzielinski.basket.model.Item;
import com.xkzielinski.basket.model.ReceiptItem;

/**
 * Promotion interface
 */
public interface Promotion {

    List<ReceiptItem> calculate(Item item, int quantity);

}
