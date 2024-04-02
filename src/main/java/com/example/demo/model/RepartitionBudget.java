package com.example.demo.model;

public class RepartitionBudget {
    private double profileBudget;
    private double lastCategoryId;

    public RepartitionBudget(double profileBudget, double lastCategoryId) {
        this.profileBudget = profileBudget;
        this.lastCategoryId = lastCategoryId;
    }

    public double calculateRepartition() {
        if (lastCategoryId != 0) {
            return profileBudget / lastCategoryId;
        } else {
            // Gérer le cas où lastCategoryId est égal à zéro pour éviter une division par zéro
            return 0.0; // Ou une autre valeur par défaut appropriée
        }
    }
}

