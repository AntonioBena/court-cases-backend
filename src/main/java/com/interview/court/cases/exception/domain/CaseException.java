package com.interview.court.cases.exception.domain;

import com.interview.court.cases.exception.CustomException;

public class CaseException extends CustomException {
    public CaseException(String message) {
        super(message);
    }
}