package pl.konopek.sales.offering;

import java.math.BigDecimal;

public class OfferLine {
    private final String productId;
    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final BigDecimal lineTotal;

    public OfferLine(String productId, String name, BigDecimal price, int quantity, BigDecimal lineTotal) {

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return price;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }
}
