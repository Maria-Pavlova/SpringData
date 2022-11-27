package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.constants.FilePath.PATH_USERS;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PATH_USERS));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(this.readFromFileContent(), UserDto[].class))
                .filter(userDto -> {
                    boolean isValid = validationUtil.isValid(userDto);
                    if (userRepository.findByUsername(userDto.getUsername()).isPresent()
                            || pictureRepository.findByPath(userDto.getProfilePicture()).isEmpty()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported User: %s",
                                    userDto.getUsername())
                                    : "Invalid User")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(userDto -> {
                    User user = modelMapper.map(userDto, User.class);
                    user.setProfilePicture(pictureRepository.findByPath(userDto.getProfilePicture()).get());
                    return user;
                })
                .forEach(userRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder sb = new StringBuilder();
        List<User> users = userRepository.findAllOrderByPostsDescId();
        for (User user : users) {
            sb.append(user.toString());

            user.getPosts()
                    .stream()
                    .sorted(Comparator.comparingDouble(p -> p.getPicture().getSize()))
                    .forEach(sb::append);
        }
        return sb.toString();
    }
}
