package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalog {
    //Biznes
    //Tech
    ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String desc) {
        Product newOne = new Product(
                UUID.randomUUID(),
                name,
                desc
        );

        productStorage.add(newOne);
        return newOne.getId();
    }

    public Product loadById(String productId) {
        return productStorage.loadById(productId);
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        Product loaded = productStorage.loadById(productId);
        loaded.changePrice(newPrice);
    }

    public void assignImage(String productId, String imageKey) {
        Product loaded = productStorage.loadById(productId);
        loaded.setImage(imageKey);
    }

    public List<Product> allPublishedProducts() {
        return productStorage.allPublishedProducts();
    }

    public void publishProduct(String productId) {
        Product loaded = loadById(productId);
        if (loaded.getPrice() == null) {
            throw new ProductCantBePublishedException();
        }

        if (loaded.getImageKey() == null) {
            throw new ProductCantBePublishedException();
        }

        loaded.setOnline();
    }
}
