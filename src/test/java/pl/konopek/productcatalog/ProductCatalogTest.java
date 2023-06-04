package pl.konopek.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    @Test
    void itExposeEmptyCollectionOfProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        List<Product> products = catalog.allProducts();
        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertListIsEmpty(products);
        assertListIsEmpty(publishedProducts);
    }

    @Test
    void itAllowsToAddProduct() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.addProduct("lego set 8080", "nice one");
        //Assert
        List<Product> products = catalog.allProducts();
        assert 1 == products.size();
    }

    @Test
    void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");

        Product loaded = catalog.loadById(productId);

        assert loaded.getId().equals(productId);
    }
    @Test
    void itAllowsToChangePrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        Product loaded = catalog.loadById(productId);
        assertEquals(BigDecimal.valueOf(20.20), loaded.getPrice());
    }

    @Test
    void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");

        catalog.assignImage(productId, "some/nice.jpeg");

        Product loaded = catalog.loadById(productId);
        assertEquals("some/nice.jpeg", loaded.getImageKey());
    }

    @Test
    void itDenyPublicationWithoutImageAndPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");

        assertThrows(
                ProductCantBePublishedException.class,
                () -> catalog.publishProduct(productId)
        );
    }

    @Test
    void itAllowsToPublishProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");
        catalog.changePrice(productId, BigDecimal.valueOf(10));
        catalog.assignImage(productId, "nice.jpeg");

        catalog.publishProduct(productId);

        assertEquals(1, catalog.allPublishedProducts().size());
    }

    @Test
    void itDoesNotShowDraftProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8080", "nice one");

        assertEquals(0, catalog.allPublishedProducts().size());
    }


    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new HashMapProductStorage()
        );
    }

    private void assertListIsEmpty(List<Product> products) {
        assert 0 == products.size();
    }
}
