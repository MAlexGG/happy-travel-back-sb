
package com.happy_travel.happy_travel.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UsernameCannotBeChangeException extends DataIntegrityViolationException {
    public UsernameCannotBeChangeException(String username){
        super("El nombre " + username + " no se puede cambiar");
    }
}