package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.passenger.PassengerDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import static softuni.exam.constants.FilePath.PATH_PASSENGERS;


@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, Gson gson, ValidationUtil validationUtil,
                                ModelMapper modelMapper, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PATH_PASSENGERS));
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readPassengersFileContent(), PassengerDto[].class))
                .filter(passengerDto -> {


                    boolean isValid = validationUtil.isValid(passengerDto);
                    if (passengerRepository.findByEmail(passengerDto.getEmail()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Passenger %s - %s",
                                    passengerDto.getLastName(), passengerDto.getEmail())
                                    : "Invalid agent")
                            .append(System.lineSeparator());
                    return isValid;
                }).map(passengerDto -> {
                            Passenger passenger = modelMapper.map(passengerDto, Passenger.class);
                            passenger.setTown(townRepository.findByName(passengerDto.getTown()).get());
                            return passenger;
                        }
                )
                .forEach(passengerRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return passengerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Passenger::getTicketsSize).reversed()
                        .thenComparing(Passenger::getEmail))
                .map(Passenger::toString).collect(Collectors.joining(System.lineSeparator()));

    }
}
