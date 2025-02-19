package com.interview.court.cases.service.impl;

import com.interview.court.cases.configuration.ApplicationProperties;
import com.interview.court.cases.configuration.security.JwtService;
import com.interview.court.cases.model.dto.requests.AuthenticationRequest;
import com.interview.court.cases.model.dto.requests.RegistrationRequest;
import com.interview.court.cases.model.dto.response.AuthenticationResponse;
import com.interview.court.cases.model.email.EmailTemplateName;
import com.interview.court.cases.model.user.Token;
import com.interview.court.cases.model.user.UserEntity;
import com.interview.court.cases.repository.TokenRepository;
import com.interview.court.cases.repository.UserRepository;
import com.interview.court.cases.service.AuthenticationService;
import com.interview.court.cases.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

import static com.interview.court.cases.model.user.UserRole.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ApplicationProperties appProperties;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //TODO add logging
    @Override
    public void registerUser(RegistrationRequest request) throws MessagingException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User already exists!");
        }

        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .accountLocked(false)
                .enabled(
                        appProperties.isCreateEnabledUsers()
                )
                .role(USER)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
        log.info("User registered successfully! {}", user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = (UserEntity) auth.getPrincipal();
        claims.put("fullName", user.fullName());
        var jwtToken = jwtService.generateToken(claims, user);
        log.info("JWT user: {}", user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void activateAccount(String activationCode) throws MessagingException {
        var savedToken = tokenRepository.findByToken(activationCode) //TODO exception handling
                .orElseThrow(() -> new IllegalStateException("Token not found"));
        var user = savedToken.getUser();
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(user);
            throw new IllegalStateException("Token expired!");
        }
        var foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        foundUser.setEnabled(true);
        userRepository.save(foundUser);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    private void sendValidationEmail(UserEntity user) throws MessagingException {
        String generatedToken = generateAndSaveActivationToken(user);
        if (!appProperties.isSendEmailVerification()) {
            log.info("Email verification is disabled, Verification token is: {}", generatedToken);
            return;
        }
        emailService.sendEmail(
                user.getEmail(),
                user.getFirstName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                appProperties.getActivationUrl(),
                generatedToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(UserEntity user) {
        var generatedToken = generateActivationCode();
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode() {
        String chars = appProperties.getActivationCodeChars();
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int index = 0; index < appProperties.getActivationTokenLength(); index++) {
            int randomIndex = secureRandom.nextInt(chars.length());
            codeBuilder.append(chars.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}