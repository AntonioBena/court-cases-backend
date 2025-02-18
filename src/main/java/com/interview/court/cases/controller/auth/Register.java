package com.interview.court.cases.controller.auth;

import com.interview.court.cases.model.dto.requests.LoginRegisterUserRequest;
import com.interview.court.cases.service.impl.RegisterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/register")
public class Register {

    private final RegisterServiceImpl registerService;

    @PostMapping(path = "/new")
    public ResponseEntity<String> createCourtCase(@RequestBody LoginRegisterUserRequest request) {
        return registerService.registerNewUser(request);
    }
}
