package com.app.exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username){
        super(MessageFormat.format("Could not found user with username: {0}", username));
    }
}
