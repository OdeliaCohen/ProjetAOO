package com.example.demo.model;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private float amountToSpend;
    private float amountSpent;
    private Date spendDay;

    public Expenses() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expenses(float amountToSpend, float amountSpent, Date spendDay) {
        this.amountToSpend = amountToSpend;
        this.amountSpent = amountSpent;
        this.spendDay = spendDay;
    }

    public boolean gererDepense(float amountSpent){
        if(amountSpent <= amountToSpend){ // if the amount spent is less than or equal to the amount to spend
            this.amountSpent += amountSpent;
            return true;
        }
        return false;
    }
    public float calculerDepenseJour(){
        return amountSpent;
    }
    
}
