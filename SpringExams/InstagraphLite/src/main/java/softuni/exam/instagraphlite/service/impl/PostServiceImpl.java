package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostRootDto;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.instagraphlite.constants.FilePath.PATH_POSTS;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, XmlParser xmlParser, ValidationUtil validationUtil,
                           ModelMapper modelMapper, PictureRepository pictureRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PATH_POSTS));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PATH_POSTS, PostRootDto.class)
                .getPosts()
                .stream()
                .filter(postDto -> {
                    boolean isValid = validationUtil.isValid(postDto);
                    if (pictureRepository.findByPath(postDto.getPicture().getPath()).isEmpty()
                            || userRepository.findByUsername(postDto.getUser().getUsername()).isEmpty()) {
                        isValid = false;
                    }
                    sb.append( isValid ? String.format("Successfully imported Post, made by %s",
                                    postDto.getUser().getUsername())
                                    : "Invalid Post")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(postDto -> {
                    Post post = modelMapper.map(postDto, Post.class);
                    post.setUser(userRepository.findByUsername(postDto.getUser().getUsername()).get());
                    post.setPicture(pictureRepository.findByPath(postDto.getPicture().getPath()).get());
                    return post;
                })
                .forEach(postRepository::saveAndFlush);
                

        return sb.toString();
    }
}
