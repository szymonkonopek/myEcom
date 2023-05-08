package pl.konopek.sales;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CollectingProducts {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;

    @BeforeEach
    void setup(){
        cartStorage = new CartStorage();
        productDetailsProvider = new ProductDetailsProvider();
    }

    @Test
    void idAllowsToCollectProductsToCart(){
        //Arange
        Sales sales = thereIsSalesModule();
        String productId = thereIsProduct();
        String customer = thereIsCustomer("Szymon");

        //Act
        sales.addToCart(customer, productId);

        //Assert
        assertThereIsNProductsInCustomerCart(customer, 1);

    }

    private void assertThereIsNProductsInCustomerCart(String customerId, int productCount) {
        Cart customerCart = cartStorage.load(customerId).get();
        assert customerCart.itemsCount() == productsCount;

    }

    private String thereIsProduct() {
        return UUID.randomUUID().toString();
    }

    private String thereIsCustomer(String customerID) {
        return customerID;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, new ProductDetailsProvider());
    }
}
