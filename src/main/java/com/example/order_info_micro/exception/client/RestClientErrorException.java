package com.example.order_info_micro.exception.client;

public class RestClientErrorException extends RuntimeException {
    public RestClientErrorException() {
    }

    public RestClientErrorException(String message) {
        super(message);
    }
}
