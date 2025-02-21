package com.interview.court.cases.controller.auth;

import com.interview.court.cases.model.dto.requests.AuthenticationRequest;
import com.interview.court.cases.model.dto.requests.RegistrationRequest;
import com.interview.court.cases.service.impl.AuthenticationServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.registerUser(request); //TODO remove messaging exception and handle them internaly
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(
            @RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/activate-account")
    public void activateAccount(@RequestParam String activationCode) throws MessagingException {
        authenticationService.activateAccount(activationCode);
    }
}