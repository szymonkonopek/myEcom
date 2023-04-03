package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    List<Product> loadAllPublishedProducts();

    String addProduct(String name, String desc, String image, Boolean isPublished, BigDecimal price, String color, int x, int y);

    void changePriceById(BigDecimal price, String id);

    void changeImageById(String image, String id);

    void changeVisibilityById(Boolean isPublished, String id);

    Product loadById(String productId);
}
