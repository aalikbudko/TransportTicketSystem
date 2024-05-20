package com.example;

class ExpiredTicketException extends Exception {
    public ExpiredTicketException(String message) {
        super(message);
    }
}
