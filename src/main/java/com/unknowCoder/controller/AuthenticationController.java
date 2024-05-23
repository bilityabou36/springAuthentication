package com.unknowCoder.controller;

import com.unknowCoder.dto.LoginResponDto;
import com.unknowCoder.dto.RegistrationDto;
import com.unknowCoder.model.UserApplication;
import com.unknowCoder.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/register")
    public ResponseEntity<UserApplication> registerUser(@RequestBody RegistrationDto body) {
        UserApplication user = authenticationServices.registerUser(body.getUsername(), body.getPassword());
        return ResponseEntity.ok(user);  // Returns HTTP 200 with the user details
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponDto> loginUser(@RequestBody RegistrationDto body) {
        LoginResponDto response = authenticationServices.loginUser(body.getUsername(), body.getPassword());
        if (response.getUser() != null) {
            return ResponseEntity.ok(response);  // Returns HTTP 200 with the login details
        } else {
            return ResponseEntity.badRequest().body(response);  // Returns HTTP 400 if login fails
        }
    }

}

