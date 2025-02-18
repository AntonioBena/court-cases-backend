package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.requests.LoginRegisterUserRequest;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<String> registerNewUser(LoginRegisterUserRequest request);
}