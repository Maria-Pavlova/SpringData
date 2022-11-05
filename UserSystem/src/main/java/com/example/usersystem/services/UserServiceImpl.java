package com.example.usersystem.services;

import com.example.usersystem.models.User;
import com.example.usersystem.repositories.UserRepository;
import com.example.usersystem.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }


    @Override
    public void addUser(User user) {
        Set<ConstraintViolation<User>> violations = validationUtil.getViolations(user);
        if (!violations.isEmpty()) {
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        userRepository.save(user);
    }

    @Override
    public List<User> findUserByEmailProvider(String provider) {
     return userRepository.findByEmailEndingWith(provider);
    }

    @Override
    public List<User> findAllByLastTimeLoggedInBefore(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return userRepository.findAllByLastTimeLoggedInBefore(localDateTime);
    }

    @Override
    public int updateUsersIsDeleted(String date) {
        //2022-10-02 17:42:18.226860
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        return userRepository.updateUsersIsDeleted(localDateTime);
    }

    @Override
    public int deleteAllByDeletedIsTrue() {
        return userRepository.deleteAllByDeletedIsTrue();
    }
}
