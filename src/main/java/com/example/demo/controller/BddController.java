package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
public ResponseEntity<List<Expenses>> bddExpenses() {
    List<Expenses> expensesList = expensesService.findAllExpenses(); // This line fetches all expenses from the database
    if (expensesList.isEmpty()) {
        return ResponseEntity.noContent().build(); // Returns a 204 No Content if the list is empty
    }
    return ResponseEntity.ok(expensesList); // Returns a 200 OK with the list of expenses
}


}




