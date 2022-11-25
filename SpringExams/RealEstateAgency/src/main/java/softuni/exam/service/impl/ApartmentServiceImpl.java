package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentDto;
import softuni.exam.models.dto.ApartmentRootDto;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.FilePath.PATH_APARTMENTS;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.apartmentRepository = apartmentRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count()>0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(PATH_APARTMENTS));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        List<ApartmentDto> apartments = xmlParser.fromFile(PATH_APARTMENTS, ApartmentRootDto.class)
                .getApartments();
        apartments
                .stream()
                .filter(apartmentDto -> {
                    boolean isValid = validationUtil.isValid(apartmentDto);
                    if (apartmentRepository.findByAreaAndTownName(apartmentDto.getArea(),apartmentDto.getTownName()).isPresent()) {
                        isValid = false;
                    }
                    sb.append( isValid ? String.format("Successfully imported Apartment %s - %.2f",
                           apartmentDto.getApartmentType(), apartmentDto.getArea())
                           : "Invalid apartment").append(System.lineSeparator());
                   return isValid;
                })
                .map(apartmentDto -> {
                    Apartment apartment = modelMapper.map(apartmentDto, Apartment.class);
                   apartment.setTown( townRepository.findByTownName(apartmentDto.getTownName()).get());
                   return apartment;

                })
                .forEach(apartmentRepository::saveAndFlush);

        return sb.toString();
    }
}
