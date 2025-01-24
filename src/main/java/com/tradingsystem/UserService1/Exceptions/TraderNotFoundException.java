package com.tradingsystem.UserService1.Exceptions;

public class TraderNotFoundException extends RuntimeException{
    private static  final long serialVersionUID=1;

    public TraderNotFoundException(String message) {
        super(message);
    }
}
