package com.xkzielinski.basket.prices;

import com.xkzielinski.basket.model.Item;
import com.xkzielinski.basket.model.ReceiptItem;

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
