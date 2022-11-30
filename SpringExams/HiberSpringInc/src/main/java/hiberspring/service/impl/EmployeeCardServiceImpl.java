package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchDto;
import hiberspring.domain.dtos.EmployeeCardDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
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
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.employeeCardRepository = employeeCardRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean employeeCardsAreImported() {
        return employeeCardRepository.count()>0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return Files.readString(Path.of(PATH_EMPLOYEE_CARDS));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(this.readEmployeeCardsJsonFile(), EmployeeCardDto[].class))
                .filter(employeeCardDto -> {
                    boolean isValid = validationUtil.isValid(employeeCardDto);
                    if (employeeCardRepository.findByNumber(employeeCardDto.getNumber()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format(SUCCESSFUL_IMPORT_MESSAGE, "Employee Card",employeeCardDto.getNumber())
                            : INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    return isValid;
                })
                .map(employeeCardDto -> modelMapper.map(employeeCardDto, EmployeeCard.class))
                .forEach(employeeCardRepository::saveAndFlush);
        return sb.toString();
    }
}
