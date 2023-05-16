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

        ProductDetails productDetails = getProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customersCart.add(productDetails);

        cartStorage.save(customerId,customersCart);

    }

    private Optional<ProductDetails> getProductDetails(String productId) {
        return productDetailsProvider.loadCartForProduct(productId);
    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customer) {
        return new Offer();
    }
}
