package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Repository.SignRepository;
import com.example.demo.model.Account;

@RestController
public class BddController {

    private final SignRepository signRepository;

    @Autowired
    public BddController(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

   @PostMapping("/signup")
public String createAccount(@ModelAttribute Account newAccount) {
    signRepository.save(newAccount);
    return "redirect:/login"; // ou la page de succès de l'inscription
}


    // Votre endpoint existant pour obtenir tous les comptes
    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        return signRepository.findAll();
    }
    
    // Les autres méthodes du contrôleur...
}
