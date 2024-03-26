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
    private String repeatPassword;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() <= 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir plus de 6 caractères.");
        }
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        if (repeatPassword == null || repeatPassword.length() <= 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir plus de 6 caractères.");
        }
        this.repeatPassword = repeatPassword;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() <= 2) {
            throw new IllegalArgumentException("Le nom doit contenir plus de 2 caractères.");
        }
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.length() <= 2) {
            throw new IllegalArgumentException("Le nom doit contenir plus de 2 caractères.");
        }
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("L'âge doit être supérieur à 18 ans.");
        }
        this.age = age;
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
       
        return new Account( password, email, name, lastname, age);
    }


    
}
