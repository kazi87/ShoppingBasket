package com.xkzielinski.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the summary of the shopping basket.
 * Provides information about items, prices and promotions.
 *
 */
public class Receipt {

    /**
     * initial total price = 0
     */
    private BigDecimal totalPrice = BigDecimal.ZERO;

    /**
     * List of the ReceiptItem
     */
    private List<ReceiptItem> items;

    public Receipt() {
        items = new LinkedList<ReceiptItem>();
    }

    /**
     * Adds a new item to the list and updates the total price
     * @param item
     */
    public void addItem(ReceiptItem item) {
        items.add(item);
        BigDecimal totalItemPrice = item.getTotalPrice();
        totalPrice = totalPrice.add(totalItemPrice);
    }

    /**
     * @return total price of the Basket
     */
    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2, RoundingMode.CEILING);
    }

    /**
     * @return items including promotion price
     */
    public List<ReceiptItem> getItems() {
        return items;
    }

    /**
     * Prints the summary to the given out
     * @throws IOException
     */
    public void print(OutputStream out) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(out);
        writer.write("-------------------------------------------------------------------------------\n");
        writer.write(String.format("%-5s %-15s %-10s %-20s %-10s %-10s", "Idx", "Name", "Price", "Promotion",
                "Quantity", "TotalPrice"));
        writer.write("\n------------------------------------------------------------------------------\n\n");
        writer.flush();
        int idx = 1;
        for (ReceiptItem ri : items) {
            Item item = ri.getItem();
            String price = item.getPrice().toString() + " " + item.getCurrency();
            String promotion = (ri.getPromotionName() == null) ? "-" : ri.getPromotionName();
            String totalPrice = ri.getTotalPrice() + " " + item.getCurrency();
            writer.write(String.format("%-5d %-15s %-10s %-20s %-10s %-10s", idx++, item.getName(), price, promotion,
                    ri.getQuantity(), totalPrice));
            writer.write("\n");
        }
        writer.write("------------------------------------------------------------------------------\n");
        writer.write(String.format("%65s %5s", "Total:", getTotalPrice() + " " + Item.DEFAULT_CURRENCY));
        writer.flush();
    }
}
