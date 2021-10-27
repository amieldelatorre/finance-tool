package com.financetool.finance.util;

import com.financetool.finance.dto.UserCreateRequest;
import com.financetool.finance.exception.BadRequestException;
import com.financetool.finance.model.User;
import com.financetool.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class InputValidation {
    private static UserService userService;

    @Autowired
    public InputValidation(UserService service) {
        InputValidation.userService = service;
    }

    public static boolean EmailExists(String email) {
        User user = userService.getUserByEmail(email);
        return user != null;
    }

    public static boolean checkStringNotEmpty(String apiStringInput) {
        return (apiStringInput != null && !apiStringInput.trim().isEmpty());
    }

    public static boolean isValidUserCreateRequest(UserCreateRequest userCreateRequest, HttpServletRequest request) {
        if (!checkStringNotEmpty(userCreateRequest.getFirstName()))
            throw new BadRequestException("Field firstName cannot be null or empty.", request);
        if (!checkStringNotEmpty(userCreateRequest.getLastName()))
            throw new BadRequestException("Field lastName cannot be null or empty.", request);
        if (!checkStringNotEmpty(userCreateRequest.getEmail()))
            throw new BadRequestException("Field email cannot be null or empty.", request);
        if (EmailExists(userCreateRequest.getEmail()))
            throw new BadRequestException("Sorry this email already exists.", request);
        if (!checkStringNotEmpty(userCreateRequest.getPassword()))
            throw new BadRequestException("Field password cannot be null or empty.", request);

        return true;
    }

}
