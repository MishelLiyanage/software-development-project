package com.SDP.project.services.impli;

import com.SDP.project.Repository.AccountRepository;
import com.SDP.project.models.Account;
import com.SDP.project.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Implementation of the UserService interface that handles user-related operations.
@Service
public class UserServiceImpli implements UserService {

    // Repository for accessing account data from the database
    private final AccountRepository accountRepository;

    // Constructor to initialize UserServiceImpli with the required AccountRepository dependency.
    public UserServiceImpli(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Retrieves all user accounts from the database.
    public List<Account> allUsers() {
        // List to store all user accounts
        List<Account> users = new ArrayList<>();

        // Fetch all user accounts from the repository and add them to the list
        accountRepository.findAll().forEach(users::add);

        return users;
    }
}
