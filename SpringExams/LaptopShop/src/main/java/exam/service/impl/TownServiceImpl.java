package exam.service.impl;

import exam.model.dto.TownDto;
import exam.model.dto.TownRootDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static exam.constants.filePath.PATH_TOWNS;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_TOWNS));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        List<TownDto> towns = xmlParser.fromFile(PATH_TOWNS, TownRootDto.class).getTowns();
        StringBuilder sb = new StringBuilder();
        towns.stream()
                .filter(townDto -> {
                    boolean isValid = validationUtil.isValid(townDto);
                    if (townRepository.findByName(townDto.getName()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Town %s", townDto.getName())
                            : "Invalid town").append(System.lineSeparator());

                    return isValid;
                })
                .map(townDto -> modelMapper.map(townDto, Town.class))
                .forEach(townRepository::saveAndFlush);
        return sb.toString();
    }
}
