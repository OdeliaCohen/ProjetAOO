package com.example.demo.controller;
import com.example.demo.Repository.LivreRepository;
import com.example.demo.model.Livre;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class LivreController {
    @Autowired
    private LivreRepository livreRepository;



 /*   @GetMapping("/livre")
    public List<Livre> listLivre() {
        Livre l1 = new Livre();
        Livre l2 = new Livre();
        livreRepository.save(l1);
        livreRepository.save(l2);
       
        return  livreRepository.findAll();
    }
*/

    @GetMapping("/livre")
    public String listLivre() {
        Livre l1 = new Livre();
        livreRepository.save(l1);
        Livre l2 = new Livre();
        livreRepository.save(l2);
        List<Livre> list = livreRepository.findAll();
        return list.toString();
        
        
    }

    
}