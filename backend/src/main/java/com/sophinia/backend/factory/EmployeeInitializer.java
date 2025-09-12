package com.sophinia.backend.factory;


import com.sophinia.backend.model.Employee;
import com.sophinia.backend.model.User;
import com.sophinia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Configuration
public class EmployeeInitializer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeInitializer.class);

    @Value("${secrets.initializer.employee_password}")
    private String password;

    @Bean
    CommandLineRunner createEmmployee (UserRepository userRepository, PasswordEncoder encoder) {

        return args -> {
            if (userRepository.findByEmail("kawtar@gmail.com").isEmpty()) {
                User newUser = new Employee();

                newUser.setFirstName("kawtar");
                newUser.setLastName("sine");
                newUser.setEmail("kawtar@gmail.com");
                newUser.setPassword(encoder.encode(password));

                userRepository.save(newUser);
            } else {
                logger.info("this employee already exists");
            }
        };

    }
}
