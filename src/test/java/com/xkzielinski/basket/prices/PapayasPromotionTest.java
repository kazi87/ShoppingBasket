package com.xkzielinski.basket.prices;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.xkzielinski.basket.model.Item;
import com.xkzielinski.basket.model.ReceiptItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO: Add description...
 */
public class PapayasPromotionTest {

    private final static BigDecimal papayaStandardPrice = BigDecimal.TEN;

    private Promotion testObj;
    private BigDecimal promotionPrice;

    @Before
    public void setUp() throws Exception {
        testObj = new PapayasPromotion(papayaStandardPrice);
        promotionPrice = papayaStandardPrice.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(3),
                new MathContext(2, RoundingMode.HALF_UP));
    }

    @Test
    public void shouldReturnSingleReceiptItem() throws Exception {
        // given
        Item papayaItem = new Item("papaya", papayaStandardPrice);
        // when
        List<ReceiptItem> result = testObj.calculate(papayaItem, 1);
        // then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldReturnSingleReceiptWithPromotionPrice() throws Exception {
        // given
        Item papayaItem = new Item("papaya", papayaStandardPrice);
        // when
        List<ReceiptItem> result = testObj.calculate(papayaItem, 3);
        // then
        assertEquals(1, result.size());
        assertEquals(promotionPrice, result.get(0).getPrice());
    }

    @Test
    public void shouldReturnSinglePromotionAndSingleStandardPrice() throws Exception {
        // given
        Item papayaItem = new Item("papaya", papayaStandardPrice);
        // when
        List<ReceiptItem> result = testObj.calculate(papayaItem, 4);
        // then
        assertEquals(2, result.size());
        assertEquals(papayaStandardPrice, result.get(0).getPrice());
        assertEquals(promotionPrice, result.get(1).getPrice());
    }
}
