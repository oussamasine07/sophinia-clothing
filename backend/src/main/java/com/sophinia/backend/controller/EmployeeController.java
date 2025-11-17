package com.sophinia.backend.controller;

import com.sophinia.backend.dto.request.EmployeeUpdateValidationDTO;
import com.sophinia.backend.dto.request.EmployeeValidationDTO;
import com.sophinia.backend.model.Employee;
import com.sophinia.backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController (
        final EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> index () {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> show ( @PathVariable Long id ) {
        return employeeService.getEmployeeById( id );
    }

    @PostMapping
    public ResponseEntity<Employee> create ( @RequestBody @Valid EmployeeValidationDTO employeeValidationDTO) {
        return employeeService.createEmployee( employeeValidationDTO );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update ( @RequestBody @Valid EmployeeUpdateValidationDTO employeeUpdateValidationDTO, @PathVariable Long id ) {
        return employeeService.updateEmployee( employeeUpdateValidationDTO, id );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Long id) {
        return null;
    }

    @PutMapping("/update-profile")
    public ResponseEntity<Employee> updateProfile () {
        return null;
    }

}




















