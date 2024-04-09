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

    private void createAndSaveExpense(float budget, float dailyBudget, float dailyBudgetPerCategory, ExpensesCategory category) {
        Expenses expense = new Expenses();
        expense.setAmountToSpend(budget);
        expense.setAmountSpent(0);
        expense.setSpendDay(new Date());  // Adjust the date as necessary
        expense.setAmountSpentDay(dailyBudget);
        expense.setAmountSpentPerCategory(dailyBudgetPerCategory);
        expense.setCategory(category);
        expensesRepository.save(expense);
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
        float dailyBudgetPerCategory = dailyBudget /5; 
    
        Map<String, Map<String, Float>> weeklyBudget = new LinkedHashMap<>();
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednsday", "Thursday", "Friday", "Saturday", "Sunday");
    
        // Create and save expenses only once per category
        for (ExpensesCategory category : categories) {
            createAndSaveExpense(budget, dailyBudget, dailyBudgetPerCategory, category);
        }
    
        for (String day : daysOfWeek) {
            Map<String, Float> dailyBudgets = new HashMap<>();
            for (ExpensesCategory category : categories) {
                dailyBudgets.put(category.getCategoryName(), dailyBudgetPerCategory);
            }
            weeklyBudget.put(day, dailyBudgets);
        }
        return weeklyBudget;
    }
    

    @Transactional
public void updateSpending(Float amountSpentDay, Float amountSpentPerCategory) {
    List<Expenses> expensesList = findAllExpenses();
    for (Expenses expense : expensesList) {
        expense.setAmountSpentDay(amountSpentDay);
        expense.setAmountSpentPerCategory(amountSpentPerCategory);
        saveExpenses(expense);
    }

    // Recalculate the weekly budget with the new values
    calculateWeeklyBudget(amountSpentDay * 7, expensesCService.findAllCategories());
}


    


   
}    