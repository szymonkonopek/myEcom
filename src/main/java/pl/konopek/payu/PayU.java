package pl.konopek.payu;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class PayU {
    private final PayUApiCredentials configuration;
    private final RestTemplate http;

    public PayU(PayUApiCredentials configuration, RestTemplate http) {
        this.configuration = configuration;
        this.http = http;
    }

    public OrderCreateResponse handle(OrderCreateRequest orderCreateRequest) {
        orderCreateRequest.setMerchantPosId(configuration.getPosId());
        orderCreateRequest.setNotifyUrl(configuration.getNotifyUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));
        HttpEntity<OrderCreateRequest> request = new HttpEntity<>(orderCreateRequest, headers);

        ResponseEntity<OrderCreateResponse> response = http.postForEntity(
                getUrl("/api/v2_1/orders"),
                request,
                OrderCreateResponse.class);

        return response.getBody();
    }

    private String getToken() {
        String body = String.format(
                "grant_type=client_credentials&client_id=%s&client_secret=%s",
                configuration.getClientId(),
                configuration.getClientSecret());

        String url = getUrl("/pl/standard/user/oauth/authorize");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        HttpEntity<AccessTokenResponse> response = http.postForEntity(
                url,
                request,
                AccessTokenResponse.class);

        return response.getBody().getAccessToken();
    }


    private String getUrl(String uri) {
        return String.format("%s%s", configuration.getBaseUrl(), uri);
    }
}
