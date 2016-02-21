package com.xkzielinski.basket;

import java.io.IOException;
import java.math.BigDecimal;

import com.xkzielinski.basket.model.Basket;
import com.xkzielinski.basket.model.Receipt;
import com.xkzielinski.basket.prices.PapayasPromotion;
import com.xkzielinski.basket.prices.PriceCalculationProcessor;
import com.xkzielinski.basket.registry.ItemRegistryInMemory;

/**
 * Main application class.
 * Process a basket parsed from the program parameters in the format: itemName1 quantity1 itemName2 quantity2...
 * Displays summary  of the basket including "Papaya" promotion.
 */
public class ShoppingBasketMain {

    public static void main(String[] s) {
        try {
            ItemRegistryInMemory itemRegistry = new ItemRegistryInMemory();
            PriceCalculationProcessor priceCalculationProcessor = new PriceCalculationProcessor(itemRegistry);
            Basket basket = parseInputStream(s);
            if (basket != null) {
                BigDecimal papayaPrice = itemRegistry.getItemByName("papaya").getPrice();
                PapayasPromotion papayasPromotion = new PapayasPromotion(papayaPrice);
                Receipt receipt = priceCalculationProcessor.process(basket, papayasPromotion);
                receipt.print(System.out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Basket parseInputStream(String[] s) {
        Basket basket;
        if (s.length > 0) {
            if ((s.length % 2) != 0) {
                System.out.println(
                    "Invalid input. Please define basket in format: itemName quantity, itemName quantity");
                return null;
            }
            basket = new Basket();
            for (int i = 0; i < s.length; i += 2) {
                String name = s[i];
                Integer quant = Integer.valueOf(s[i + 1]);
                basket.addItem(name, quant);
            }
            return basket;
        }

        return null;
    }
}
