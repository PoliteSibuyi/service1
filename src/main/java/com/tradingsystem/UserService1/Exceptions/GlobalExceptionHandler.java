package com.tradingsystem.UserService1.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//this class will handle all exceptions thrown
@ControllerAdvice

public class GlobalExceptionHandler {
    //interceptor the exceptions
    @ExceptionHandler(TraderNotFoundException.class)
    public ResponseEntity<ErrorObject> handleTradeNotFound(TraderNotFoundException ex, WebRequest request) {
        //the object is what could have been displayed in the web page of the error
        ErrorObject errorObject=new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

}