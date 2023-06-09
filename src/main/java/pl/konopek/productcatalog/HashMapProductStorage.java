package pl.konopek.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    private Map<String, Product> products;

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
    public void add(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public Product loadById(String productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(Product::getOnline)
                .collect(Collectors.toList());
    }
}
