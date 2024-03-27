package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.AccountService;
import com.example.demo.service.ProfileService;

import jakarta.servlet.http.HttpSession;

import com.example.demo.model.Account;
import com.example.demo.model.Profile;

@Controller
public class SignController {

    private AccountService accountService;
    private ProfileService profileService;

    public SignController(AccountService accountService , ProfileService profileService) {
        this.accountService = accountService;
        this.profileService = profileService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("account", new Account()); 
        return "connexion"; // Le nom du template sans l'extension .html
    }
    
    @PostMapping("/login")
    public String authenticate(@ModelAttribute Account account, Model model, HttpSession session) {
        if (accountService.authenticate(account.getEmail(), account.getPassword())) {
            Account loggedInAccount = accountService.getAccountByEmail(account.getEmail());
            model.addAttribute("account", loggedInAccount);
            session.setAttribute("account", loggedInAccount);
            return "redirect:/accueil"; // Redirigez vers une page de succès ou une page d'accueil
        } else {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
            return "connexion"; // Retournez à la page de connexion avec un message d'erreur
        }
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

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }
    @PostMapping("/accueil")
    public String postProfile(@ModelAttribute Profile profile, @RequestParam("id") Long id, Model model) {
        // Récupérer l'Account à partir de l'ID et l'associer au Profile
        if (id != null) {
            Account account = accountService.findAccountById(id);
            if (account != null) {
                profile.setAccount(account); // Associer le profil au compte
                profileService.saveProfile(profile); // Sauvegarder le profil
            } else {
                model.addAttribute("error", "Compte non trouvé.");
                return "accueil";
            }
        } else {
            model.addAttribute("error", "ID de compte manquant.");
            return "accueil";
        }
        
        return "redirect:/accueil";
    }
    
}
