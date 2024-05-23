package com.unknowCoder;

import com.unknowCoder.model.Role;
import com.unknowCoder.model.UserApplication;
import com.unknowCoder.repository.RoleRepository;
import com.unknowCoder.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoAuthenticatedBackedApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAuthenticatedBackedApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Check if "ADMIN" role exists
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

			// Create and save roles
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			// Create and save the admin user with the "ADMIN" role
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			UserApplication admin = new UserApplication("admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);
		};
	}
}
