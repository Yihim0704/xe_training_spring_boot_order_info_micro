package com.example.order_info_micro.exception.client;

public class MagicWandCatalogueNotValidException extends RuntimeException {

    private String message;
    private String httpStatus;

    public MagicWandCatalogueNotValidException() {
    }

    public MagicWandCatalogueNotValidException(String message, String httpStatus) {
        setMessage(message);
        setHttpStatus(httpStatus);
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
