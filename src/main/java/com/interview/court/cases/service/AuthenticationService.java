package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.requests.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationRequest request) throws MessagingException;
}