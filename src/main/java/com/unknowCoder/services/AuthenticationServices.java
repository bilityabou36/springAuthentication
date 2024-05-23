
package com.unknowCoder.services;

import com.unknowCoder.dto.LoginResponDto;
import com.unknowCoder.model.Role;
import com.unknowCoder.model.UserApplication;
import com.unknowCoder.repository.RoleRepository;
import com.unknowCoder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public UserApplication registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> userRole = roleRepository.findByAuthority("USER");
        if (!userRole.isPresent()) {
            throw new IllegalStateException("User role not found");
        }
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole.get());
        return userRepository.save(new UserApplication(username, encodedPassword, authorities));
    }

    public LoginResponDto loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenService.generateJwt(authentication);
            return new LoginResponDto(userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalStateException("User not found")), token);
        } catch (AuthenticationException e) {
            return new LoginResponDto(null, "Invalid credentials or user not found");
        }
    }
}
