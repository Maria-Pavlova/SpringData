package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static softuni.exam.instagraphlite.constants.FilePath.PATH_PICTURES;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PATH_PICTURES));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(this.readFromFileContent(), PictureDto[].class))
                .filter(pictureDto -> {
                    boolean isValid = validationUtil.isValid(pictureDto);
                    if (pictureRepository.findByPath(pictureDto.getPath()).isPresent()) {
                        isValid = false;
                    }
                    sb.append( isValid ? String.format("Successfully imported Picture with size %.2f",
                                    pictureDto.getSize())
                                    : "Invalid Picture")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(pictureDto -> modelMapper.map(pictureDto, Picture.class))
                .forEach(pictureRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String exportPictures() {
       return pictureRepository.findBySizeGreaterThanOrderBySize(30000)
                .stream()
                .map(Picture::toString)
                .collect(Collectors.joining(System.lineSeparator()));

    }
}
