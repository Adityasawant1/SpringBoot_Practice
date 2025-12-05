package com.SpringBoot.SpringLearningPhase.exception;

public class ResourceNotfoundException extends RuntimeException{
    public ResourceNotfoundException(String message) {
        super(message);
    }
}
