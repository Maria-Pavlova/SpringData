package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.FilePath.*;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(PATH_COUNTRIES));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(this.readCountriesFromFile(), CountryDto[].class))
                .filter(countryDto -> {
                    boolean isValid = validationUtil.isValid(countryDto);
                    if (countryRepository.findByCountryName(countryDto.getCountryName()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported country %s - %s",
                            countryDto.getCountryName(), countryDto.getCurrency())
                            : "Invalid country").append(System.lineSeparator());
                    return isValid;
                })
                .map(countryDto -> modelMapper.map(countryDto, Country.class))
                .forEach(countryRepository::saveAndFlush);
        return sb.toString();
    }
}
