package com.sophinia.backend.service;

import com.sophinia.backend.dto.mappingDTO.AuthUserDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.exception.PasswordIncorrectException;
import com.sophinia.backend.mapper.AdminMapper;
import com.sophinia.backend.mapper.ClientMapper;
import com.sophinia.backend.mapper.EmployeeMapper;
import com.sophinia.backend.model.Admin;
import com.sophinia.backend.model.Client;
import com.sophinia.backend.model.Employee;
import com.sophinia.backend.model.User;
import com.sophinia.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final ClientMapper clientMapper;
    private final EmployeeMapper employeeMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthService(
            UserRepository userRepository,
            AdminMapper adminMapper,
            ClientMapper clientMapper,
            EmployeeMapper employeeMapper,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.adminMapper = adminMapper;
        this.clientMapper = clientMapper;
        this.employeeMapper = employeeMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> loginUser (User user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    user.getPassword()
                            )
                    );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateJwtToken(userDetails); // Must accept UserDetails

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);

//            if (authentication.isAuthenticated()){
//                AuthUserDTO authUser = this.getAuthenticatedUser(user.getEmail());
//
//                String token = jwtService.generateJwtToken(authUser);
//
//                Map<String, String> responseSuccess = new HashMap<>();
//                responseSuccess.put("token", token);
//
//                return new ResponseEntity<>(responseSuccess, HttpStatus.OK);
//
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                String token = jwtService.generateJwtToken(userDetails); // Must accept UserDetails
//
//                Map<String, String> response = new HashMap<>();
//                response.put("token", token);
//                return ResponseEntity.ok(response);
//            }
//
//            throw  new PasswordIncorrectException("Invalid credentials");
        }
        catch (AuthenticationException e ) {
            throw  new PasswordIncorrectException("Invalid credentials");
        }
    }


    public AuthUserDTO getAuthenticatedUser (String email ) {
        User authenticatedUser = userRepository.findByEmail( email )
                .orElseThrow(() -> new NotFoundException("User not found"));

        AuthUserDTO authUserDTO = null;
        switch (authenticatedUser.getClass().getSimpleName()) {
            case "Admin":
                authUserDTO = adminMapper.toDTO((Admin) authenticatedUser);
                break;
            case "Client":
                authUserDTO = clientMapper.toDTO((Client) authenticatedUser);
                break;
            case "Emloyee":
                authUserDTO = employeeMapper.toDTO((Employee) authenticatedUser);
                break;
        }
        return authUserDTO;
    }

}
