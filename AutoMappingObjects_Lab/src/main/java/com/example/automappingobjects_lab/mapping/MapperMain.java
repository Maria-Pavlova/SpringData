package com.example.automappingobjects_lab.mapping;

import com.example.automappingobjects_lab.mapping.dto.ManagerDTO;
import com.example.automappingobjects_lab.mapping.entities.Address;
import com.example.automappingobjects_lab.mapping.entities.Employee;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {

        ModelMapper modelMapper = new ModelMapper();

        // 01
//        Address address = new Address("Street", 12,"Sofia","Bulgaria");
//        Employee source = new Employee("first","last", BigDecimal.TEN, LocalDate.now(), address);
//        EmployeeDTO dto = modelMapper.map(source, EmployeeDTO.class);
//        System.out.println(dto);


        // 02
        Address address = new Address("Street", 12,"Sofia","Bulgaria");
        Employee manager = new Employee("Manager","ManLast", BigDecimal.TEN,
                LocalDate.now(), address, true);
        Employee first = new Employee("firstEmpl","last1", BigDecimal.ONE,
                LocalDate.now(), address, true);
        Employee second = new Employee("secondEmpl","last2", BigDecimal.ZERO,
                LocalDate.now(), address, true);
        manager.addWorker(first);
        manager.addWorker(second);

        ManagerDTO dto = modelMapper.map(manager, ManagerDTO.class);
        System.out.println(dto);



    }
}
