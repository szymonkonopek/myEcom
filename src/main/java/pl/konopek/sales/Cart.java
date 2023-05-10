package pl.konopek.sales;

import java.util.List;

public class Cart {
    private final CartStorage cartStorage;
    public Cart(){
        this.cartStorage = new CartStorage();
    }
    public static Cart empty() {
        return CartStorage.empty();
    }

    public void add(ProductDetails productDetails) {
        this.cartStorage.add(productDetails);
    }

    public int itemsCount() {
        return this.cartStorage.itemsCount();
    }
}
