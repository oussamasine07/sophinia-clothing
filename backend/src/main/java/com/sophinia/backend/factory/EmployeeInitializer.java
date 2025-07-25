package com.sophinia.backend.factory;


import com.sophinia.backend.model.Employee;
import com.sophinia.backend.model.User;
import com.sophinia.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EmployeeInitializer {
    @Bean
    CommandLineRunner createEmmployee (UserRepository userRepository, PasswordEncoder encoder) {

        return args -> {
            if (userRepository.findByEmail("assia@gmail.com").isEmpty()) {
                User newUser = new Employee();

                newUser.setFirstName("assia");
                newUser.setLastName("sine");
                newUser.setEmail("assia@gmail.com");
                newUser.setPassword(encoder.encode("123456"));

                userRepository.save(newUser);
            } else {
                System.out.println("this employee already exists");
            }
        };

    }
}
