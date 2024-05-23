package com.unknowCoder.repository;

import com.unknowCoder.model.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Integer> {

    Optional<UserApplication> findByUsername(String username);
}
