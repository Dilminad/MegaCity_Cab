package com.megacity.server.exception;

public class ExceptionHandler {

    // Method for handling customer not found exceptions
    public void handleCustomerNotFound(String message) {
        throw new CustomerNotFoundException(message);
    }

    // Method for handling username already exists exceptions
    public void handleUsernameAlreadyExists(String message) {
        throw new UsernameAlreadyExistsException(message);
    }

    // Custom exception for when a customer is not found
    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    // Custom exception for when a username already exists
    public static class UsernameAlreadyExistsException extends RuntimeException {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }
}
