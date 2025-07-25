package com.sophinia.backend.controller.auth;


import com.sophinia.backend.dto.validation.LoginValidationDTO;
import com.sophinia.backend.model.Admin;
import com.sophinia.backend.model.Employee;
import com.sophinia.backend.model.User;
import com.sophinia.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AdminAuthController {

    private final AuthService authService;

    public AdminAuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody LoginValidationDTO loginValidationDTO) {

        User user = new Admin();

        user.setEmail( loginValidationDTO.email() );
        user.setPassword( loginValidationDTO.password() );

        return authService.loginUser( user );
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> loginEmployee(@Valid @RequestBody LoginValidationDTO loginValidationDTO) {

        User user = new Employee();

        user.setEmail( loginValidationDTO.email() );
        user.setPassword( loginValidationDTO.password() );

        return authService.loginUser( user );
    }



}
