package com.happy_travel.happy_travel.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entity){
        super(entity.getSimpleName() + " con id " + id + " no encontrado");	
    }
}