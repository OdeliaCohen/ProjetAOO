package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService; 
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
        return accountService.findAllAccounts(); 
    }

    @GetMapping("/bddProfile")
    public List<Profile> bddProfile(){
        return profileService.findAllProfiles(); 
    }
    @GetMapping("/bddExpensesCategory")
    public Iterable<ExpensesCategory> bddExpensesCategory(){
        return expensesCService.findAllCategories(); 
    }

    @GetMapping("/bddExpenses")
public ResponseEntity<List<Expenses>> bddExpenses() {
    List<Expenses> expensesList = expensesService.findAllExpenses(); 
    if (expensesList.isEmpty()) {
        return ResponseEntity.noContent().build(); 
    }
    return ResponseEntity.ok(expensesList); 
}


}




