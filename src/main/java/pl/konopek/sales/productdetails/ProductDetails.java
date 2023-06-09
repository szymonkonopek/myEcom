package pl.konopek.sales.productdetails;

import java.math.BigDecimal;

public class ProductDetails {
    private final String id;
    private final String name;
    private final BigDecimal price;

    public ProductDetails(String id, String name, BigDecimal price) {

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
