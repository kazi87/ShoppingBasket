package com.xkzielinski.prices;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.xkzielinski.model.Item;
import com.xkzielinski.model.ReceiptItem;

/**
 * Simple implementation of "3 for 2" papaya promotion
 */
public class PapayasPromotion extends NoPromotion {

    private static final int PROMOTION_QUANTITY = 3;
    private final BigDecimal promotionPrice;
    private final Item papayaPromotionItem;

    public PapayasPromotion(BigDecimal papayaStandardPrice) {
        promotionPrice = papayaStandardPrice.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(3),
                new MathContext(2, RoundingMode.HALF_UP));
        papayaPromotionItem = new Item("papaya", promotionPrice);
    }


    public List<ReceiptItem> calculate(Item item, int quantity) {
        if ("papaya".equalsIgnoreCase(item.getName()) && (quantity >= PROMOTION_QUANTITY)) {
            return calculatePapayaPromotion(item, quantity);
        } else {
            return super.calculate(item, quantity);
        }
    }

    private List<ReceiptItem> calculatePapayaPromotion(Item item, int quantity) {
        List<ReceiptItem> result = new LinkedList<ReceiptItem>();
        int outOfPromotionQuantity = quantity % PROMOTION_QUANTITY;
        if(outOfPromotionQuantity > 0) {
            result.add(new ReceiptItem(item, outOfPromotionQuantity));
        }

        int promotionPackages = quantity / PROMOTION_QUANTITY;
        for (int i = 0; i < promotionPackages; i++) {
            result.add(new ReceiptItem(papayaPromotionItem, PROMOTION_QUANTITY, "3 for 2"));
        }
        return result;
    }
}
