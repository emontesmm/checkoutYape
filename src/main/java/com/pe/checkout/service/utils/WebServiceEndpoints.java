package com.pe.checkout.service.utils;

public enum WebServiceEndpoints {
    TOKEN_RESOURCE("/oauth2/v2.0/token"),
    PAY_RESOURCE("/yap-che/channel/yape/v1/checkout/pay");

    private final String url;

    WebServiceEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
