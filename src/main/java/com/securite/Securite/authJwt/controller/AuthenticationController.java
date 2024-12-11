package com.securite.Securite.authJwt.controller;

import com.securite.Securite.authJwt.LoginResponse;
import com.securite.Securite.authJwt.service.AuthenticationService;
import com.securite.Securite.authJwt.service.JwtService;
import com.securite.Securite.dto.user.UserDto;
import com.securite.Securite.dto.auth.LoginUserDto;
import com.securite.Securite.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User registeredUser = authenticationService.signup(userDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Upadate to password by slug")
    @PostMapping("/update/{slug}")
    public String updatePasswordBySlug(@RequestBody UserDto userDto, @PathVariable String slug) {
        return authenticationService.updatePassword(userDto, slug);
    }

    @Operation(summary = "Verify same password ")
    @PostMapping("/{email}")
    public boolean verifySamePassword(@PathVariable String email) {
        return authenticationService.verifySamePassword(email);
    }
}
