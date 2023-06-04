package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String uuid;
    private final String name;
    private final String desc;
    private BigDecimal price;
    private String imageKey;
    private boolean online;

    public Product(UUID uuid, String name, String desc) {
        this.uuid = uuid.toString();
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return uuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal newPrice) {

        price = newPrice;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImage(String imageKey) {

        this.imageKey = imageKey;
    }

    public void setOnline() {
        this.online = true;
    }

    public boolean isOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
