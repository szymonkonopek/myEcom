package pl.konopek.sales.reservation;

import pl.konopek.sales.offering.Offer;
import pl.konopek.sales.payment.PaymentData;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Reservation {

    private String id;
    private ClientData clientData;
    private BigDecimal total;

    private String paymentId;
    private String paymentUrl;

    private Instant paidAt;

    private Instant createdAt;

    public static Reservation of(OfferAcceptanceRequest request, Offer offer, PaymentData payment) {
        Reservation reservation = new Reservation();
        reservation.id = UUID.randomUUID().toString();
        reservation.clientData = new ClientData(request.getFirstname(), request.getLastname(), request.getEmail());

        reservation.total = offer.getTotal();

        reservation.paymentId = payment.getId();
        reservation.paymentUrl = payment.getUrl();

        reservation.createdAt = Instant.now();

        return reservation;
    }

    public String getId() {
        return id;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public ClientData getClientData() {
        return clientData;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean isPending() {
        return paidAt == null;
    }
}
