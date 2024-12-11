package com.securite.Securite.dto.auth;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String email;

    private String password;

}
