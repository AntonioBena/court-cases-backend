package com.interview.court.cases.model.dto.requests;

import com.interview.court.cases.model.dto.UserDto;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRegisterUserRequest {
    private UserDto userDto;
    private String deviceType;
    private String deviceId;
    private String userAgent;

    @Override
    public String toString() {
        return "LoginRegisterUserRequest{" +
                "userDto=" + userDto +
                ", deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginRegisterUserRequest that = (LoginRegisterUserRequest) o;
        return Objects.equals(userDto, that.userDto) && Objects.equals(deviceType, that.deviceType) && Objects.equals(deviceId, that.deviceId) && Objects.equals(userAgent, that.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDto, deviceType, deviceId, userAgent);
    }
}