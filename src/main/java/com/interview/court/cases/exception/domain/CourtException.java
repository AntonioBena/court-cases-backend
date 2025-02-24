package com.interview.court.cases.exception.domain;

import com.interview.court.cases.exception.CustomException;

public class CourtException extends CustomException{
    public CourtException(String message) {
        super(message);
    }
}