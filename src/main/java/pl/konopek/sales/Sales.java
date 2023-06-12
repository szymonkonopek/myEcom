package pl.konopek.sales;

import pl.konopek.sales.cart.Cart;
import pl.konopek.sales.cart.CartStorage;
import pl.konopek.sales.offering.EveryNItemLineDiscountPolicy;
import pl.konopek.sales.offering.Offer;
import pl.konopek.sales.offering.OfferCalculator;
import pl.konopek.sales.offering.TotalDiscountPolicy;
import pl.konopek.sales.payment.PaymentData;
import pl.konopek.sales.payment.PaymentGateway;
import pl.konopek.sales.payment.RegisterPaymentRequest;
import pl.konopek.sales.productdetails.NoSuchProductException;
import pl.konopek.sales.productdetails.ProductDetails;
import pl.konopek.sales.productdetails.ProductDetailsProvider;
import pl.konopek.sales.reservation.OfferAcceptanceRequest;
import pl.konopek.sales.reservation.Reservation;
import pl.konopek.sales.reservation.ReservationDetails;
import pl.konopek.sales.reservation.InMemoryReservationStorage;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private InMemoryReservationStorage reservationStorage;

    public Sales(
            CartStorage cartStorage,
            ProductDetailsProvider productDetails,
            OfferCalculator offerCalculator,
            PaymentGateway paymentGateway,
            InMemoryReservationStorage reservationStorage
        ) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetails;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationStorage = reservationStorage;
    }

    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product.getId());

        cartStorage.addForCustomer(customerId, customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500), BigDecimal.valueOf(50)),
                new EveryNItemLineDiscountPolicy(5)
        );

        return offer;
    }

    public ReservationDetails acceptOffer(String customerId, OfferAcceptanceRequest request) {
        Offer offer = this.getCurrentOffer(customerId);

        PaymentData payment = paymentGateway.register(RegisterPaymentRequest.of(request, offer));

        Reservation reservation = Reservation.of(request, offer, payment);

        reservationStorage.save(reservation);

        return new ReservationDetails(reservation.getId(), reservation.getPaymentUrl());
    }
}
