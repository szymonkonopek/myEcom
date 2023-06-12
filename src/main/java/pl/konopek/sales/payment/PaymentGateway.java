package pl.konopek.sales.payment;

public interface PaymentGateway {
    PaymentData register(RegisterPaymentRequest request);
}
