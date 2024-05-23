package com.unknowCoder.services;

import com.unknowCoder.model.Role;
import com.unknowCoder.model.UserApplication;
import com.unknowCoder.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in the user detail server ");

        return  userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}
