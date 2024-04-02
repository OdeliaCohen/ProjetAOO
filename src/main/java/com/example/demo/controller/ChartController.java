package com.example.demo.controller;

import com.example.demo.model.Profile;
import com.example.demo.model.ExpensesCategory;
import com.example.demo.Repository.ExpensesCRepository; // Assurez-vous que le chemin d'importation est correct
import com.example.demo.Repository.ProfileRepository; // Assurez-vous que le chemin d'importation est correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired // Ajout pour l'injection de dépendance
    private ExpensesCRepository expensesCRepository; // Correction du nom de la variable pour suivre la convention Java

    @GetMapping("/chart")
    public String showChart(Model model, @RequestParam(value = "id", defaultValue = "1") Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        List<ExpensesCategory> expensesCategories = expensesCRepository.findAll();
        
        if (profile != null) {
            model.addAttribute("profileBudget", profile.getProfileBudget());
            model.addAttribute("expensesCategories", expensesCategories);

        } else {
            // Gestion du cas où le profil n'est pas trouvé
        }
    
        return "chart";
    }
}






