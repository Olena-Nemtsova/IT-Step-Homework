package edu.it.step.exception;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
