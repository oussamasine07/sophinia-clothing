package com.sophinia.backend.service;

import com.sophinia.backend.dto.request.EmployeeUpdateValidationDTO;
import com.sophinia.backend.dto.request.EmployeeValidationDTO;
import com.sophinia.backend.dto.request.UpdateEmployeeProfileValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Employee;
import com.sophinia.backend.repository.EmployeeRepository;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;

    public EmployeeService (
            final EmployeeRepository employeeRepository,
            final JwtService jwtService
    ) {
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
    }

    public ResponseEntity<List<Employee>> getEmployees () {
        List<Employee> employees = employeeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    public ResponseEntity<Employee> getEmployeeById ( Long id ) {
        Employee employee = employeeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("unfound employee"));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    public ResponseEntity<Employee> createEmployee (EmployeeValidationDTO employeeValidationDTO) {

        System.out.println(employeeValidationDTO.toString());

        // take employee information
        Employee employee = new Employee();
        employee.setFirstName(employeeValidationDTO.firstName());
        employee.setLastName(employeeValidationDTO.lastName());
        employee.setEmail(employeeValidationDTO.email());

        // hash the password for employee
        employee.setPassword(encoder.encode( employeeValidationDTO.password() ));

        Employee savedEmployee = employeeRepository.save( employee );

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    public ResponseEntity<Employee> updateEmployee (EmployeeUpdateValidationDTO employeeUpdateValidationDTO, Long id) {
        Employee employee = employeeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update Unfound employee"));

        employee.setFirstName(employeeUpdateValidationDTO.firstName());
        employee.setLastName(employeeUpdateValidationDTO.lastName());
        employee.setEmail(employeeUpdateValidationDTO.email());

        if (!employeeUpdateValidationDTO.password().equals("")) {
            employee.setPassword(encoder.encode( employeeUpdateValidationDTO.password() ));
        }

        Employee savedEmployee = employeeRepository.save( employee );

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

    public ResponseEntity<Employee> updateEmployeeProfile (
            UpdateEmployeeProfileValidationDTO updateEmployeeProfileValidationDTO,
            String headerToken
    ) {

        String token = headerToken.substring(7);
        System.out.println(headerToken);
        // decrypt token
        Claims claims = jwtService.extractAllClaims( token );
        Long id = claims.get("id", Long.class);

        Employee employee = employeeRepository.findById( id )
                .orElseThrow(() -> new NotFoundException("you can't update this profile"));

        employee.setFirstName(updateEmployeeProfileValidationDTO.firstName());
        employee.setLastName(updateEmployeeProfileValidationDTO.lastName());
        employee.setEmail(updateEmployeeProfileValidationDTO.email());
        employee.setPhone(updateEmployeeProfileValidationDTO.phone());
        employee.setAddress(updateEmployeeProfileValidationDTO.address());
        employee.setCity(updateEmployeeProfileValidationDTO.city());
        employee.setPostalCode(updateEmployeeProfileValidationDTO.postalCode());
        employee.setCnssCode(updateEmployeeProfileValidationDTO.cnssCode());

        Employee savedEmployee = employeeRepository.save( employee );

        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }

}






























