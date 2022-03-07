package com.pe.checkout.service.model;

public class Token {
public String access_token;

    public Token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
