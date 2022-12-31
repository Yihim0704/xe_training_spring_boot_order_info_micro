package com.example.order_info_micro.exception.client;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
        super(message);
    }
}
