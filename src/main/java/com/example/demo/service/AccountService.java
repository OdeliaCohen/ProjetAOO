package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.metamodel.mapping.ForeignKeyDescriptor.Side;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.SignRepository;
import com.example.demo.model.Account;

@Service
public class AccountService {

    private final SignRepository signRepository;
    
    public AccountService(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    public boolean checkIfAccountExists(String email) {
        return signRepository.findByEmail(email).isPresent();
    }

    public void createAccount(Account account) {
        signRepository.save(account);
    }

    public List<Account> findAllAccounts() {
        return signRepository.findAll();
    }


      // Méthode pour vérifier si l'email et le mot de passe correspondent dans la base de données
      public boolean authenticate(String email, String password) {
        Optional<Account> optionalAccount = signRepository.findByEmail(email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            return account.getPassword().equals(password); // Vérifiez si le mot de passe correspond
        }
        return false; // Si aucun compte n'est trouvé avec cet email
    }

    public Account getAccountByEmail(String email) {
        // Ici, nous supposons que accountRepository a une méthode findByEmail définie.
        return signRepository.findByEmail(email).orElse(null); 
        // Utilisez orElse(null) pour retourner un résultat null si aucun compte n'est trouvé.
        // Cela pourrait également être géré en lançant une exception si le compte est requis.
    }

    public Account findAccountById(Long id) {
        return signRepository.findById(id).orElse(null);
    }
       
}
