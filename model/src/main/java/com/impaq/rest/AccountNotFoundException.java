package com.impaq.rest;


public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String userId) {
        super("could not find account for user '" + userId + "'.");
    }
}
