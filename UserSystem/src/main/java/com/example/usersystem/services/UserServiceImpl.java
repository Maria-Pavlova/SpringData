package com.example.usersystem.services;

import com.example.usersystem.models.User;
import com.example.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void seedUser() {
        User user2 = new User("userTest2InvalidPass", "first1", "last1", "pass", "alabala@bg.com",
                LocalDateTime.now(), LocalDateTime.now(),25, false
                );
        userRepository.save(user2);

            User user1 = new User("userTest", "first2","last2", "Alabala1@", "alabala@ala.bala.com",
            LocalDateTime.now(), LocalDateTime.now(), 25, false);
    userRepository.save(user1);
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
//   TODO password validator