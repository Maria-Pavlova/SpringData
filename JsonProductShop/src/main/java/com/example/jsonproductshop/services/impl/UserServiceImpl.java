package com.example.jsonproductshop.services.impl;

import com.example.jsonproductshop.models.dto.*;
import com.example.jsonproductshop.models.entities.User;
import com.example.jsonproductshop.models.entities.UserDto;
import com.example.jsonproductshop.repositories.UserRepository;
import com.example.jsonproductshop.services.UserService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.jsonproductshop.constants.FilePaths.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers(List<User> users) {
        if (userRepository.count() == 0){
            userRepository.saveAllAndFlush(users);
        }
    }

    @Override
    public User getRandomUser() {
        long randomId = new Random().nextLong(1L, userRepository.count());
        return userRepository.findById(randomId).orElse(null);
    }

    @Override
    public void getUsersSoldProducts() throws IOException {
        List<UserSoldProductsDto> userSoldProductsDtos = userRepository.findAllWithSoldProducts()
                .stream()
                .map(user ->
                        modelMapper.map(user, UserSoldProductsDto.class)
                )
                .collect(Collectors.toList());

        String jsonContent = gson.toJson(userSoldProductsDtos);
        System.out.println(jsonContent);
        writeToFile(OUTPUT_PATH + USERS_SOLD_PRODUCTS_FILE, jsonContent);
    }

    @Override
    @Transactional
    public void getUsersAndProducts() throws IOException {

        List<UsersWithSoldProductsDto> usersWithSoldProductsDtos = userRepository.findAllUsersAndSoldProducts()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .map(UserDto::toUsersWithSoldProductsDto)
                .collect(Collectors.toList());
        UsersAndProductsWrapperDto usersAndProductsWrapperDto = new UsersAndProductsWrapperDto(usersWithSoldProductsDtos);


        String jsonContent = gson.toJson(usersAndProductsWrapperDto);

        System.out.println(jsonContent);
        writeToFile(OUTPUT_PATH + USERS_AND_PRODUCTS_FILE, jsonContent);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }
}
