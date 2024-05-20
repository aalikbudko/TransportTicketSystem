package com.example;

public class InvalidTicketTypeException extends Exception {
    public InvalidTicketTypeException(String message) {
        super(message);
    }
}