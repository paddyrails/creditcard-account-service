package com.creditcard.account.service;
import com.creditcard.account.dto.*;
import com.creditcard.account.exception.*;
import com.creditcard.account.model.*;
import com.creditcard.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repo;
    private final SecureRandom random = new SecureRandom();

    public AccountDTO createAccount(CreateAccountRequest req) {
        if(repo.existsByEmail(req.getEmail())) throw new DuplicateAccountException("Email exists");
        Account a = Account.builder().accountNumber(genAccNum()).customerId(UUID.randomUUID().toString())
            .firstName(req.getFirstName()).lastName(req.getLastName()).email(req.getEmail()).phone(req.getPhone())
            .dateOfBirth(req.getDateOfBirth()).totalCreditLimit(req.getTotalCreditLimit()).availableCredit(req.getTotalCreditLimit())
            .interestRate(req.getInterestRate()!=null?req.getInterestRate():new BigDecimal("19.99"))
            .paymentDueDate(LocalDate.now().plusMonths(1)).build();
        return map(repo.save(a));
    }
    @Transactional(readOnly=true) public AccountDTO getAccountById(String id) { return map(findById(id)); }
    @Transactional(readOnly=true) public List<AccountDTO> getAllAccounts() { return repo.findAll().stream().map(this::map).collect(Collectors.toList()); }
    public AccountDTO updateAccount(String id, UpdateAccountRequest req) {
        Account a = findById(id);
        if(req.getEmail()!=null) a.setEmail(req.getEmail());
        if(req.getPhone()!=null) a.setPhone(req.getPhone());
        if(req.getTotalCreditLimit()!=null) { BigDecimal d=req.getTotalCreditLimit().subtract(a.getTotalCreditLimit()); a.setTotalCreditLimit(req.getTotalCreditLimit()); a.setAvailableCredit(a.getAvailableCredit().add(d)); }
        if(req.getInterestRate()!=null) a.setInterestRate(req.getInterestRate());
        return map(repo.save(a));
    }
    public AccountDTO updateStatus(String id, AccountStatus status) { Account a=findById(id); a.setStatus(status); return map(repo.save(a)); }
    public void deleteAccount(String id) { repo.delete(findById(id)); }

    private Account findById(String id) { return repo.findById(id).orElseThrow(()->new AccountNotFoundException("Not found: "+id)); }
    private String genAccNum() { StringBuilder sb=new StringBuilder(); for(int i=0;i<12;i++)sb.append(random.nextInt(10)); String n=sb.toString(); while(repo.existsByAccountNumber(n)){sb=new StringBuilder();for(int i=0;i<12;i++)sb.append(random.nextInt(10));n=sb.toString();} return n; }
    private AccountDTO map(Account a) { return AccountDTO.builder().id(a.getId()).accountNumber(a.getAccountNumber()).firstName(a.getFirstName()).lastName(a.getLastName()).email(a.getEmail()).phone(a.getPhone()).totalCreditLimit(a.getTotalCreditLimit()).availableCredit(a.getAvailableCredit()).currentBalance(a.getCurrentBalance()).paymentDueDate(a.getPaymentDueDate()).status(a.getStatus()).interestRate(a.getInterestRate()).build(); }
}
