package com.financetool.finance.util;

import com.financetool.finance.model.User;
import com.financetool.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputValidation {
    private static UserService userService;

    @Autowired
    public InputValidation(UserService service) {
        InputValidation.userService = service;
    }

    public static boolean checkEmailExists(String email) {
        User user = userService.getUserByEmail(email);
        if (user == null)
            return false;
        return true;
    }
}
