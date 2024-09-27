package com.happy_travel.happy_travel.exception;

public class EmptyException extends RuntimeException {
    public EmptyException(){
        super("No existen datos");
    }
}
