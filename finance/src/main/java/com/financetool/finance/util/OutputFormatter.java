package com.financetool.finance.util;

import com.financetool.finance.dto.UserOutDto;
import com.financetool.finance.model.User;

public class OutputFormatter {
    public static UserOutDto userToUserOutDto(User user) {
        UserOutDto userOut = new UserOutDto();

        userOut.setId(user.getId());
        userOut.setFirstName(user.getFirstName());
        userOut.setLastName(user.getLastName());
        userOut.setEmail(user.getEmail());
        userOut.setRole(user.getRole());

        return userOut;
    }
}
