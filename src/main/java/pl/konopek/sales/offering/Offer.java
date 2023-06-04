package pl.konopek.sales.offering;

import java.math.BigDecimal;

public class Offer {
    BigDecimal total;
    Integer itemsCount;

    public Offer() {
        this.total = BigDecimal.ZERO;
        itemsCount = 0;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }
}
