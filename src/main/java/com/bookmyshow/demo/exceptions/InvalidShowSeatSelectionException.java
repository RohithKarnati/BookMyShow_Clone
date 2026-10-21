package com.bookmyshow.demo.exceptions;

public class InvalidShowSeatSelectionException extends RuntimeException {
    public InvalidShowSeatSelectionException(String message) {
        super(message);
    }

    public InvalidShowSeatSelectionException(){
    }
}
