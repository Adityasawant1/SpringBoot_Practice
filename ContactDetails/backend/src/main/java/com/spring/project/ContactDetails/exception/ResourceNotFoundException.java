package com.spring.project.ContactDetails.exception;

public class ResourceNotFoundException extends RuntimeException{
    ResourceNotFoundException(String message)
    {
        super(message);
    }
}
