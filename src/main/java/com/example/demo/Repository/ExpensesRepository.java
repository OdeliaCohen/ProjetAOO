package com.example.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Expenses;
import com.example.demo.model.ExpensesCategory;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>{
    List<Expenses> findByCategory(ExpensesCategory category);

   
    
} 
    

