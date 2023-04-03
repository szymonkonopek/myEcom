package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    HashMapProductStorage productStorage;
    public ProductCatalog() {
        this.productStorage = new HashMapProductStorage();
    }
    public List<Product> allProducts() {
        return productStorage.allProducts();
    }
    public List<Product> loadAllPublishedProducts() {
        return productStorage.loadAllPublishedProducts();
    }

        //Fake loading from db
    public ArrayList<Product> loadDatabase(){
        ArrayList<Product> database = new ArrayList<Product>();
        database.add(new Product(UUID.randomUUID(),"Name 1", "desc", "image",false,  BigDecimal.valueOf(1), "red", 1, 1));
        database.add(new Product(UUID.randomUUID(),"Name 2", "desc", "image",false, BigDecimal.valueOf(1), "red", 2, 1));
        database.add(new Product(UUID.randomUUID(),"Name 3", "desc", "image", false, BigDecimal.valueOf(1), "red", 3, 1));
        return database;
    }

    //Seller -> Add product
    public String addProduct(String name, String desc, String image, Boolean isPublished, BigDecimal price, String color, int x, int y) {
        return productStorage.addProduct(name,desc,image,isPublished,price,color,x,y);
    }

    //ERP -> change price
    public void changePriceById(BigDecimal price, String id){
        productStorage.changePriceById(price, id);
    }

    //ERP -> change image
    public void changeImageById(String image, String id){
        productStorage.changeImageById(image,id);
    }

    //ERP -> Publish product
    public void changeVisibilityById(Boolean isPublished, String id){
        productStorage.changeVisibilityById(isPublished,id);
    }

    public Product loadById(String productId) {
        return productStorage.loadById(productId);
    }
}