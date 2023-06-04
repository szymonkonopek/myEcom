package pl.konopek.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> products;

    public HashMapProductStorage() {
        this.products = new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return products.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void add(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product loadById(String productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(Product::isOnline)
                .collect(Collectors.toList());
    }
}
