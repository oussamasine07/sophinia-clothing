package com.sophinia.backend.factory;


import com.sophinia.backend.model.Admin;
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
public class AdminInitializer {

    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    @Value("${secrets.initializer.admin_password}")
    private String password;

    @Bean
    CommandLineRunner createAdmin (UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if  (userRepository.findByEmail("sine@gmail.com").isEmpty()) {
                User newUser = new Admin();

                newUser.setFirstName("oussama");
                newUser.setLastName("sine");
                newUser.setEmail("sine@gmail.com");
                newUser.setPassword(encoder.encode(password));

                userRepository.save(newUser);

            } else {
                logger.info("this admin already exists");
            }
        };
    }

}
