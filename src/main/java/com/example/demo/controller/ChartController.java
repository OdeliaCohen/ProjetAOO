package com.example.demo.controller;
import com.example.demo.model.Profile;
import com.example.demo.service.ExpensesCService;
import com.example.demo.service.ExpensesService;
import com.example.demo.service.ProfileService;
import com.example.demo.model.ExpensesCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
        float dailyBudget = profileBudget;
    
        List<ExpensesCategory> categories = new ArrayList<>();
        ExpensesCategory category = expensesCService.findCategoryByName(categoryName);
        if (category != null) {
            categories.add(category);
        }
        Map<String, Map<String, Float>> chartData = expensesService.generateWeeklyBudget(dailyBudget, categories);
        model.addAttribute("profileType", profile.getProfileType());
        model.addAttribute("profileBudget", profileBudget);
    
        model.addAttribute("chartData", chartData);
    
        return "chart"; 
    }

    @PostMapping("/updateSpending")
public String updateSpending(@RequestParam("amountSpentDay") Float amountSpentDay,
                             @RequestParam("amountSpentPerCategory") Float amountSpentPerCategory,
                             Model model) {
    Profile profile = profileService.findAllProfiles().get(0);
    if (profile == null) {
        model.addAttribute("error", "Profile not found.");
        return "error";
    }

    try {
        expensesService.updateSpending(amountSpentDay, amountSpentPerCategory);
        float newTotalBudget = amountSpentDay * 7; 
        profile.setProfileBudget(newTotalBudget);
        profileService.saveProfile(profile);
        List<ExpensesCategory> categories = expensesCService.findAllCategories();
        Map<String, Map<String, Float>> chartData = expensesService.generateWeeklyBudget(newTotalBudget, categories);
        model.addAttribute("profileType", profile.getProfileType());
        model.addAttribute("profileBudget", newTotalBudget);
        model.addAttribute("chartData", chartData);
        return "chart";
    } catch (Exception e) {
        model.addAttribute("error", "Error updating budget: " + e.getMessage());
        return "error";
    }
}



}

    
    

   
    
