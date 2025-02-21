package com.interview.court.cases.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ApplicationProperties {
    @Value("${email.send.verification}")
    private boolean sendEmailVerification;
    @Value("${email.send.activationUrl}")
    private String activationUrl;
    @Value("${application.security.create-enabled-users}")
    private boolean createEnabledUsers;
    @Value("${application.security.activation.token.length}")
    private int activationTokenLength;
    @Value("${application.security.activation.code.chars}")
    private String activationCodeChars;
    @Value("${application.security.activation-token.expire.mins}")
    private int activationTokenExpireMins;
    @Value("${application.security.allowed-origins}")
    private String allowedOrigins;

    @Value("${application.security.jwt-expiration}")
    private String jwtExpiration;

    @Value("${application.security.jwt.secret}")
    private String jwtSecret;
}