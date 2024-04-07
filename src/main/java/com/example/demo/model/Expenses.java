package com.example.demo.model;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private float amountToSpend;
    private float amountSpent;
    private Date spendDay;
    private float amountSpentDay;
    private float amountSpentPerCategory;


    @ManyToOne
    @JoinColumn(name = "category_id") // Cette colonne fera le lien avec l'entité ExpensesCategory
    private ExpensesCategory category;

    public Expenses() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public float getAmountToSpend() {
        return amountToSpend;
    }

    public void setAmountToSpend(float amountToSpend) {
        this.amountToSpend = amountToSpend;
    }

    public float getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(float amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Date getSpendDay() {
        return spendDay;
    }

    public void setSpendDay(Date spendDay) {
        this.spendDay = spendDay;
    }

    public ExpensesCategory getCategory() {
        return category;
    }

    public void setCategory(ExpensesCategory category) {
        this.category = category;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public float getAmountSpentDay() {
        return amountSpentDay;
    }

    public void setAmountSpentDay(float amountSpentDay) {
        this.amountSpentDay = amountSpentDay;
    }

    public float getAmountSpentPerCategory() {
        return amountSpentPerCategory;
    }

    public void setAmountSpentPerCategory(float amountSpentPerCategory) {
        this.amountSpentPerCategory = amountSpentPerCategory;
    }




    public Expenses(float amountToSpend, float amountSpent, Date spendDay, float amountSpentDay, float amountSpentPerCategory) {
        this.amountToSpend = amountToSpend;
        this.amountSpent = amountSpent;
        this.spendDay = spendDay;
        this.amountSpentDay = amountSpentDay;
        this.amountSpentPerCategory = amountSpentPerCategory;
    }


    
    
}
