package com.alkemy.ong.exception;

public class EmailAlreadyExists extends Exception{

    public EmailAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
