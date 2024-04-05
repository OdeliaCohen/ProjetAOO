package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Repository.ExpensesCRepository;
import com.example.demo.Repository.ExpensesRepository;
import com.example.demo.model.Expenses;
import com.example.demo.model.ExpensesCategory;
import com.example.demo.model.Profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final ExpensesCRepository expensesCRepository;
    private final ProfileService profileService; // Ajout du ProfileService
    private ExpensesCService expensesCService;

    public ExpensesService(ExpensesRepository expensesRepository, ExpensesCRepository expensesCRepository, ProfileService profileService, ExpensesCService expensesCService) {
        this.expensesRepository = expensesRepository;
        this.expensesCRepository = expensesCRepository;
        this.profileService = profileService; 
        this.expensesCService = expensesCService;
    
    }
    public void saveExpenses(Expenses expenses) {
        expensesRepository.save(expenses);
    }

    public List<Expenses> findAllExpenses() {
        return expensesRepository.findAll();
    }
    @Transactional
    public List<Expenses> calculateAndSaveExpensesFromBudget(Profile profile) {
        List<Expenses> expensesList = new ArrayList<>();
    
        // Récupérer toutes les catégories
        List<ExpensesCategory> categories = expensesCRepository.findAll();
        
        // Vérifier si des catégories ont été trouvées
        if (!categories.isEmpty()) {
            // Calculer le budget par catégorie
            float budgetPerCategory = profile.getProfileBudget() / categories.size();
    
            // Parcourir les catégories
            for (ExpensesCategory category : categories) {
                Expenses expense = new Expenses();
                expense.setAmountToSpend(budgetPerCategory);
                expense.setAmountSpent(0);
                expense.setSpendDay(new Date());
                expense.setCategory(category);
        
                expensesRepository.save(expense);
                expensesList.add(expense);
            }
        } else {
            System.out.println("Aucune catégorie trouvée.");
        }
    
        return expensesList;
    }
    @Transactional
    public Map<String, Map<String, Float>> calculateWeeklyBudget(float budget, List<ExpensesCategory> expensesCategoriesList) {
        // Récupérer le premier profil et toutes les catégories
        Profile profile = profileService.findAllProfiles().get(0);
        List<ExpensesCategory> categories = expensesCService.findAllCategories();

        return generateWeeklyBudget(profile.getProfileBudget(), categories);
    }

    public Map<String, Map<String, Float>> generateWeeklyBudget(float budget, List<ExpensesCategory> categories) {
        int daysInWeek = 7;
        float dailyBudget = budget / daysInWeek;
        float dailyBudgetPerCategory = dailyBudget/5; // 5 catégories obligatoires

        Map<String, Map<String, Float>> weeklyBudget = new LinkedHashMap<>();
        List<String> daysOfWeek = Arrays.asList("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi","Samedi", "Dimanche");

        for (String day : daysOfWeek) {
            Map<String, Float> dailyBudgets = new HashMap<>();
            for (ExpensesCategory category : categories) {
                dailyBudgets.put(category.getCategoryName(), dailyBudgetPerCategory);
            }
            weeklyBudget.put(day, dailyBudgets);
        }

        return weeklyBudget;
    }
}    