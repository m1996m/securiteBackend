package com.securite.Securite.dto.user;

import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements GenericDtoMapper<UserDto, User> {
    @Override
    public UserDto toDto(User User){
        return UserDto.builder()
                .firstName(User.getFirstName())
                .lastName(User.getLastName())
                .email(User.getEmail())
                .address(User.getAddress())
                .avatar(User.getAvatar())
                .idUser(User.getIdUser())
                .gender(User.getGender())
                .role(User.getRole())
                .createdAt(User.getCreatedAt())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return userDto.update(userDto);
    }
}
