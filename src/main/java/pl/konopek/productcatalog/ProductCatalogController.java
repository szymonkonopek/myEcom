package pl.konopek.productcatalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductCatalogController {
    private ProductCatalog productCatalog;
    ProductCatalogController(ProductCatalog productCatalog){
        this.productCatalog = productCatalog;
    }
    @GetMapping("/api/products")
    List<Product> allProducts(){
        return this.productCatalog.allProducts();
    }
}
