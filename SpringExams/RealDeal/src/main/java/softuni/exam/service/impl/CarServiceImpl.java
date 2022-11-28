package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static softuni.exam.constants.FilePath.PATH_CARS;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return carRepository.count()>0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_CARS));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(this.readCarsFileContent(), CarDto[].class))
                .filter(carDto -> {
                    boolean isValid = validationUtil.isValid(carDto);
                    if (carRepository.findByMakeAndModelAndKilometers(carDto.getMake(),carDto.getModel(),carDto.getKilometers()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported car - %s - %s",
                            carDto.getMake(), carDto.getModel())
                            : "Invalid car").append(System.lineSeparator());
                    return isValid;
                })
                .map(carDto -> modelMapper.map(carDto, Car.class))
                .forEach(carRepository::saveAndFlush);
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return carRepository.findAllOrderByPicturesCountDescThenByMake()
                .stream().map(Car::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
