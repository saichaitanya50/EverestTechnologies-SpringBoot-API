package com.pro.EverestTechnologies.service;

import com.pro.EverestTechnologies.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);                        // Create/Update a single employee
    List<Employee> saveEmployees(List<Employee> employees);          // Create/Update multiple employees
    List<Employee> getAllEmployees();                                // Retrieve all employees
    Optional<Employee> getEmployeeById(Long id);                     // Retrieve an employee by ID
    Employee updateEmployee(Long id, Employee employeeDetails);       // Update an employee by ID
    void deleteEmployee(Long id);                                    // Delete an employee by ID
}

