package pl.konopek.sales;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
public class Reservation {
    public Reservation(String id, BigDecimal total, String paymentId) {
        this.id = id;
        this.total = total;
        this.paymentId = paymentId;
    }

    public Reservation(){

    }

    @Id
    String id;
    BigDecimal total;
    String paymentId;
}
