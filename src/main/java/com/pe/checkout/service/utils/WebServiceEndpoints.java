package com.pe.checkout.service.utils;

public enum WebServiceEndpoints {
    TOKEN_RESOURCE("/oauth2/v2.0/token"),
    PAY_RESOURCE("/yap-pub-che/public/yape/v1/checkout/validate");

    private final String url;

    WebServiceEndpoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
