package com.example.cartecom.error.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("role not found");
    }
}
