package com.impaq.config;


public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String userId) {
        super("could not find account for user '" + userId + "'.");
    }
}
