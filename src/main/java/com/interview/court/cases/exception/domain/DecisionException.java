package com.interview.court.cases.exception.domain;

import com.interview.court.cases.exception.CustomException;

public class DecisionException extends CustomException {
    public DecisionException(String message) {
        super(message);
    }
}