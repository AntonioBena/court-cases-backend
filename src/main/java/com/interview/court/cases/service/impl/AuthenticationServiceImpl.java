package com.interview.court.cases.service.impl;

import com.interview.court.cases.configuration.ApplicationProperties;
import com.interview.court.cases.model.dto.requests.RegistrationRequest;
import com.interview.court.cases.model.email.EmailTemplateName;
import com.interview.court.cases.model.user.Token;
import com.interview.court.cases.model.user.UserEntity;
import com.interview.court.cases.repository.RoleRepository;
import com.interview.court.cases.repository.TokenRepository;
import com.interview.court.cases.repository.UserRepository;
import com.interview.court.cases.service.AuthenticationService;
import com.interview.court.cases.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ApplicationProperties appProperties;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Override
    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER") //TODO add roles enumeration
                .orElseThrow(() -> new IllegalStateException("Role USER is not initialized!")); //TODO add exception handling
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
                .enabled(false) //TODO you can make some email service
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }
    private void sendValidationEmail(UserEntity user) throws MessagingException {
        String generatedToken = generateAndSaveActivationToken(6, user);
        if (!appProperties.isSendEmailVerification()) {
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

    private String generateAndSaveActivationToken(int lenght, UserEntity user) {
        var generatedToken = generateActivationCode(lenght);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int lenght){
        String chars = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int index = 0; index < lenght; index++) {
            int randomIndex = secureRandom.nextInt(chars.length());
            codeBuilder.append(chars.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}