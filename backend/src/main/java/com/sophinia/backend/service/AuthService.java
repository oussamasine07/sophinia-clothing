package com.sophinia.backend.service;

import com.sophinia.backend.dto.mappingdto.AuthUserDTO;
import com.sophinia.backend.dto.validation.LoginValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.exception.PasswordIncorrectException;
import com.sophinia.backend.model.*;
import com.sophinia.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthService(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<Map<String, String>> loginUser (LoginValidationDTO loginValidationDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginValidationDTO.email(),
                                    loginValidationDTO.password()
                            )
                    );

            if (authentication.isAuthenticated()){
                AuthUserDTO authUser = this.getAuthenticatedUser(loginValidationDTO.email());

                String token = jwtService.generateJwtToken(authUser); 

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            }

            throw  new PasswordIncorrectException("Invalid credentials");
        }
        catch (AuthenticationException e ) {
            throw  new PasswordIncorrectException("Invalid credentials");
        }
    }


    public AuthUserDTO getAuthenticatedUser (String email ) {
        User authenticatedUser = userRepository.findByEmail( email )
                .orElseThrow(() -> new NotFoundException("User not found"));

        UserPrincipal principal = new UserPrincipal(authenticatedUser);
        return new AuthUserDTO(
                authenticatedUser.getId(),
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                principal.getAuthorities()  // correct authorities set here
        );

    }



}
