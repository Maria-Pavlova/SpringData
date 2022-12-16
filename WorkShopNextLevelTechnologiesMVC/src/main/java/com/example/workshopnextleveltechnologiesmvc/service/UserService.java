package com.example.workshopnextleveltechnologiesmvc.service;

import com.example.workshopnextleveltechnologiesmvc.service.models.LoginModel;
import com.example.workshopnextleveltechnologiesmvc.service.models.UserRegisterModel;
import com.example.workshopnextleveltechnologiesmvc.data.entities.User;
import com.example.workshopnextleveltechnologiesmvc.repositories.UserRepository;
import com.example.workshopnextleveltechnologiesmvc.service.models.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
        User user = this.modelMapper.map(userRegisterModel, User.class);
        return this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);

    }

    public Optional<User> login(LoginModel loginModel){
       return this.userRepository.findByUsernameAndPassword(loginModel.getUsername(), loginModel.getPassword());
    }
}
