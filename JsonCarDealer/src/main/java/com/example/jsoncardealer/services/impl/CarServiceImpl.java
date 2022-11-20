package com.example.jsoncardealer.services.impl;

import com.example.jsoncardealer.models.dtoExport.CarAndPartDto;
import com.example.jsoncardealer.models.dtoExport.CarExportDto;
import com.example.jsoncardealer.models.entities.Car;
import com.example.jsoncardealer.repositories.CarRepository;
import com.example.jsoncardealer.services.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.jsoncardealer.constants.FilePath.*;


@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCars(List<Car> cars) {
        if (carRepository.count() == 0){
            carRepository.saveAll(cars);
        }
    }

    @Override
    public void getCarsMakeFrom(String makeBy) throws IOException {
        List<CarExportDto> carExportDtos = carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(makeBy)
                .stream()
                .map(car -> modelMapper.map(car, CarExportDto.class))
                .toList();
        String json = gson.toJson(carExportDtos);
        System.out.println(json);
        writeToFile(EXPORT_PATH + TOYOTA_CARS_FILE, json);

    }

    @Override
    public void getCarsWithTheirParts() throws IOException {
        List<CarAndPartDto> dtos = carRepository.findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarAndPartDto.class))
                .collect(Collectors.toList());
        String json = gson.toJson(dtos);
        System.out.println(json);
     //   writeToFile(EXPORT_PATH + CARS_AND_PARTS_FILE, json);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), Collections.singleton(content));
    }

}
