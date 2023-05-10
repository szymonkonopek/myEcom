package pl.konopek.sales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class CartsStorage {
    private HashMap<String, Cart> cartsStorage;
    public CartsStorage(){
        this.cartsStorage = new HashMap<>();
    }

    public Optional<Cart> loadCustomerCart(String customerId){
        return Optional.ofNullable(cartsStorage.get(customerId));
    }

    public void save(String customerId, Cart customersCart) {
        this.cartsStorage.put(customerId, customersCart);
    }
}
