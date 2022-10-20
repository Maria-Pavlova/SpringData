package com.example.usersystem.services;

import com.example.usersystem.models.User;
import com.example.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
//        User user2 = new User("userTest2", null, "", ".alabala.@com",
//                LocalDateTime.now(), LocalDateTime.now(), 25, false, null, null,
//                null, null, null);
//        userRepository.save(user2);

            User user1 = new User("userTest", null, "Alabala1@", "alabala@ala.bala.com",
            LocalDateTime.now(), LocalDateTime.now(), 25, false, null, null,
            null,null,null);
    userRepository.save(user1);
    }

    @Override
    public List<User> findUserByEmailProvider(String provider) {
     return userRepository.findByEmailEndingWith(provider);
    }
}
//   TODO password validator