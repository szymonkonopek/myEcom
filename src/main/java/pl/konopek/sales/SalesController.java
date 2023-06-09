package pl.konopek.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import pl.konopek.sales.offering.Offer;

@RestController
public class SalesController {

    private Sales sales;

    public SalesController(Sales sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return sales.getCurrentOffer(getCurrentCustomer());
    }

    @PostMapping("/api/cart/{productId}")
    public void addToCart(@PathVariable String productId) {
        sales.addToCart(getCurrentCustomer(), productId);
    }


    @PostMapping("/api/accept-offer")
    public void acceptOffer() {
        sales.acceptOffer();
    }
    private String getCurrentCustomer() {
        return "Kuba";
    }
}
