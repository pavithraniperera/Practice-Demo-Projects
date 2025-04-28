package com.example.restcrud.Dao;

import com.example.restcrud.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll();
    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
