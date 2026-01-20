package com.creditcard.account.repository;
import com.creditcard.account.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByAccountNumber(String num);
}
