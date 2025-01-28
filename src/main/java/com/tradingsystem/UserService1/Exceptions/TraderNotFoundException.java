package com.tradingsystem.UserService1.Exceptions;

//exception for trader not found
public class TraderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;
    public TraderNotFoundException(String message) {
        super(message);
    }
}
