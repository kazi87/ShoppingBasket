package com.xkzielinski.prices;

import java.util.List;
import java.util.Map;

import com.xkzielinski.model.Basket;
import com.xkzielinski.model.Item;
import com.xkzielinski.model.Receipt;
import com.xkzielinski.model.ReceiptItem;
import com.xkzielinski.registry.ItemRegistry;

/**
 * The Receipt calculation processor.
 * Calculates a Receipt based on given Basket and Promotion.
 */
public class PriceCalculationProcessor {

    public static final NoPromotion NO_PROMOTION = new NoPromotion();
    private final ItemRegistry itemRegistry;

    public PriceCalculationProcessor(ItemRegistry itemRegistry) {
        this.itemRegistry = itemRegistry;
    }

    public Receipt process(Basket basket, Promotion promotion) {
        if (basket == null) {
            throw new IllegalArgumentException("Basket can not be null");
        }
        if (promotion == null) {
            promotion = NO_PROMOTION;
        }
        return processWithPromotion(basket, promotion);
    }

    private Receipt processWithPromotion(Basket basket, Promotion promotion) {
        Receipt receipt = new Receipt();
        for (Map.Entry<String, Integer> entry : basket.getItems().entrySet()) {
            Item item = itemRegistry.getItemByName(entry.getKey());
            Integer quantity = entry.getValue();
            List<ReceiptItem> receiptItems = promotion.calculate(item, quantity);
            for (ReceiptItem ri : receiptItems) {
                receipt.addItem(ri);
            }
        }
        return receipt;
    }

}
