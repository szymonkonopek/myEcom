package pl.konopek.sales.product;

import pl.konopek.productcatalog.Product;
import pl.konopek.productcatalog.ProductCatalog;

import java.util.Optional;

public class ProductCatalogProductDetailsProvider implements ProductDetailsProvider {

    ProductCatalog productCatalog;

    public ProductCatalogProductDetailsProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public Optional<ProductDetails> loadForProduct(String productId) {
        Product product = productCatalog.loadById(productId);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(new ProductDetails(
                product.getId(),
                product.getName(),
                product.getPrice()));
    }
}
