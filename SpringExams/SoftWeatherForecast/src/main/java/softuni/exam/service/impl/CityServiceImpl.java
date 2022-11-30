package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityDto;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static softuni.exam.constants.FilePath.PATH_CITIES;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(PATH_CITIES));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(this.readCitiesFileContent(), CityDto[].class))
                .filter(cityDto -> {
                    boolean isValid = validationUtil.isValid(cityDto);
                    if (cityRepository.findByCityName(cityDto.getCityName()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported city %s - %d",
                            cityDto.getCityName(), cityDto.getPopulation())
                            : "Invalid city").append(System.lineSeparator());
                    return isValid;
                })
                .map(cityDto -> {
                    City city = modelMapper.map(cityDto, City.class);
                    city.setCountry(countryRepository.findById(cityDto.getCountry()).get());
                    return city;
                })
                .forEach(cityRepository::saveAndFlush);
        return sb.toString();
    }
}
