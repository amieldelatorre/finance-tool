package com.financetool.finance.service;

import com.financetool.finance.dto.UserCreateRequest;
import com.financetool.finance.model.User;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    User createUser(UserCreateRequest user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer userId);
    Optional<User> updateUser(Integer userId, UserCreateRequest userRequest);
    void deleteUser(Integer userId);

}
