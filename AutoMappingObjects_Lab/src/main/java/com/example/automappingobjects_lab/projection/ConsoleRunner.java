package com.example.automappingobjects_lab.projection;

import com.example.automappingobjects_lab.projection.dto.EmployeeDTO;
import com.example.automappingobjects_lab.projection.dto.ManagerDTO;
import com.example.automappingobjects_lab.projection.entity.Address;
import com.example.automappingobjects_lab.projection.entity.Employee;
import com.example.automappingobjects_lab.projection.services.AddressService;
import com.example.automappingobjects_lab.projection.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

        ModelMapper mapper = new ModelMapper();

        // 01
        Address address1 = new Address("Bulgaria 50", "Sofia", "Bulgaria");
        address1 = addressService.addAddress(address1);
        Employee employee1 = new Employee("Ivan", "Ivanov", BigDecimal.valueOf(3000), LocalDate.of(1980, 5, 9), address1);
        employee1 = employeeService.addEmployee(employee1);
        EmployeeDTO employeeDTO = mapper.map(employee1, EmployeeDTO.class);
        System.out.println(employeeDTO);


        // 02
        List<Address> addresses = List.of(
                new Address("Vitosha 50", "Sofia", "Bulgaria"),
                new Address("Orlovska 25", "Sofia", "Bulgaria"),
                new Address("Rakovski 111", "Sofia", "Bulgaria"),
                new Address("Dondukov 59", "Sofia", "Bulgaria"),
                new Address("Slivnitsa 27", "Sofia", "Bulgaria"),
                new Address("Svishtovska 46", "Sofia", "Bulgaria")
        );

        addresses = addresses.stream().map(addressService::addAddress).collect(Collectors.toList());

        List<Employee> employees = List.of(
                new Employee("Ivan", "Ivanov", BigDecimal.valueOf(3000), LocalDate.of(1980, 5, 8), addresses.get(0)),
                new Employee("Iva", "Ivanova", BigDecimal.valueOf(2600), LocalDate.of(1985, 2, 25), addresses.get(1)),
                new Employee("Peter", "Petrov", BigDecimal.valueOf(2250), LocalDate.of(1978, 8, 6), addresses.get(2)),
                new Employee("Diana", "Petrova", BigDecimal.valueOf(3200), LocalDate.of(1990, 9, 30), addresses.get(3)),
                new Employee("George", "Popov", BigDecimal.valueOf(1900), LocalDate.of(1989, 12, 15), addresses.get(4)),
                new Employee("Bill", "Tomov", BigDecimal.valueOf(2875), LocalDate.of(1998, 7, 26), addresses.get(5)),
                new Employee("Biliana", "Tomova", BigDecimal.valueOf(2875), LocalDate.of(1998, 1, 9), addresses.get(5))
        );

        employees = employees.stream().map(employeeService::addEmployee).collect(Collectors.toList());


        employees.get(1).setManager(employees.get(0));
        employees.get(2).setManager(employees.get(0));
        employees.get(4).setManager(employees.get(3));
        employees.get(5).setManager(employees.get(3));
        employees.get(6).setManager(employees.get(3));

        List<Employee> updated = employees.stream().map(employeeService::updateEmployee).toList();

        TypeMap<Employee, ManagerDTO> managerTypeMap = mapper.createTypeMap(Employee.class, ManagerDTO.class)
                .addMappings(m -> m.map(Employee::getSubordinates, ManagerDTO::setSubordinates));

        List<Employee> managers = employeeService.getAllManagers();
        List<ManagerDTO> managerDTOS = managers.stream().map(managerTypeMap::map).toList();
        managerDTOS.forEach(System.out::println);

        mapper.validate();


        // 03.

        System.out.println("-".repeat(60) + "\n");

        TypeMap employeeMap = mapper.getTypeMap(Employee.class, EmployeeDTO.class)
                .addMapping(src -> src.getManager().getLastName(), EmployeeDTO::setManagerLastName);

        List<Employee> byBirthdateBefore = this.employeeService.findByBirthdateBefore(1990);

        byBirthdateBefore.stream().map(employeeMap::map)
                .forEach(System.out::println);

    }
}
