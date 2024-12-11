package com.securite.Securite.controller;

import com.securite.Securite.dto.user.UserDto;
import com.securite.Securite.generic.GenericController;
import com.securite.Securite.generic.GenericDtoMapper;
import com.securite.Securite.generic.GenericService;
import com.securite.Securite.model.Organisme;
import com.securite.Securite.model.User;
import com.securite.Securite.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends GenericController<User, UserDto> {
    private final UserService userService;

    public UserController(
            GenericService<User> genericService,
            GenericDtoMapper<UserDto, User> genericDtoMapper,
            UserService userService
    ) {
        super(genericService, genericDtoMapper);
        this.userService = userService;
    }

    @Operation(summary = "Get user with By email")
    @GetMapping("/email")
    public User getUserByEmail(@RequestParam("email") String email){
        return userService.findUniqueWithValueAndAttribut(email,"email");
    }

    @Operation(summary = "Get user with By tel")
    @GetMapping("/tel")
    public User getUserByTel(@RequestParam("tel") String tel){
        return userService.findUniqueWithValueAndAttribut(tel,"tel");
    }
}
