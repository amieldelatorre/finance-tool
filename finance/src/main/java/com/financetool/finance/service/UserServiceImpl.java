package com.financetool.finance.service;

import com.financetool.finance.dto.UserCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.User;
import com.financetool.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreateRequest user) {
        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User id " + userId + " cannot be found.");
        }

        return user;
    }

    @Override
    public Optional<User> updateUser(Integer userId, UserCreateRequest userRequest) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User id " + userId + " cannot be found.");
        }
        else {
            user.get().setFirstName(userRequest.getFirstName());
            user.get().setLastName(userRequest.getLastName());
            user.get().setEmail(userRequest.getEmail());
            user.get().setPassword(userRequest.getPassword());
            user.get().setRole(userRequest.getRole());
            userRepository.save(user.get());
            return user;
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<User> user = userRepository.findById((userId));

        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User id " + userId + " cannot be found.");
        }
        else {
            userRepository.delete(user.get());
        }
    }
}
