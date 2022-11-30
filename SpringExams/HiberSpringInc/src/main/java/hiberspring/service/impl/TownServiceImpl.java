package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static hiberspring.common.Constants.*;


@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count()>0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return Files.readString(Path.of(PATH_TOWNS));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(this.readTownsJsonFile(), TownDto[].class))
                .filter(townDto -> {
                    boolean isValid = validationUtil.isValid(townDto);
                    sb.append(isValid ? String.format(SUCCESSFUL_IMPORT_MESSAGE, "Town",townDto.getName())
                            : INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    return isValid;
                })
                .map(townDto -> modelMapper.map(townDto, Town.class))
                .forEach(townRepository::saveAndFlush);
        return sb.toString();
    }
}
