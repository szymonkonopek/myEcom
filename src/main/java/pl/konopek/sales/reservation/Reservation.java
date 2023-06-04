package pl.konopek.sales.reservation;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Reservation {
    @Id
    String id;
    BigDecimal total;
    String paymentId;

    Reservation() {}

    public Reservation(String id, BigDecimal total, String paymentId) {
        this.id = id;
        this.total = total;
        this.paymentId = paymentId;
    }
}
