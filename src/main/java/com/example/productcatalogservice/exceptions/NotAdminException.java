package com.example.productcatalogservice.exceptions;

public class NotAdminException extends Exception{
    public NotAdminException(String message) {
        super(message);
    }
}
