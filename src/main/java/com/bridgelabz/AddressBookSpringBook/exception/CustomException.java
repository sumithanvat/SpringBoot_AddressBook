package com.bridgelabz.AddressBookSpringBook.exception;

public class CustomException extends RuntimeException{
    public CustomException(String employee){
        super(employee);
    }
}
