package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService; // Assurez-vous que c'est bien importé
import com.example.demo.service.ExpensesCService;
import com.example.demo.service.ExpensesService;
import com.example.demo.service.ProfileService;
import com.example.demo.model.Account;
import com.example.demo.model.Expenses;
import com.example.demo.model.ExpensesCategory;
import com.example.demo.model.Profile;


@RestController
public class BddController {

    private AccountService accountService;
    private ProfileService profileService;
    private ExpensesCService expensesCService;
    private ExpensesService expensesService;

    @Autowired
    public BddController(AccountService accountService, ProfileService profileService, ExpensesCService expensesCService, ExpensesService expensesService) {
        this.accountService = accountService;
        this.profileService = profileService;
        this.expensesService = expensesService;
        this.expensesCService = expensesCService;
    }



    
    @GetMapping("/bddAccount")
    public List<Account> bddAccount(){
        return accountService.findAllAccounts(); // Assurez-vous que cette méthode existe dans AccountService
    }

    @GetMapping("/bddProfile")
    public List<Profile> bddProfile(){
        return profileService.findAllProfiles(); // Assurez-vous que cette méthode existe dans ProfileService
    }
    @GetMapping("/bddExpensesCategory")
    public Iterable<ExpensesCategory> bddExpensesCategory(){
        return expensesCService.findAllCategories(); // Assurez-vous que cette méthode existe dans ExpensesCService
    }

     @GetMapping("/bddExpenses")
    public List<Expenses> bddExpenses(){
        Profile profile = profileService.findAllProfiles().get(0); // Assurez-vous que cette méthode existe dans ProfileService
        expensesService.calculateAndSaveExpensesFromBudget(profile); // Assurez-vous que cette méthode existe dans ExpensesService
        return expensesService.findAllExpenses(); // Cette ligne retournera la liste des dépenses
    }

    @GetMapping("/calculateDailyExpense")
    public Map<String, Map<String, Float>> calculateDailyExpense() {
        Profile profile = profileService.findAllProfiles().get(0);
        Iterable<ExpensesCategory> expensesCategoriesIterable = expensesCService.findAllCategories();
        List<ExpensesCategory> expensesCategoriesList = new ArrayList<>();
        expensesCategoriesIterable.forEach(expensesCategoriesList::add);
        float budget = profile.getProfileBudget();
        return expensesService.calculateWeeklyBudget(budget, expensesCategoriesList);
    }
}
  




