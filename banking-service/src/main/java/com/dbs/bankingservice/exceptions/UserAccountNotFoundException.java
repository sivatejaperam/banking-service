package com.dbs.bankingservice.exceptions;


public class UserAccountNotFoundException extends RuntimeException {


    public UserAccountNotFoundException(String message) {
        super(message);
    }
}
