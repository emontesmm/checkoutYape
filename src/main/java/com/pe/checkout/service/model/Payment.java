package com.pe.checkout.service.model;

public class Payment {
    private String code;
    private String description;
    private String errorType;

    public Payment(String code, String description, String errorType) {
        this.code = code;
        this.description = description;
        this.errorType = errorType;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorType() {
        return errorType;
    }
}
