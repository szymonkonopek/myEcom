package pl.konopek.sales.payment;

public class PaymentData {
    private final String id;
    private final String url;

    public PaymentData(String id, String url) {

        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
