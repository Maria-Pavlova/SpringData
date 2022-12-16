package com.example.workshopnextleveltechnologiesmvc.service;

import com.example.workshopnextleveltechnologiesmvc.data.dtoExport.EmployeesAbove25Dto;
import com.example.workshopnextleveltechnologiesmvc.data.dtoImport.EmployeesRootDto;
import com.example.workshopnextleveltechnologiesmvc.data.entities.Employee;
import com.example.workshopnextleveltechnologiesmvc.data.entities.Project;
import com.example.workshopnextleveltechnologiesmvc.repositories.CompanyRepository;
import com.example.workshopnextleveltechnologiesmvc.repositories.EmployeeRepository;
import com.example.workshopnextleveltechnologiesmvc.repositories.ProjectRepository;
import com.example.workshopnextleveltechnologiesmvc.util.ValidationUtil;
import com.example.workshopnextleveltechnologiesmvc.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static com.example.workshopnextleveltechnologiesmvc.constants.FilePath.PATH_EMPLOYEES;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CompanyRepository companyRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository,
                           XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.companyRepository = companyRepository;
    }

    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(PATH_EMPLOYEES));
    }

    public void importEmployees() throws JAXBException, FileNotFoundException {
        xmlParser.xmlParse(PATH_EMPLOYEES, EmployeesRootDto.class)
                .getEmployees()
                .stream()
                .filter(employeeDto -> {
                    boolean isValid = validationUtil.isValid(employeeDto);
                    if (projectRepository.findByName(employeeDto.getProject().getName()).isEmpty()) {
                        isValid = false;
                    }
                    return isValid;
                })
                .map(employeeDto -> {
                    Employee employee = modelMapper.map(employeeDto, Employee.class);
                    Project project = projectRepository.findByName(employeeDto.getProject().getName()).get();
                    project.setCompany(companyRepository.findByName(employeeDto.getProject().getCompany().getName()).get());
                    employee.setProject(project);
                    return employee;
                })
                .map(employee-> employee.setProjectWithId(projectRepository.findByName(employee.getProject().getName()).get()))
                .forEach(employeeRepository::saveAndFlush);
    }

    public String getEmployeesOlderThan(int age){
       return this.employeeRepository.findByAgeGreaterThan(25)
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeesAbove25Dto.class))
                .map(EmployeesAbove25Dto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
