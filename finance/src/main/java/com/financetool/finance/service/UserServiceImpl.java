package com.financetool.finance.service;

import com.financetool.finance.dto.UserCreateRequest;
import com.financetool.finance.exception.ResourceNotFoundException;
import com.financetool.finance.model.User;
import com.financetool.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
        newUser.setRoleType(user.getRoleType());
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            throw new ResourceNotFoundException("User id " + userId + " cannot be found.", request);
        }

        return user.get();
    }

    @Override
    public User updateUser(Integer userId, UserCreateRequest userRequest) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            throw new ResourceNotFoundException("User id " + userId + " cannot be found.", request);
        }
        else {
            user.get().setFirstName(userRequest.getFirstName());
            user.get().setLastName(userRequest.getLastName());
            user.get().setEmail(userRequest.getEmail());
            user.get().setPassword(userRequest.getPassword());
            user.get().setRoleType(userRequest.getRoleType());
            userRepository.save(user.get());
            return user.get();
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            throw new ResourceNotFoundException("User id " + userId + " cannot be found.", request);
        }
        else {
            userRepository.delete(user.get());
        }
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
