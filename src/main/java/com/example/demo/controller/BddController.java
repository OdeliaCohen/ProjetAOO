package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.SignRepository;
import com.example.demo.model.Account;

@RestController
public class BddController {

    private SignRepository signRepository;

    @Autowired
    public BddController(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        Account account1 = new Account();
        signRepository.save(account1);
        return signRepository.findAll();
    }
    
}
