package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.SignRepository;
import com.example.demo.model.Account;

@Service
public class AccountService {

    private final SignRepository signRepository;
    
    public AccountService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public boolean checkIfAccountExists(String email) {
        return signRepository.findByEmail(email).isPresent();
    }

    public void createAccount(Account account) {
        signRepository.save(account);
    }

    public List<Account> findAllAccounts() {
        return signRepository.findAll();
    }
       
}
