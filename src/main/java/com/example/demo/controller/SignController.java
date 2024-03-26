package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.service.AccountService;
import com.example.demo.model.Account;

@Controller
public class SignController {

    private final AccountService accountService;

    public SignController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String login() {
        return "connexion"; // Le nom du template sans l'extension .html
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("account", new Account());
        return "inscription"; // Le nom du template sans l'extension .html
    }

    @PostMapping("/signup") // permet de créer un compte
    public String registerAccount(@ModelAttribute Account account, Model model) {
        if (accountService.checkIfAccountExists(account.getEmail())) {
            model.addAttribute("error", "Un compte existe déjà avec cet email.");
            return "inscription";
        }
       if (!account.getPassword().equals(account.getRepeatPassword())) {
        model.addAttribute("error", "Les mots de passe ne correspondent pas.");
        return "inscription";
        }
       
        accountService.createAccount(account);
        return "redirect:/login"; // ou la page de succès de l'inscription
    }

    // Si vous voulez également gérer la déconnexion
    @GetMapping("/logout")
    public String logout() {
        return "connexion";
    }

}
