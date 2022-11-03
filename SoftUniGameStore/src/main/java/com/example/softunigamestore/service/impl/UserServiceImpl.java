package com.example.softunigamestore.service.impl;

import com.example.softunigamestore.models.dto.UserLoginDto;
import com.example.softunigamestore.models.dto.UserRegisterDto;
import com.example.softunigamestore.models.entity.User;
import com.example.softunigamestore.repositories.UserRepository;
import com.example.softunigamestore.service.UserService;
import com.example.softunigamestore.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.getViolations(userRegisterDto);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        if (loggedInUser != null){
            System.out.println("This user exists and is logged in");
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        long userCount = this.userRepository.count();
        if (userCount == 0){
            user.setAdmin(true);
        }
        userRepository.save(user);
        System.out.println(user.getFullName() + " was registered");

    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {

        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.getViolations(userLoginDto);

        if (!violations.isEmpty()) {
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);

        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
        System.out.println("Successfully logged in " + user.getFullName());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void logout() {
        if (loggedInUser == null){
            System.out.println("Cannot log out. No user was logged in");

        }else {

            System.out.println("User "+ loggedInUser.getFullName() +" successfully logged out");
            loggedInUser = null;
        }
    }

    @Override
    public boolean hasLoggedInUser() {
        return loggedInUser != null;
    }
}
