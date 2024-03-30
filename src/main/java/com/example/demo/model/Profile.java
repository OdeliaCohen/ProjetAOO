package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String profileType;
    private float profileBudget;

    @ManyToOne
    @JoinColumn(name = "account_id")
     @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ExpensesCategory> expensesCategories;

     public Profile() {
        this.expensesCategories = new ArrayList<>(); // Initialisation de la liste
    }



    public List<ExpensesCategory> getExpensesCategories() {
        return expensesCategories;
    }

    

    public void setExpensesCategories(List<ExpensesCategory> expensesCategories) {
        this.expensesCategories = expensesCategories;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public float getProfileBudget() {
        return profileBudget;
    }

    public void setProfileBudget(float profileBudget) {
        this.profileBudget = profileBudget;
    }

    
    
    
}
