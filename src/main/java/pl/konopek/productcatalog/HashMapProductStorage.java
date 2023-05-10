package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> productStorage;
    public HashMapProductStorage(){
        this.productStorage = arrayListToHashMap(loadDatabase());
        //this.productStorage = new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return productStorage.values()
                .stream()
                .collect(Collectors.toList());
    }
    @Override
    public ArrayList<Product> loadDatabase(){
        ArrayList<Product> database = new ArrayList<Product>();
        database.add(new Product(UUID.fromString("2c4257c0-3549-4269-b9dd-526df1693260"),"Name 1", "desc", "image",false,  BigDecimal.valueOf(1), "red", 1, 1));
        database.add(new Product(UUID.randomUUID(),"Name 2", "desc", "image",false, BigDecimal.valueOf(1), "red", 2, 1));
        database.add(new Product(UUID.randomUUID(),"Name 3", "desc", "image", false, BigDecimal.valueOf(1), "red", 3, 1));
        return database;
    }
    public HashMap<String,Product> arrayListToHashMap(ArrayList<Product> arrayList){
        HashMap<String,Product> map = new HashMap<>();
        for (Product product : arrayList) {
            map.put(product.getId(),product);
        }
        return map;
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
