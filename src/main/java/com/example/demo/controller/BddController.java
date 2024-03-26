package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService; // Assurez-vous que c'est bien importé
import com.example.demo.model.Account;

@RestController
public class BddController {

    private final AccountService accountService;

    @Autowired
    public BddController(AccountService accountService) {
        this.accountService = accountService;
    }


    
    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        return accountService.findAllAccounts(); // Assurez-vous que cette méthode existe dans AccountService
    }
}
