package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ListProductStorage implements ProductStorage {
    List<Product> productStorage;
    public ListProductStorage(){
        this.productStorage = new ArrayList();
    }

    @Override
    public List<Product> allProducts() {
       return new ArrayList<>(productStorage);
    }

    @Override
    public List<Product> loadAllPublishedProducts(){
        return productStorage.stream().filter(product -> (Boolean) product.getProductInfo().get("isPublished")).collect(Collectors.toList());
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
        productStorage.add(newProduct);

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
        return productStorage.stream().filter(product -> product.getProductInfo().get("uuid") == productId).collect(Collectors.toList()).get(0);
    }
}
