package pl.konopek.sales;

import pl.konopek.sales.cart.Cart;
import pl.konopek.sales.cart.CartStorage;
import pl.konopek.sales.offering.Offer;
import pl.konopek.sales.product.NoSuchProductException;
import pl.konopek.sales.product.ProductDetails;
import pl.konopek.sales.product.ProductDetailsProvider;

import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
    }

    public void addToCart(String customerId, String productId) {
        Cart customersCart = loadForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = getProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customersCart.add(product);

        cartStorage.save(customerId, customersCart);
    }

    private Optional<ProductDetails> getProductDetails(String productId) {
        return productDetailsProvider.loadForProduct(productId);
    }

    private Optional<Cart> loadForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String currentCustomer) {
        return new Offer();
    }

//    public PaymentData acceptOffer(String customerId) {
//
//        Offer offer = getCurrentOffer(customerId);
//
//        Reservation reservation = Reservation.from(offer);
//
//        reservationStrage.save(reservation);
//
//
//    }
}
