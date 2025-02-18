package com.interview.court.cases.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean isActive;
}