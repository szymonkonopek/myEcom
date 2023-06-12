package pl.konopek.sales.payment;

import pl.konopek.payu.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class PayuPaymentGateway implements PaymentGateway {
    private PayU payU;

    public PayuPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentData register(RegisterPaymentRequest request) {
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setDescription("RTV market")
                .setCurrencyCode("PLN")
                .setTotalAmount(request.getOffer().getTotal().multiply(BigDecimal.valueOf(100)).intValue())
                .setBuyer(new Buyer()
                        .setEmail(request.getClientData().getEmail())
                        .setFirstName(request.getClientData().getFirstname())
                        .setLastName(request.getClientData().getLastname())
                        .setLanguage("pl")
                )
                .setProducts(request.getOffer().getOrderLines()
                        .stream().map(ol -> new Product(ol.getName(), ol.getUnitPrice().multiply(BigDecimal.valueOf(100)).intValue(), ol.getQuantity()))
                        .collect(Collectors.toList())
                );

        OrderCreateResponse response = payU.handle(orderCreateRequest);

        return new PaymentData(response.getOrderId(), response.getRedirectUri());

    }
}
