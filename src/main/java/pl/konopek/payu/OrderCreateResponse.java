package pl.konopek.payu;

public class OrderCreateResponse {
    private String redirectUri;
    private String orderId;
    private String extOrderId;
    /*
    {
   "status":{
      "statusCode":"SUCCESS",
   },
       "redirectUri":"{url_do_przekierowania_na_stronę_podsumowania_płatności}",
       "orderId":"WZHF5FFDRJ140731GUEST000P01",
       "extOrderId":"{twój_identyfikator_zamówienia}",
    }
     */

    public OrderCreateResponse() {}

    public String getRedirectUri() {
        return redirectUri;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public OrderCreateResponse setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }
}
