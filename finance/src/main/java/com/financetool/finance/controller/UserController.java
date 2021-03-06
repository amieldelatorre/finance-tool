package com.financetool.finance.controller;

import com.financetool.finance.exception.InternalServerException;
import com.financetool.finance.service.UserService;
import com.financetool.finance.util.InputValidation;
import com.financetool.finance.util.OutputFormatter;
import com.financetool.finance.dto.UserCreateRequest;
import com.financetool.finance.dto.UserOutDto;
import com.financetool.finance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public @ResponseStatus(HttpStatus.CREATED) UserOutDto addNewUser(@RequestBody @Valid UserCreateRequest user, HttpServletRequest request) {
        boolean validUserCreateRequest = InputValidation.isValidUserCreateRequest(user, request);

        if (validUserCreateRequest) {
            User newUser = userService.createUser(user);
            return OutputFormatter.userToUserOutDto(newUser);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @GetMapping(path = "/users", produces = "application/json")
    public Iterable<UserOutDto> getAllUsers() {
        List<UserOutDto> userOutList = new ArrayList<UserOutDto>();
        List<User> users = userService.getAllUsers();

        for (User user : users)
            userOutList.add(OutputFormatter.userToUserOutDto(user));

        return userOutList;
    }

    @GetMapping(path = "/users/{userId}", produces = "application/json")
    public UserOutDto getUserById(@PathVariable(value="userId") Integer userId) {
        User user = userService.getUserById(userId);

        return OutputFormatter.userToUserOutDto(user);
    }

    @PutMapping(path = "/users/{userId}", consumes = "application/json", produces = "application/json")
    public UserOutDto updateUserById(@PathVariable(value="userId") Integer userId, @RequestBody UserCreateRequest userRequest, HttpServletRequest request) {
        boolean validUserCreateRequest = InputValidation.isValidUserCreateRequest(userRequest, request);

        if (validUserCreateRequest) {
            User user = userService.updateUser(userId, userRequest);
            return OutputFormatter.userToUserOutDto(user);
        }
        else
            throw new InternalServerException("Sorry something went wrong.", request);
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value="userId") Integer userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
