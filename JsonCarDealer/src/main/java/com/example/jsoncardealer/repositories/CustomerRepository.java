package com.example.jsoncardealer.repositories;

import com.example.jsoncardealer.models.dtoExport.CustomersTotalSalesDto;
import com.example.jsoncardealer.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c ORDER BY c.birthDate, c.isYoungDriver DESC")
    List<Customer> findAllByOrderByBirthDateAscIsYoungDriverAsc();


    @Query("SELECT new com.example.jsoncardealer.models.dtoExport.CustomersTotalSalesDto" +
            "(c.name, count(s), sum(p.price*(1.0-s.discountPercentage/100))) " +
            "FROM Customer c JOIN c.sales s JOIN s.car car JOIN car.parts p " +
            "GROUP BY c " +
            "ORDER BY sum(p.price*(1-s.discountPercentage/100)) desc, count(s) desc")
    List<CustomersTotalSalesDto> findCustomersBySales();
}
