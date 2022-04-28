package com.publicis.creditcardprovider.exception;

public class CreditCardProviderException extends Exception{

    private static final String MESSAGE = "Invalid Data";

    public CreditCardProviderException() {
        super(MESSAGE);
    }

    public CreditCardProviderException(String message) {
        super(message);
    }
}
