package com.creditcard.account.service;
import com.creditcard.account.dto.*;
import com.creditcard.account.model.AccountStatus;
import java.util.List;
public interface AccountService {
    AccountDTO createAccount(CreateAccountRequest req);
    AccountDTO getAccountById(String id);
    List<AccountDTO> getAllAccounts();
    AccountDTO updateAccount(String id, UpdateAccountRequest req);
    AccountDTO updateStatus(String id, AccountStatus status);
    void deleteAccount(String id);
}
