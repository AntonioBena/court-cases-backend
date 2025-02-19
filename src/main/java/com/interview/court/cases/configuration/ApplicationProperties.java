package com.interview.court.cases.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ApplicationProperties {
    @Value("${application.security.jwt.secret-key.key}")
    private String jwtSecretKey;
    @Value("${application.security.jwt.secret-key.expiration}")
    private long jwtExpiration;
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
}