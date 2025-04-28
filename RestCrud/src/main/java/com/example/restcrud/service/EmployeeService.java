package com.example.restcrud.service;

import com.example.restcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
}
