package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExpensesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String categoryName;

    public ExpensesCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExpensesCategory( String categoryName) {
        this.categoryName = categoryName;
    }

    public ExpensesCategory createCategory(String id, String categoryName) { 
        return new ExpensesCategory( categoryName);
    }
    
}
