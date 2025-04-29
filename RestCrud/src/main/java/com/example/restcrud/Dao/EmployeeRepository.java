package com.example.restcrud.Dao;

import com.example.restcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//localhost:8080/magic-api/members
//@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //no need to write any implementation by manually
}
