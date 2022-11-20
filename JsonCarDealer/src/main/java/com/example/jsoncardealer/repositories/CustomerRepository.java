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


//    @Query("SELECT new com.example.jsoncardealer.models.dtoExport.CustomersTotalSalesDto( c.name, s.size, sum(p.price))" +
//            " FROM Customer c JOIN c.sales s JOIN Part p " +
//            "WHERE count(c.sales) > 0 GROUP BY c ORDER BY count (c.sales) desc")

    @Query("SELECT c FROM Customer c WHERE c.sales.size > 0 GROUP BY c")
    List<Customer> findCustomersBySales();
}
//