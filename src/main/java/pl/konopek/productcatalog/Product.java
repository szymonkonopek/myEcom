package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Product {
    private final String uuid;
    private final String name;
    private final String desc;
    private String image;
    private Boolean isPublished;
    private BigDecimal price;
    private final String color;
    private final int x;
    private final int y;

    public Product(UUID uuid, String name, String desc, String image, Boolean isPublished, BigDecimal price, String color, int x, int y) {
        this.uuid = uuid.toString();
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.isPublished = isPublished;
        this.price = price;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return this.uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(this.uuid);
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setIsPublished(Boolean isPublished){
        if (this.image == null || this.price == null){
            throw new ProductImageOrPriceIsNotDefined();
        }
        this.isPublished = isPublished;
    }

    //JSON type sh*t
    public Map<String,Object> getProductInfo(){
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("uuid", this.uuid);
        productInfo.put("name", this.name);
        productInfo.put("desc", this.desc);
        productInfo.put("image", this.image);
        productInfo.put("isPublished", this.isPublished);
        productInfo.put("price", this.price);
        productInfo.put("color", this.color);
        productInfo.put("x", this.x);
        productInfo.put("y", this.y);

        return productInfo;
    }
}