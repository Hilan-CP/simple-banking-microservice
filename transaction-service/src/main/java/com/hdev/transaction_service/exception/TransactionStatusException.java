package com.hdev.transaction_service.exception;

public class TransactionStatusException extends RuntimeException {
    public TransactionStatusException() {
        super("Invalid transaction status transition");
    }

    public TransactionStatusException(String message) {
        super(message);
    }
}
