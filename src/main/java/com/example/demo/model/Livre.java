
package com.example.demo.model;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Livre {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private String titre;

    private String auteur;

    private String isbn;

    private float prix;

    
}