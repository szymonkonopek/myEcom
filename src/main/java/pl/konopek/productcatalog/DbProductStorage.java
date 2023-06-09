package pl.konopek.productcatalog;

import java.util.List;

public class DbProductStorage implements ProductStorage {
    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public Product loadById(String productId) {
        return null;
    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }
}
