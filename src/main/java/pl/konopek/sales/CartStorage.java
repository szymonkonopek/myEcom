package pl.konopek.sales;

import java.util.Optional;

public class CartStorage {
    public Optional<Cart> load(String customerId) {
        return Optional.empty();
    }

    public void save(String customerId, Cart customersCart) {
    }
}
