package pl.konopek.sales.offering;

import pl.konopek.sales.productdetails.ProductDetails;
import pl.konopek.sales.productdetails.ProductDetailsProvider;
import pl.konopek.sales.cart.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferCalculator {
    ProductDetailsProvider productDetailsProvider;

    public OfferCalculator(ProductDetailsProvider productDetailsProvider) {
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer calculateOffer(List<CartItem> cartItems) {
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());

        Offer offer = new Offer(offerItems, calculateTotal(offerItems));

        return offer;
    }

    private OfferLine createOrderLine(CartItem cartItem) {
        ProductDetails details = productDetailsProvider.load(cartItem.getProductId()).get();

        BigDecimal lineTotal = details.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        return new OfferLine(
                cartItem.getProductId(),
                details.getName(),
                details.getPrice(),
                cartItem.getQuantity(),
                lineTotal
            );
    }

    private BigDecimal calculateTotal(List<OfferLine> orderItems) {
        return orderItems.stream()
                .map(orderLine -> orderLine.getLineTotal())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Offer calculateOffer(List<CartItem> cartItems, TotalDiscountPolicy totalDiscount) {
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());

        Offer offer = new Offer(offerItems, calculateTotal(offerItems));

        offer = totalDiscount.apply(offer);

        return offer;
    }

    public Offer calculateOffer(List<CartItem> cartItems, TotalDiscountPolicy totalDiscount, EveryNItemLineDiscountPolicy lineDiscount) {
        List<OfferLine> offerItems = cartItems.stream()
                .map(this::createOrderLine)
                .map(lineDiscount::apply)
                .collect(Collectors.toList());

        Offer offer = new Offer(offerItems, calculateTotal(offerItems));

        offer = totalDiscount.apply(offer);

        return offer;
    }
}
