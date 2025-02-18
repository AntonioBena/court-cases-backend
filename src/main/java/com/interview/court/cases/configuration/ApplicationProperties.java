package com.interview.court.cases.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
public class ApplicationProperties {
    @Value("${application.security.jwt.secret-key.key}")
    private String jwtSecretKey;
    @Value("${application.security.jwt.secret-key.expiration}")
    private long jwtExpiration;
}