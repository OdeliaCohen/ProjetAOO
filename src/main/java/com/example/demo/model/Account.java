package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String password;
    private String email;
    private String name;
    private String lastname;
    private int age;

     // Constructeur par défaut nécessaire pour JPA
     public Account() {
        // Ne fait rien, mais JPA en a besoin
    }


    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account(String password, String email, String name, String lastname, int age) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    

    public void updateAccount( String password, String email, String name, String lastname, int age) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public static Account signUp(String password, String repeatPassword, String email, String name, String lastname, int age) {
        if (password == null || password.length() <= 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir plus de 6 caractères.");
        }
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Les mots de passe ne correspondent pas.");
        }
        if (!password.contains("@")) {
            throw new IllegalArgumentException("Le mot de passe doit contenir le caractère '@'.");
        }
        return new Account( password, email, name, lastname, age);
    }


    
}
