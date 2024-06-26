package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.ExpensesCRepository;
import com.example.demo.model.ExpensesCategory;



@Service
public class ExpensesCService {

    private final ExpensesCRepository expensesCRepository;
    
    public ExpensesCService(ExpensesCRepository expensesCRepository) {
        this.expensesCRepository = expensesCRepository;
    }

    public void saveCategory(ExpensesCategory expensesCategory) {
        expensesCRepository.save(expensesCategory);
    }

   public java.util.List<ExpensesCategory> findAllCategories() { //on a mit Iterable car on a pas besoin de la taille de la liste
        return expensesCRepository.findAll();
    }

public ExpensesCategory findCategoryByName(String categoryName) {
    return expensesCRepository.findByCategoryName(categoryName);
}




    
}
