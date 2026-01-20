package com.creditcard.account.dto;
import com.creditcard.account.model.AccountStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDTO {
    private String id; private String accountNumber; private String firstName; private String lastName;
    private String email; private String phone; private BigDecimal totalCreditLimit; private BigDecimal availableCredit;
    private BigDecimal currentBalance; private LocalDate paymentDueDate; private AccountStatus status; private BigDecimal interestRate;
}
