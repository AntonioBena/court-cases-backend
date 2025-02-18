package com.interview.court.cases.model.dto.requests;

import com.interview.court.cases.model.dto.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class LoginRegisterUserRequest {
    private UserDto userDto;
    private String deviceType;
    private String deviceId;
    private String userAgent;
}