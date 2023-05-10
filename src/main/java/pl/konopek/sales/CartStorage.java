package pl.konopek.sales;

import java.util.ArrayList;
import java.util.Optional;

public class CartStorage {
    private CartsStorage cartsStorage;
    private final ArrayList<ProductDetails> cartStorage;
    public CartStorage(){
        this.cartStorage = new ArrayList<>();
        this.cartsStorage = new CartsStorage();
    }
    public Optional<Cart> load(String customerId) {
        Cart customerCart = cartsStorage.loadCustomerCart(customerId).orElse(Cart.empty());
        return Optional.of(customerCart);
    }

    public void add(ProductDetails productDetails) {
        this.cartStorage.add(productDetails);
    }

    public static Cart empty() {
        return new Cart();
    }

    public void save(String customerId, Cart customersCart) {
        this.cartsStorage.save(customerId, customersCart);
    }

    public int itemsCount(){
        return this.cartStorage.size();
    }
}
