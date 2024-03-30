package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ExpensesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "profile_id") // This column will link back to the Profile entity
    @JsonBackReference
    private Profile profile;

    

    public ExpensesCategory(String categoryName, Profile profile) {
        this.categoryName = categoryName;
        this.profile = profile;
    }

    // Constructeur par défaut
    public ExpensesCategory() {
        // Ce constructeur est nécessaire pour que Hibernate puisse créer une instance de l'entité
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

   
    public ExpensesCategory createCategory(String id, String categoryName) { 
        return new ExpensesCategory(categoryName, profile);
    }
    
    
    
}
