package pl.konopek.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.konopek.productcatalog.Product;
import pl.konopek.productcatalog.ProductCatalog;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferAcceptanceHTTPTest {

    @Autowired
    ProductCatalog productCatalog;

    @Autowired
    TestRestTemplate http;

    @Test
    void itAllowsToAcceptOffer() {
        //Arrange
        //thereAreProducts
        String productId = thereIsExampleProduct();
        //customerAddedProductToCart
        http.postForEntity(String.format("/api/add-to-cart/%s", productId), null, String.class);
        http.postForEntity(String.format("/api/add-to-cart/%s", productId), null, String.class);

        //Act
        AcceptOffer acceptOffer = new AcceptOffer("Jakub", "jakub@example.com");
        ResponseEntity<PaymentData> response = http.postForEntity(String.format("/api/accept-offer"), acceptOffer, PaymentData.class);

        //Assert
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody().getPaymentUrl());
    }

    private String thereIsExampleProduct() {
        return productCatalog.allPublishedProducts().stream()
                .findFirst()
                .map(Product::getId)
                .get();
    }
}
