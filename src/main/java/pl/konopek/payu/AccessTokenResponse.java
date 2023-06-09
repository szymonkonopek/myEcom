package pl.konopek.payu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("grant_type")
    private String grantType;
    /*
    {
    "access_token":"8f79c971-195e-43f5-bd83-ad2104414acc",
    "token_type":"bearer",
    "expires_in":43199, //czas ważności w sekundach
    "grant_type":"client_credentials"
}
     */
    public AccessTokenResponse() {}

    public String getAccessToken() {
        return accessToken;
    }

    public AccessTokenResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public AccessTokenResponse setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public AccessTokenResponse setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getGrantType() {
        return grantType;
    }

    public AccessTokenResponse setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }
}
