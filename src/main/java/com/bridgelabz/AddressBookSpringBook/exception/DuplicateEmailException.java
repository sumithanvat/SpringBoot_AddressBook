package com.bridgelabz.AddressBookSpringBook.exception;

// Custom exception class
public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}
