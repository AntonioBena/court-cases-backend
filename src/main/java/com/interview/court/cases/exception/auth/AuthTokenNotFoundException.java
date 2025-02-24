package com.interview.court.cases.exception.auth;

import com.interview.court.cases.exception.CustomException;

public class AuthTokenNotFoundException extends CustomException {
    public AuthTokenNotFoundException(String message) {
        super(message);
    }

    public AuthTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
