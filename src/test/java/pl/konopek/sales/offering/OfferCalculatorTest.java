package pl.konopek.sales.offering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.konopek.sales.productdetails.InMemoryProductDetailsProvider;
import pl.konopek.sales.productdetails.ProductDetails;
import pl.konopek.sales.cart.CartItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferCalculatorTest {

    private InMemoryProductDetailsProvider productDetails;

    @BeforeEach
    void setUp() {
        this.productDetails = new InMemoryProductDetailsProvider();
    }

    @Test
    public void itCreateOfferBasedOnCartItems() {
        String product1 = thereIsProduct("Lego set 1", BigDecimal.valueOf(10.10));
        String product2 = thereIsProduct("Lego set 2", BigDecimal.valueOf(20.10));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, 2),
                new CartItem(product2, 1)
        );

        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems);

        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(40.3));
    }

    @Test
    public void itAllowsToApplyTotalDiscount() {
        String product1 = thereIsProduct("Lego set 1", BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, 1)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        TotalDiscountPolicy totalDiscount = thereIsTotalDiscount(BigDecimal.valueOf(50), BigDecimal.valueOf(10));

        Offer offer = offerCalculator.calculateOffer(cartItems, totalDiscount);

        assertThat(offer.getTotal())
                .isEqualTo(BigDecimal.valueOf(90));
    }

    @ParameterizedTest
    @MethodSource("getTotalDiscounts")
    public void itAllowsToApplyTotalDiscount(TotalDiscountPolicy policy, BigDecimal result) {
        String product1 = thereIsProduct("Lego set 1", BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, 1)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems, policy);

        assertThat(offer.getTotal())
                .isEqualTo(result);
    }

    public static Stream<Arguments> getTotalDiscounts() {
        return Stream.of(
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(50), BigDecimal.valueOf(10)), BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(100), BigDecimal.valueOf(10)), BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(99), BigDecimal.valueOf(10)), BigDecimal.valueOf(90)),
                Arguments.of(thereIsTotalDiscount(BigDecimal.valueOf(101), BigDecimal.valueOf(10)), BigDecimal.valueOf(100))
        );
    }

    @Test
    public void itAllowsToApplyDiscountPerLine() {
        String product1 = thereIsProduct("Lego set 1", BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, 5)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();
        EveryNItemLineDiscountPolicy lineDiscount = thereIsPerLineDiscount(5);

        Offer offer = offerCalculator.calculateOffer(cartItems, TotalDiscountPolicy.noDiscount(), lineDiscount);

        assertThat(offer.getTotal())
                .isEqualByComparingTo(BigDecimal.valueOf(400));
    }

    @ParameterizedTest
    @MethodSource("getLinesDiscounts")
    public void itAllowsToApplyDiscountPerLine(EveryNItemLineDiscountPolicy policy, Integer itemsCount, BigDecimal result) {
        String product1 = thereIsProduct("Lego set 1", BigDecimal.valueOf(100));
        List<CartItem> cartItems = Arrays.asList(
                new CartItem(product1, itemsCount)
        );
        OfferCalculator offerCalculator = thereIsOfferCalculator();

        Offer offer = offerCalculator.calculateOffer(cartItems, TotalDiscountPolicy.noDiscount(), policy);

        assertThat(offer.getTotal())
                .isEqualByComparingTo(result);
    }

    public static Stream<Arguments> getLinesDiscounts() {
        return Stream.of(
                Arguments.of(thereIsPerLineDiscount(5), 5, BigDecimal.valueOf(400)),
                Arguments.of(thereIsPerLineDiscount(5), 6, BigDecimal.valueOf(500)),
                Arguments.of(thereIsPerLineDiscount(5), 4, BigDecimal.valueOf(400))
        );
    }

    private static EveryNItemLineDiscountPolicy thereIsPerLineDiscount(int quantityThreshold) {
        return new EveryNItemLineDiscountPolicy(quantityThreshold);
    }

    private static TotalDiscountPolicy thereIsTotalDiscount(BigDecimal total, BigDecimal discountValue) {
        return new TotalDiscountPolicy(total, discountValue);
    }

    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        this.productDetails.add(new ProductDetails(id, name, price));
        return id;
    }

    private OfferCalculator thereIsOfferCalculator() {
        return new OfferCalculator(productDetails);
    }
}
