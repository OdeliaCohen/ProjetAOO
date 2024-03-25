package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;


public interface SignRepository extends JpaRepository<Account, Long>{
    
}
