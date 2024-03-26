package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;


public interface SignRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByEmail(String email);
    
}
