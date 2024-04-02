package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExpensesCategory;


public interface ExpensesCRepository extends JpaRepository<ExpensesCategory, Long>{

    
    
    List<ExpensesCategory> findAll();
   
    


    
    
    
}
