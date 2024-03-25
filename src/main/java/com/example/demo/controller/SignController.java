package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class SignController {


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