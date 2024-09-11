package com.pro.EverestTechnologies.controller;

import com.pro.EverestTechnologies.dto.ResponseMessage;
import com.pro.EverestTechnologies.model.Employee;
import com.pro.EverestTechnologies.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/csrf-token")
    public CsrfToken getcsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(new ResponseMessage("Employee is added"), HttpStatus.CREATED);
    }

    @PostMapping("/batchIntake")
    public ResponseEntity<ResponseMessage> saveEmployees(@RequestBody List<Employee> employees) {
        employeeService.saveEmployees(employees);
        return new ResponseEntity<>(new ResponseMessage("Employees have been added"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);  // Returns Employee when found
        } else {
            return new ResponseEntity<>(new ResponseMessage("Employee not found with ID " + id), HttpStatus.NOT_FOUND);  // Returns ResponseMessage when not found
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(new ResponseMessage("Employee is updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new ResponseMessage("Employee is deleted"), HttpStatus.NO_CONTENT);
    }
}
