package com.interview.court.cases.exception.domain;

import com.interview.court.cases.exception.CustomException;

public class RegisteredUserException extends CustomException {
    public RegisteredUserException(String message) {
        super(message);
    }
}