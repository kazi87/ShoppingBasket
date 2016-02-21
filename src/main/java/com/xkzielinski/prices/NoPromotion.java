package com.xkzielinski.prices;

import com.xkzielinski.model.Item;
import com.xkzielinski.model.ReceiptItem;

import java.util.Arrays;
import java.util.List;

/**
 * Default promotion = no promotion
 */
public class NoPromotion implements Promotion {

    public List<ReceiptItem> calculate(Item item, int quantity) {
        return Arrays.asList(new ReceiptItem(item, quantity));
    }
}
