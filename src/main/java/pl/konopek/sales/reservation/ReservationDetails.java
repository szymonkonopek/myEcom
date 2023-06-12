package pl.konopek.sales.reservation;

public class ReservationDetails {
    String reservationId;
    String paymentUrl;

    public ReservationDetails(String reservationId, String paymentUrl) {

        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
