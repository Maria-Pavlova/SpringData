package com.example.automappingobjects_lab.projection;

import com.example.automappingobjects_lab.projection.dto.CustomDto;
import com.example.automappingobjects_lab.projection.dto.Employee3DTO;
import com.example.automappingobjects_lab.projection.entity.Employee3;
import com.example.automappingobjects_lab.projection.services.Employee3Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private Employee3Service employee3Service;


    public ConsoleRunner(Employee3Service employee3Service) {
        this.employee3Service = employee3Service;
    }

    @Override
    public void run(String... args) throws Exception {

        // this.persist();
                ModelMapper mapper = new ModelMapper();

//        Optional<Employee3> managerOpt = this.employee3Service.findOneById(2);
//        Employee3 manager = managerOpt.get();
//        Employee3DTO dto = mapper.map(manager, Employee3DTO.class);
//        System.out.println(dto);



                this.employee3Service.findByBirthdateBefore(1990)
                        .forEach(System.out::println);


        List<Employee3> all = this.employee3Service.findAll();

        TypeMap<Employee3, CustomDto> typeMap = mapper.createTypeMap(Employee3.class, CustomDto.class);

        // dont work:
       // typeMap.addMappings(m->m.map( source -> source.getManager() == null ? 0 : source.getManager().getLastName().length(), CustomDto::setManagerLastNameLength));

        all.stream().map(e->typeMap.map(e))
                .forEach(System.out::println);

//        all.stream()
//                .map(e -> mapper.map(e, Employee3DTO.class))
//                .forEach(System.out::println);
    }

     private void persist() {
        Employee3 manager = new Employee3("Mrs", "Manager", BigDecimal.TEN, LocalDate.now(), "Sofia", null);
        Employee3 employee1 = new Employee3("First", "Employee1", BigDecimal.ONE, LocalDate.now(), "Sofia", manager);
        Employee3 employee2 = new Employee3("Second", "Employee2", BigDecimal.ONE, LocalDate.now(), "Sofia", manager);

        //  this.employee3Service.save(employee1);
        this.employee3Service.save(employee2);
    }
}
