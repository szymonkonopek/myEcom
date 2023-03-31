package pl.konopek.productcatalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class ProductCatalog {
    private ArrayList<Product> products;
    public ProductCatalog() {
        this.products = loadDatabase();
    }
    public ArrayList<Product> allProducts() {
        return products;
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
        products.add(newProduct);

        return newProduct.getId();
    }

    //ERP -> change price
    public void changePriceById(BigDecimal price, String id){
        loadById(id).setPrice(price);
    }

    //ERP -> change image
    public void changeImageById(String image, String id){
        loadById(id).setImage(image);
    }

    //ERP -> Publish product
    public void changeVisibilityById(Boolean isPublished, String id){
        loadById(id).setIsPublished(isPublished);
    }

    public Product loadById(String productId) {
        ArrayList <Product> allProducts = allProducts();
        for (Product product : allProducts){
            if (product.getId().equals(productId)){
                return product;
            }
        }
        return null;
    }
}