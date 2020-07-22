package com.dumitru.taskmanager.exception;

public class ProcessLimitExceededException extends Exception {
    public ProcessLimitExceededException(String message) {
        super(message);
    }
}
