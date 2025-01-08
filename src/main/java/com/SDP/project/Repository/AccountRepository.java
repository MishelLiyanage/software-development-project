package com.SDP.project.Repository;

import com.SDP.project.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsernameIgnoreCase(String username);
}
