package pl.konopek.sales;

import pl.konopek.productcatalog.Product;
import pl.konopek.productcatalog.ProductCatalog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductDetailsProvider {
    public Optional<ProductDetails> loadCartForProduct(String productId){
        ProductCatalog productCatalog = new ProductCatalog();
        ProductDetails productDetails = new ProductDetails(productCatalog.loadById(productId).getProductInfo());
        return Optional.of(productDetails);
    }
}
