package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService; // Assurez-vous que c'est bien importé
import com.example.demo.service.ProfileService;
import com.example.demo.model.Account;
import com.example.demo.model.Profile;


@RestController
public class BddController {

    private AccountService accountService;
    private ProfileService profileService;

    @Autowired
    public BddController(AccountService accountService, ProfileService profileService) {
        this.accountService = accountService;
        this.profileService = profileService;
    }



    
    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        return accountService.findAllAccounts(); // Assurez-vous que cette méthode existe dans AccountService
    }

    @GetMapping("/bddProfile")
    public List<Profile> bddProfile(){
        return profileService.findAllProfiles(); // Assurez-vous que cette méthode existe dans ProfileService
    }

}
