package com.example.productshopxml.services.impl;

import com.example.productshopxml.models.dto.usersAndProducts.UsersAndProductsRootDto;
import com.example.productshopxml.models.dto.usersAndProducts.UsersWithSoldProductsDto;
import com.example.productshopxml.models.dto.usersSoldProducts.*;
import com.example.productshopxml.models.entities.User;
import com.example.productshopxml.repositories.UserRepository;
import com.example.productshopxml.services.UserService;
import com.example.productshopxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import static com.example.productshopxml.constants.FilePaths.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
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
    public void getUsersSoldProducts() throws JAXBException {

        List<UserNamesDto> userNamesDtos = userRepository.findAllByProductsSoldBuyerIsNotNullOrderByProductsSoldBuyerFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserNamesDto.class)).toList();

        UsersSoldProductsRootDto usersSoldProductsRootDto = new UsersSoldProductsRootDto(userNamesDtos);

        xmlParser.writeToFile(OUTPUT_PATH_XML + USERS_SOLD_PRODUCTS_FILE_XML, usersSoldProductsRootDto);

    }

    @Override
    @Transactional
    public void getUsersAndProducts() throws IOException, JAXBException {

        List<UsersWithSoldProductsDto> usersWithSoldProductsDtos = userRepository.findAllUsersAndSoldProducts()
                .stream()
                .map(user -> modelMapper.map(user, UsersWithSoldProductsDto.class))
                .toList();
        UsersAndProductsRootDto usersAndProductsRootDto = new UsersAndProductsRootDto(usersWithSoldProductsDtos);

        xmlParser.writeToFile(OUTPUT_PATH_XML + USERS_AND_PRODUCTS_FILE_XML, usersAndProductsRootDto);

    }
}
