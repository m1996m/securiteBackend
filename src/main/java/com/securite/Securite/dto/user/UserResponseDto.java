package com.securite.Securite.dto.user;

import com.securite.Securite.enumeration.Genre;
import com.securite.Securite.enumeration.Role;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private long idUser;
    private String email;
    private String firstName;
    private String tel;
    private String address;
    private String lastName;
    private String password;
    private String slug;
    private Role role = Role.USER;
    private String avatar;
    private Genre gender = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idOrganisme;
}
