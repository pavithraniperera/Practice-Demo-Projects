package com.example.restcrud.DaoWithJPA;

import com.example.restcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //no need to write any implementation by manually
}
