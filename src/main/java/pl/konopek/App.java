package pl.konopek;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.konopek.payu.PayU;
import pl.konopek.payu.PayUApiCredentials;
import pl.konopek.productcatalog.HashMapProductStorage;
import pl.konopek.productcatalog.ProductCatalog;
import pl.konopek.sales.Sales;
import pl.konopek.sales.cart.CartStorage;
import pl.konopek.sales.offering.OfferCalculator;
import pl.konopek.sales.payment.PaymentGateway;
import pl.konopek.sales.payment.PayuPaymentGateway;
import pl.konopek.sales.productdetails.ProductCatalogProductDetailsProvider;
import pl.konopek.sales.productdetails.ProductDetailsProvider;
import pl.konopek.sales.reservation.InMemoryReservationStorage;
import pl.konopek.web.SessionCurrentCustomerContext;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createNewProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new HashMapProductStorage());

        String productId1 = productCatalog.addProduct("Falcon Millennium", "/lego");
        productCatalog.assignImage(productId1, "/image/lego_set.png");
        productCatalog.changePrice(productId1, BigDecimal.valueOf(4499));
        productCatalog.publishProduct(productId1);

        String productId2 = productCatalog.addProduct("Orange", "/fruits");
        productCatalog.assignImage(productId2, "/image/orange.jpg");
        productCatalog.changePrice(productId2, BigDecimal.valueOf(49));
        productCatalog.publishProduct(productId2);

        String productId3 = productCatalog.addProduct("Paper clip", "/paper-clip");
        productCatalog.assignImage(productId3, "/image/paperclips.jpg");
        productCatalog.changePrice(productId3, BigDecimal.valueOf(99));
        productCatalog.publishProduct(productId3);

        return productCatalog;
    }

    @Bean
    PaymentGateway createPaymentGateway() {
        return new PayuPaymentGateway(new PayU(PayUApiCredentials.sandbox(), new RestTemplate()));
    }

    @Bean
    Sales createSales(ProductDetailsProvider productDetailsProvider, PaymentGateway paymentGateway) {
        return new Sales(
                new CartStorage(),
                productDetailsProvider,
                new OfferCalculator(productDetailsProvider),
                paymentGateway,
                new InMemoryReservationStorage()
        );
    }

    @Bean
    SessionCurrentCustomerContext currentCustomerContext(HttpSession httpSession) {
        return new SessionCurrentCustomerContext(httpSession);
    }

    @Bean
    ProductDetailsProvider createProductDetailsProvider(ProductCatalog catalog) {
        return new ProductCatalogProductDetailsProvider(catalog);
    }
}
