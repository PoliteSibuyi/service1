package com.tradingsystem.UserService1.Exceptions;

import lombok.Data;

import java.util.Date;

//Error object created to store the messages on the page
@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;

    public ErrorObject() {
    }

    public ErrorObject(Integer statusCode, String message, Date timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
