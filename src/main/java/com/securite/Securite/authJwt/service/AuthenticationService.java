package com.securite.Securite.authJwt.service;

import com.securite.Securite.dto.user.UserDto;
import com.securite.Securite.dto.auth.LoginUserDto;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.User;
import com.securite.Securite.repository.nature.UserRepository;
import com.securite.Securite.service.OrganismeService;
import com.securite.Securite.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final OrganismeService organismeService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, UserService userService, OrganismeService organismeService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.organismeService = organismeService;
    }

    public User signup(UserDto userDto) {
        Organisme organisme = organismeService.findUniqueById(userDto.getIdOrganisme());

        User user = userDto.create(userDto, organisme);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }

    public String updatePassword(UserDto userDto, String slug) {
        User user = userService.findUniqueWithValueAndAttribut(slug,"slug");

        User userToUpadatePassword = userDto.updatePassword(userDto,user);

        userToUpadatePassword.setPassword(passwordEncoder.encode(userToUpadatePassword.getPassword()));

        try {
            userRepository.save(userToUpadatePassword);
        }catch (Exception e){
            throw new RuntimeException("Une erreur lors de la mise à jour du mot de passe");
        }

        return "Le mot de passe à été bien modifié";
    }

    public boolean verifySamePassword(String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (optionalUser.isEmpty())
            return false;

        User user = optionalUser.get();

        return !Objects.equals(user.getPassword(), passwordEncoder.encode(email));
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail());
    }
}

