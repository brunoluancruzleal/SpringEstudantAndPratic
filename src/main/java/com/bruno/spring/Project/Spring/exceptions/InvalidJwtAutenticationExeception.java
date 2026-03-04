package com.bruno.spring.Project.Spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAutenticationExeception extends AuthenticationServiceException {
    public InvalidJwtAutenticationExeception(String message) {
        super(message);
    }
}
