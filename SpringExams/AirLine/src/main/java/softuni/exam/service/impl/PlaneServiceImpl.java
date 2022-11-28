package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.plane.PlaneRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.FilePath.PATH_PLANES;


@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PATH_PLANES));
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(PATH_PLANES, PlaneRootDto.class)
                .getPlanes()
                .stream()
                .filter(planeDto -> {
                    boolean isValid = validationUtil.isValid(planeDto);
                    if (planeRepository.findByRegisterNumber(planeDto.getRegisterNumber()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Plane %s",
                            planeDto.getRegisterNumber())
                            : "Invalid Plane").append(System.lineSeparator());
                    return isValid;
                })
                .map(planeDto -> modelMapper.map(planeDto, Plane.class))
                .forEach(planeRepository::saveAndFlush);

        return sb.toString();
    }
}
