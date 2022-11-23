package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dto.exportDto.CarExportDto;
import com.example.cardealerxml.models.dto.exportDto.CarExportRootDto;
import com.example.cardealerxml.models.dto.exportDto.carAndParts.CarDto;
import com.example.cardealerxml.models.dto.exportDto.carAndParts.CarsAndPartsRootDto;
import com.example.cardealerxml.models.entities.Car;
import com.example.cardealerxml.repositories.CarRepository;
import com.example.cardealerxml.services.CarService;
import com.example.cardealerxml.utills.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.util.List;
import static com.example.cardealerxml.constants.FilePath.*;


@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;


    @Autowired
    public CarServiceImpl(CarRepository carRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;

    }

    @Override
    public void seedCars(List<Car> cars) {
        if (carRepository.count() == 0){
            carRepository.saveAll(cars);
        }
    }

    @Override
    public void getCarsMakeFrom(String makeBy) throws JAXBException {

        List<CarExportDto> carExportDtos = carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(makeBy)
                .stream()
                .map(car -> modelMapper.map(car, CarExportDto.class))
                .toList();
        CarExportRootDto carExportRootDto = new CarExportRootDto(carExportDtos);

        xmlParser.writeToFile(EXPORT_PATH + TOYOTA_CARS_FILE, carExportRootDto);

    }

    @Override
    public void getCarsWithTheirParts() throws JAXBException {
        List<CarDto> CarDto = carRepository.findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .toList();
        CarsAndPartsRootDto carsAndPartsRootDto = new CarsAndPartsRootDto(CarDto);
        xmlParser.writeToFile(EXPORT_PATH + CARS_AND_PARTS_FILE, carsAndPartsRootDto);

    }

}
