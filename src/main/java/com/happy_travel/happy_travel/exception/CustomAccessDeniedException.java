package com.happy_travel.happy_travel.exception;

public class CustomAccessDeniedException extends RuntimeException{
    public CustomAccessDeniedException(){
        super("Acceso denegado");
    }
}
