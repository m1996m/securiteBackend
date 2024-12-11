package com.securite.Securite.dto.user;


import com.securite.Securite.enumeration.Genre;
import com.securite.Securite.enumeration.Role;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.User;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
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
    private String slugOrganisme;

    public User create(UserDto userDto, Organisme organisme){
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setOrganisme(organisme);
        user.setSlugOrganisme(organisme.getSlug());

        return user;
    }

    public UserDto toDto(User User){
        return UserDto.builder()
                .firstName(User.getFirstName())
                .lastName(User.getLastName())
                .email(User.getEmail())
                .address(User.getAddress())
                .avatar(User.getAvatar())
                .idUser(User.getIdUser())
                .gender(User.getGender())
                //.role(User.getRole())
                .createdAt(User.getCreatedAt())
                .build();
    }

    public User login(UserDto userDto){
        PasswordEncoder passwordEncoder = null;
        
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }

    public User update(UserDto userDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;

            // Mise à jour des champs
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setAddress(userDto.getAddress());
            user.setTel(userDto.getTel());
            user.setGender(userDto.getGender());

            return user;
        } else {
            throw new RuntimeException("Unknown principal type: " + principal.getClass());
        }
    }


    public User updateRole(UserDto userDto, User user){
        user.setRole(userDto.getRole());

        return user;
    }

    public User enableUnableStatus(User user){
        //user.setRole(user.());

        return user;
    }

    public User updatePassword(UserDto userDto, User user){
        user.setPassword(userDto.getPassword());

        return user;
    }
}
