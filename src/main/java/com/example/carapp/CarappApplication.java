package com.example.carapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.carapp.model.UserRole;
import com.example.carapp.repository.UserRoleRepository;

@SpringBootApplication
public class CarappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarappApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UserRoleRepository userRoleRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRoleRepository.findByUsername("admin").isEmpty()) {
                UserRole admin = new UserRole();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                userRoleRepository.save(admin);
            }

            if (userRoleRepository.findByUsername("user").isEmpty()) {
                UserRole user = new UserRole();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRole("USER");
                userRoleRepository.save(user);
            }
        };
    }
}
