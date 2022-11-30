package hiberspring.service.impl;

import hiberspring.domain.dtos.EmployeeRootDto;
import hiberspring.domain.entities.Employee;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, XmlParser xmlParser, ValidationUtil validationUtil,
                               ModelMapper modelMapper, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository) {
        this.employeeRepository = employeeRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(PATH_EMPLOYEES));
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(PATH_EMPLOYEES, EmployeeRootDto.class)
                .getEmployees()
                .stream()
                .filter(employeeDto -> {
                    boolean isValid = validationUtil.isValid(employeeDto);
                    if (branchRepository.findByName(employeeDto.getBranch()).isEmpty()
                            || employeeRepository.findByCard_Number(employeeDto.getCard()).isPresent()
                            || (employeeCardRepository.findByNumber(employeeDto.getCard()).isEmpty())) {
                        isValid = false;
                    }

                    sb.append(isValid ? String.format(SUCCESSFUL_IMPORT_MESSAGE,
                            "Employee", employeeDto.getFirstName() + employeeDto.getLastName())
                            : INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    return isValid;
                })
                .map(employeeDto -> {
                    Employee employee = modelMapper.map(employeeDto, Employee.class);
                    employee.setBranch(branchRepository.findByName(employeeDto.getBranch()).get());
                    employee.setCard(employeeCardRepository.findByNumber(employeeDto.getCard()).get());
                    return employee;
                })
                .forEach(employeeRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String exportProductiveEmployees() {
        return employeeRepository.findByBranch_Products()
                .stream()
                .map(Employee::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
