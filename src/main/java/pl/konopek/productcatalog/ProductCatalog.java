package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalog {
    // Business
    // Technical
    private ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String desc) {
        Product newOne =  new Product(
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
        Product product = loadById(productId);

        product.changePrice(newPrice);
    }

    public void assignImage(String productId, String imageKey) {
        Product product = loadById(productId);

        product.setImage(imageKey);
    }

    public void publishProduct(String productId) {
        Product product = loadById(productId);

        if (product.getImage() == null) {
            throw new ProductCantBePublishedException();
        }

        if (product.getPrice() == null) {
            throw new ProductCantBePublishedException();
        }

        product.setOnline(true);
    }

    public List<Product> allPublishedProducts() {
        return productStorage.allPublishedProducts();
    }
}
