package pl.konopek.sales.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpyPaymentGateway implements PaymentGateway {
    List<RegisterPaymentRequest> requests;

    public SpyPaymentGateway() {
        this.requests = new ArrayList<>();
    }

    @Override
    public PaymentData register(RegisterPaymentRequest request) {
        requests.add(request);
        String id = UUID.randomUUID().toString();
        return new PaymentData(id, String.format("https://spypayment.com/%s", id));
    }

    public int getRequestsCount() {
        return requests.size();
    }
}
