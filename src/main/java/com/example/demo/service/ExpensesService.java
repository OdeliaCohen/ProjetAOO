package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    public ExpensesService(ExpensesRepository expensesRepository, ExpensesCRepository expensesCRepository) {
        this.expensesRepository = expensesRepository;
        this.expensesCRepository = expensesCRepository;
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
    public float calculateDailyExpense(float budget, List<ExpensesCategory> categories) {
        // Calculer le budget par jour en fonction du nombre de catégories
        float dailyBudget = budget / categories.size();
    
        // Calculer les dépenses totales par jour
        float totalDailyExpense = 0;
        for (ExpensesCategory category : categories) {
            // Récupérer les dépenses pour la catégorie
            List<Expenses> categoryExpenses = expensesRepository.findByCategory(category);
            for (Expenses expense : categoryExpenses) {
                totalDailyExpense += expense.getAmountSpent();
            }
        }
    
        return totalDailyExpense;
    }
    
}    