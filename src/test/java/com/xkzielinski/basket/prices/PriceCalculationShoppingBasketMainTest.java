package com.xkzielinski.basket.prices;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import com.xkzielinski.basket.model.Basket;
import com.xkzielinski.basket.model.Item;
import com.xkzielinski.basket.model.Receipt;
import com.xkzielinski.basket.model.ReceiptItem;
import com.xkzielinski.basket.registry.ItemRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO: Add description...
 */
public class PriceCalculationShoppingBasketMainTest {

    /**
     * return items always with fixed price 10!
     */
    private final static ItemRegistry FIXED_PRICE_REGISTRY = new ItemRegistry(){
        public Item getItemByName(String name) {
            return new Item(name, BigDecimal.TEN);
        }
    };

    public static final BigDecimal PRICE_ZERO = BigDecimal.ZERO.setScale(2, RoundingMode.CEILING);

    private Promotion freeApplePromotion = new Promotion() {
            public List<ReceiptItem> calculate(Item item, int quantity) {
                List<ReceiptItem> result = new LinkedList<ReceiptItem>();

                if ("Apple".equals(item.getName())) {
                    Item freeAppleItem = new Item(item.getName(), BigDecimal.ZERO);
                    result.add(new ReceiptItem(freeAppleItem, quantity, "freeApplePromotion"));
                } else {
                    result.add(new ReceiptItem(item, quantity));
                }
                return result;
            }
        };

    private PriceCalculationProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new PriceCalculationProcessor(FIXED_PRICE_REGISTRY);
    }

    @Test
    public void verifySampleBasketWithoutPromotion() throws Exception {
        // give
        Basket basket = new Basket();
        basket.addItem("Apple", 2);
        basket.addItem("Orange", 2);
        basket.addItem("Garlic", 2);
        BigDecimal expectedPrice = BigDecimal.valueOf(10 * 6).setScale(2, RoundingMode.CEILING);
        // when
        Receipt receipt = processor.process(basket, null);
        // then
        assertEquals(expectedPrice, receipt.getTotalPrice());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullBasketShouldCausesException() throws Exception {
        // give
        // when
        processor.process(null, null);
        // then
    }

    @Test
    public void emptyBasketShouldCostZero() throws Exception {
        // give
        Basket basket = new Basket();
        // when
        Receipt receipt = processor.process(basket, null);
        // then
        assertEquals(PRICE_ZERO, receipt.getTotalPrice());
    }

    @Test
    public void verifyFreeApplePromotionWithOnlyApplesInBasket() throws Exception {
        // give
        Basket basket = new Basket();
        basket.addItem("Apple", 2);
        Promotion promotion = freeApplePromotion;
        // when
        Receipt receipt = processor.process(basket, promotion);
        // then
        assertEquals(PRICE_ZERO, receipt.getTotalPrice());
    }

    @Test
    public void verifyFreeApplePromotion() throws Exception {
        // give
        Basket basket = new Basket();
        basket.addItem("Apple", 2);
        basket.addItem("Garlic", 10);
        Promotion promotion = freeApplePromotion;
        BigDecimal expectedPrice = BigDecimal.valueOf(100).setScale(2, RoundingMode.CEILING);
        // when
        Receipt receipt = processor.process(basket, promotion);
        // then
        assertEquals(expectedPrice, receipt.getTotalPrice());
    }

    @Test
    public void ShouldShowPromotionNAme() throws Exception {
        // give
        Basket basket = new Basket();
        basket.addItem("Apple", 1);
        Promotion promotion = freeApplePromotion;
        // when
        Receipt receipt = processor.process(basket, promotion);
        // then
        assertEquals("freeApplePromotion", receipt.getItems().get(0).getPromotionName());
    }

    @Test
    public void shouldHaveAsManyReceiptItemsAsItemsInBasket() throws Exception {
        // give
        Basket basket = new Basket();
        basket.addItem("Apple", 1);
        basket.addItem("Garlic", 1);
        basket.addItem("Orange", 1);
        // when
        Receipt receipt = processor.process(basket, null);
        // then
        assertEquals(3, receipt.getItems().size());
    }
}
