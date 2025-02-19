package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.requests.AuthenticationRequest;
import com.interview.court.cases.model.dto.requests.RegistrationRequest;
import com.interview.court.cases.model.dto.response.AuthenticationResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    void registerUser(RegistrationRequest request) throws MessagingException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void activateAccount(String token) throws MessagingException;
}