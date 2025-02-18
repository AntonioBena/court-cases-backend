package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.user.UserEntity;
import com.interview.court.cases.model.dto.UserDto;
import com.interview.court.cases.model.dto.requests.LoginRegisterUserRequest;
import com.interview.court.cases.repository.UserRepository;
import com.interview.court.cases.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<String> registerNewUser(LoginRegisterUserRequest request) {
        register(request.getUserDto(), request.getDeviceType(), request.getDeviceId());
        return ResponseEntity.ok("Success");
    }

    private void register(UserDto userDto, String deviceType, String deviceId) {
        var userExists = userRepository.existsByEmail(userDto.getEmail());
        if (userExists) {
            throw new IllegalStateException("User already exists!");
        }
        var newUser = mapper.map(userDto, UserEntity.class);
        userRepository.save(newUser);
    }
}