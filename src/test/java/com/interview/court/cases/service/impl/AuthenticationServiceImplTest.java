package com.interview.court.cases.service.impl;

import com.interview.court.cases.configuration.ApplicationProperties;
import com.interview.court.cases.configuration.security.JwtUtils;
import com.interview.court.cases.model.dto.requests.AuthenticationRequest;
import com.interview.court.cases.model.user.UserEntity;
import com.interview.court.cases.model.user.UserRole;
import com.interview.court.cases.repository.TokenRepository;
import com.interview.court.cases.repository.UserRepository;
import com.interview.court.cases.service.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.interview.court.cases.TestUtils.generateRegistrationRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationServiceImplTest {


    private AuthenticationServiceImpl authService;
    private ModelMapper modelMapper;

    @Mock
    private ApplicationProperties applicationProperties;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private JwtUtils jwtUtils;

    @Mock
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        authService = new AuthenticationServiceImpl(applicationProperties,passwordEncoder, userRepository,
                tokenRepository, emailService,authenticationManager, jwtUtils);
    }

    @AfterEach
    void tearDown() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void test_should_create_new_User_with_token() throws MessagingException {
        var request = generateRegistrationRequest("email", "first", "last", "password");

        authService.registerUser(request);

        var users = userRepository.findAll();
        assertEquals(1, users.size());
        assertEquals(UserRole.USER, users.stream().findFirst().get().getRole());
        assertFalse(users.stream().findFirst().get().isEnabled());
        assertFalse(users.stream().findFirst().get().isAccountLocked());

        var token = tokenRepository.findAll();
        assertNotNull(token);

        assertEquals(token.getFirst().getUser(), users.getFirst());
    }

    @Test
    void test_should_create_authenticate_active_user() {
        var user = UserEntity.builder()
                .firstName("first")
                .lastName("last")
                .email("email")
                .role(UserRole.USER)
                .password(passwordEncoder.encode("password123456"))
                .enabled(true)
                        .build();

        userRepository.save(user);

        var request = AuthenticationRequest.builder()
                .email("email")
                .password("password123456")
                .build();

        var jwt = authService.authenticate(request);
        assertNotNull(jwt);
    }
}