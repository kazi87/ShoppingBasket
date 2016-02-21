package com.xkzielinski.prices;

import java.util.List;

import com.xkzielinski.model.Item;
import com.xkzielinski.model.ReceiptItem;

/**
 * Promotion interface
 */
public interface Promotion {

    List<ReceiptItem> calculate(Item item, int quantity);

}
