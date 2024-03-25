package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Repository.SignRepository;
import com.example.demo.model.Account;



@Controller 
public class SignController {

    private final SignRepository signRepository;

    @Autowired
    public SignController(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        Account account1 = new Account();
        signRepository.save(account1);
        return signRepository.findAll();
    }

    @GetMapping("/login")
    public String login() {
        return "connexion"; // Le nom du template sans l'extension .html
    }

    // Si vous voulez également gérer la déconnexion
    @GetMapping("/logout")
    public String logout() {
        return "connexion";
    }

    
}