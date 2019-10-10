package com.tavisca.springapplication.exception;

public class RequestUserNotFoundException extends Exception {
    public RequestUserNotFoundException(String message) {
        super(message);
    }
}
