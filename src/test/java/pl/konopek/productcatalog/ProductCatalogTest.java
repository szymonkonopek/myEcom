package pl.konopek.productcatalog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    @Test
    void itAllowsToListMyProducts() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        List<Product> products = catalog.allProducts();
        //Assert
        assertListIsEmpty(products);
    }

    @Test
    void itAllowsToAddProduct() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.addProduct("lego set 8083", "nice one");

        //Assert
        List<Product> products = catalog.allProducts();
        assert 1 == products.size();
    }

    @Test
    void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("lego set 8083", "nice one");

        Product loadedProduct = catalog.loadById(productId);
        assert loadedProduct.getId().equals(productId);
        assert loadedProduct.getName().equals("lego set 8083");
    }

    @Test
    void itAllowsToChangePrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        Product loadedProduct = catalog.loadById(productId);
        assertEquals(BigDecimal.valueOf(20.20), loadedProduct.getPrice());
    }

    @Test
    void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        catalog.assignImage(productId, "foo/boo/nice_image.jpeg");

        Product loadedProduct = catalog.loadById(productId);
        assertEquals("foo/boo/nice_image.jpeg", loadedProduct.getImage());
    }

    @Test
    void itAllowsToPublishProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");
        catalog.changePrice(productId, BigDecimal.valueOf(10));
        catalog.assignImage(productId, "nice.jpeg");

        catalog.publishProduct(productId);

        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertDoesNotThrow(() -> catalog.publishProduct(productId));
        assertEquals(1, publishedProducts.size());
    }

    @Test
    void draftProductsAreNotListedForBeingSold() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertEquals(0, publishedProducts.size());
    }

    @Test
    void publicationIsPossibleWhenPriceAndImageAreDefined() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego set 8083", "nice one");

        assertThrows(
                ProductCantBePublishedException.class,
                () -> catalog.publishProduct(productId)
        );


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
