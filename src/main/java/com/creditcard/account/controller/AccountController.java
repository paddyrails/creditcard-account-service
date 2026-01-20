package com.creditcard.account.controller;
import com.creditcard.account.dto.*;
import com.creditcard.account.model.AccountStatus;
import com.creditcard.account.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/accounts") @RequiredArgsConstructor @Tag(name="Account Management")
public class AccountController {
    private final AccountService svc;
    @PostMapping public ResponseEntity<AccountDTO> create(@Valid @RequestBody CreateAccountRequest req) { return ResponseEntity.status(HttpStatus.CREATED).body(svc.createAccount(req)); }
    @GetMapping("/{id}") public ResponseEntity<AccountDTO> getById(@PathVariable String id) { return ResponseEntity.ok(svc.getAccountById(id)); }
    @GetMapping public ResponseEntity<List<AccountDTO>> getAll() { return ResponseEntity.ok(svc.getAllAccounts()); }
    @PutMapping("/{id}") public ResponseEntity<AccountDTO> update(@PathVariable String id, @Valid @RequestBody UpdateAccountRequest req) { return ResponseEntity.ok(svc.updateAccount(id,req)); }
    @PatchMapping("/{id}/status") public ResponseEntity<AccountDTO> updateStatus(@PathVariable String id, @RequestParam AccountStatus status) { return ResponseEntity.ok(svc.updateStatus(id,status)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable String id) { svc.deleteAccount(id); return ResponseEntity.noContent().build(); }
}
