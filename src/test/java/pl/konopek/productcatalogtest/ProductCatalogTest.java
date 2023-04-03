package pl.konopek.productcatalogtest;

import org.junit.jupiter.api.Test;
import pl.konopek.productcatalog.Product;
import pl.konopek.productcatalog.ProductCatalog;
import pl.konopek.productcatalog.HashMapProductStorage;
import pl.konopek.productcatalog.ProductImageOrPriceIsNotDefined;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {
    @Test
    void itAllowsToChangePrice() {
        //Arrange
        ProductCatalog productCatalog = new ProductCatalog();
        String id = productCatalog.addProduct("Pixel","Desc","image",false,BigDecimal.valueOf(1),"red",1,2);

        //Act
        productCatalog.changePriceById(BigDecimal.valueOf(10),id);

        //Assert
        assert productCatalog.loadById(id).getProductInfo().get("price").equals(BigDecimal.valueOf(10));
    }

    @Test
    void itAllowsToAssignImage() {
        ProductCatalog productCatalog = new ProductCatalog();
        String id = productCatalog.addProduct("Pixel","Desc","image",false,BigDecimal.valueOf(1),"red",1,2);

        productCatalog.changeImageById("other url",id);

        assert productCatalog.loadById(id).getProductInfo().get("image").equals("other url");
    }

    @Test
    void itAllowsToPublishProduct() {
        ProductCatalog productCatalog = new ProductCatalog();
        String id = productCatalog.addProduct("Pixel","Desc","image",false,BigDecimal.valueOf(1),"red",1,2);

        productCatalog.changeVisibilityById(true,id);

        assert (Boolean) productCatalog.loadById(id).getProductInfo().get("isPublished");
    }

    @Test
    void itAllowsToLoadProductDetails(){
        ProductCatalog productCatalog = new ProductCatalog();
        String id = productCatalog.addProduct("Pixel","Desc","image",false,BigDecimal.valueOf(1),"red",1,2);
        Product product = productCatalog.loadById(id);

    }

    @Test
    void publicationIsNotPossibleWhenPriceAndImageAreNotDefined() {
        //Arrange
        ProductCatalog productCatalog = new ProductCatalog();
        String id = productCatalog.addProduct("Pixel","Desc",null,false,null,"red",1,2);

        //Act and Assert
        assertThrows(ProductImageOrPriceIsNotDefined.class,() -> productCatalog.changeVisibilityById(true,id));
    }

    @Test
    void itExposesEmptyCatalog(){
        //??????
    }

    @Test
    void showPublishedProducts(){
        //Arrange
        ProductCatalog productCatalog = new ProductCatalog();
        String id1 = productCatalog.addProduct("Pixel","Desc","sss",false, BigDecimal.valueOf(10),"red",1,2);
        String id2 = productCatalog.addProduct("Pixel","Desc","123",false, BigDecimal.valueOf(10),"red",1,2);

        productCatalog.loadById(id1).setIsPublished(true);

        System.out.println(productCatalog.loadAllPublishedProducts());
    }
}
