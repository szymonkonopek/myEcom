package pl.konopek.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OfferAcceptanceTest {
    @Test
    void itAllowsToAcceptOffer() {
        Sales sales = thereIsSalesModule();
        String productId = thereIsExampleProduct();
        String customerId = thereIsCustomer();

        sales.addToCart(customerId, productId);
        sales.addToCart(customerId, productId);

        //Act
        PaymentData paymentData = sales.acceptOffer(customerId);

        //arrange
        assertCustomerHasNReservation(customerId, 1);
        assertNotNull(paymentData.getPaymentUrl());
    }
}
