package com.example.demo.controller;

import com.example.demo.model.Profile;
import com.example.demo.service.ExpensesCService;
import com.example.demo.service.ExpensesService;
import com.example.demo.service.ProfileService;
import com.example.demo.model.ExpensesCategory;
import com.example.demo.Repository.ExpensesCRepository; // Assurez-vous que le chemin d'importation est correct
import com.example.demo.Repository.ProfileRepository; // Assurez-vous que le chemin d'importation est correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChartController {

    private final ExpensesService expensesService;
    private final ExpensesCService expensesCService;
    private final ProfileService profileService;

    public ChartController(ExpensesService expensesService, ExpensesCService expensesCService, ProfileService profileService) {
        this.expensesService = expensesService;
        this.expensesCService = expensesCService;
        this.profileService = profileService;
    }

    @GetMapping("/chart")
    public String chart(@RequestParam("id") Long id, @RequestParam("categoryName") String categoryName, Model model) {
        Profile profile = profileService.findProfileById(id);
    
        if (profile == null) {
            model.addAttribute("error", "Profil non trouvé.");
            return "error";
        }
    
        float profileBudget = profile.getProfileBudget();
        float dailyBudget = profileBudget / 7;
    
        List<ExpensesCategory> categories = new ArrayList<>();
        ExpensesCategory category = expensesCService.findCategoryByName(categoryName);
        if (category != null) {
            categories.add(category);
        }
    
        Map<String, Map<String, Float>> chartData = expensesService.generateWeeklyBudget(dailyBudget, categories);
    
        // Ajouter des attributs pour le type de profil et le budget de profil
        model.addAttribute("profileType", profile.getProfileType());
        model.addAttribute("profileBudget", profileBudget);
    
        model.addAttribute("chartData", chartData);
    
        return "chart";
    }
    
}