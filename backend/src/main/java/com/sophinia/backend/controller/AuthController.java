package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.LoginValidationDTO;
import com.sophinia.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody LoginValidationDTO loginValidationDTO) {
        return authService.loginUser( loginValidationDTO );
    }



}
