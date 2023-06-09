package pl.konopek.payu;

public class PayUApiCredentials {
    private final String posId;
    private final String secondKey;
    private final String clientId;
    private final String clientSecret;
    private final String baseUrl;
    private final String notifyUrl;

    public PayUApiCredentials(String posId, String secondKey, String clientId, String clientSecret, String baseUrl) {
        this.posId = posId;
        this.secondKey = secondKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.notifyUrl = "http://127.0.0.1/notify/me";
    }

    public static PayUApiCredentials sandbox() {
        return new PayUApiCredentials(
                "300746",
                "b6ca15b0d1020e8094d9b5f8d163db54",
                "300746",
                "2ee86a66e5d97e3fadc400c9f19b065d",
                "https://secure.snd.payu.com");
    }

    public static PayUApiCredentials production(String posId, String secondKey, String clientId, String clientSecret) {
        return new PayUApiCredentials(
                posId,
                secondKey,
                clientId,
                clientSecret,
                "https://secure.payu.com");
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getPosId() {
        return posId;
    }

    public String getSecondKey() {
        return secondKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
