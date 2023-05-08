package pl.konopek.sales;

import pl.konopek.productcatalog.Product;

import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private  ProductDetailsProvider productDetailsProvider;
    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider){
        this.productDetailsProvider = productDetailsProvider;
        this.cartStorage = cartStorage;
    }
    public void addToCart(String customerId, String productId) {
        Cart customersCart = loadForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = getProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customersCart.add(product);

        cartStorage.save(customerId,customersCart);

    }

    private Optional<ProductDetails> getProductDetails(String productId) {
        return null;
    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }
}
