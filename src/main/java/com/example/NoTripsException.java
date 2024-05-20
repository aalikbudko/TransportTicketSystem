package com.example;

// исключение, которое выбрасывается, если поездок нет
class NoTripsException extends Exception {
    public NoTripsException(String message) {
        super(message);
    }
}