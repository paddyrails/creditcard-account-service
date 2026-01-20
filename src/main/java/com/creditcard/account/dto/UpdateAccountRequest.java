package com.creditcard.account.dto;
import lombok.*;
import java.math.BigDecimal;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateAccountRequest { private String email; private String phone; private BigDecimal totalCreditLimit; private BigDecimal interestRate; }
