package pl.konopek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.konopek.productcatalog.HashMapProductStorage;
import pl.konopek.productcatalog.ProductCatalog;

import java.math.BigDecimal;

@SpringBootApplication

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Bean
    ProductCatalog createProductCatalog(){
         ProductCatalog productCatalog = new ProductCatalog();
         productCatalog.addProduct("item","item","lo",true, BigDecimal.valueOf(123),"red",2,2);
         return productCatalog;
    }
}
