package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.UserDto;

public interface LoginService {
    UserDto login(String username, String password);
    void logout();
}