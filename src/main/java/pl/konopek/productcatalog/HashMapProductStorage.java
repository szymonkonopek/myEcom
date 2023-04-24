package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> productStorage;
    public HashMapProductStorage(){
        this.productStorage = new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return productStorage.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> loadAllPublishedProducts(){
        return productStorage.values().stream().filter(product -> (Boolean) product.getProductInfo().get("isPublished")).collect(Collectors.toList());
    }
    @Override
    public String addProduct(String name, String desc, String image, Boolean isPublished, BigDecimal price, String color, int x, int y) {
        Product newProduct = new Product(
                UUID.randomUUID(),
                name,
                desc,
                image,
                isPublished,
                price,
                color,
                x,
                y
        );
        productStorage.put(newProduct.getId(),newProduct);

        return newProduct.getId();
    }
    @Override
    public void changePriceById(BigDecimal price, String id){
        loadById(id).setPrice(price);
    }
    @Override
    public void changeImageById(String image, String id){
        loadById(id).setImage(image);
    }
    @Override
    public void changeVisibilityById(Boolean isPublished, String id){
        loadById(id).setIsPublished(isPublished);
    }

    @Override
    public Product loadById(String productId) {
        return productStorage.get(productId);
    }

}
