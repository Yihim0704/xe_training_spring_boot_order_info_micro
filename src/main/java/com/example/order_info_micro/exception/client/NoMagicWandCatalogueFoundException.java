package com.example.order_info_micro.exception.client;

public class NoMagicWandCatalogueFoundException extends RuntimeException {
    private String message;
    private String httpStatus;

    public NoMagicWandCatalogueFoundException() {
    }

    public NoMagicWandCatalogueFoundException(String message, String httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
